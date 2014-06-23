package biz.neustar.dece.portal.saml.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.BindingException;
import org.opensaml.saml2.binding.decoding.HTTPRedirectDeflateDecoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Response;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

import biz.neustar.dece.portal.saml.util.Util;

public class HttpHandler
{
	private static boolean bootstrap = false;
	
	private static int bootcount = 0;
	/** Class logger. */
	private static final Logger logger = Logger.getLogger( HttpHandler.class);

	/** HTTP request param name for SAML response. */
	public static final String RESPONSE_PARAM = "SAMLResponse";

	/** HTTP request param name for relay state. */
	public static final String RELAY_STATE_PARAM = "RelayState";

	protected String xmlSAMLRequest, relayState;

	private BasicSAMLMessageContext context;

	public void setXMLSAMLRequest(String newXMLSAMLRequest)
	{
		xmlSAMLRequest = newXMLSAMLRequest;
	}

	public String getXMLSAMLRequest()
	{
		return xmlSAMLRequest;
	}

	public void setRelayState(String newRelayState)
	{
		relayState = newRelayState;
	}

	public String getRelayState()
	{
		return relayState;
	}

	/*
	 * Create the HttpHandler object for Idp usage.
	 */
	public HttpHandler() throws org.opensaml.xml.ConfigurationException
	{
		// do the bootstrap thing and make sure the library is happy
		if (bootstrap == false)
		{
			org.opensaml.DefaultBootstrap.bootstrap();
			bootstrap = true;
			if (logger.isInfoEnabled())
			{
				bootcount++;
				logger.info("HttpHandler.java (line 86) bootstrap has been called. ["
						+ bootcount + "]");
			}
		}
	}

	public BasicSAMLMessageContext getContext()
	{
		//this.context = new BasicSAMLMessageContext();
		
		return this.context;
	}

	public Response decodeSAMLResponse(HttpServletRequest request)
			throws BindingException,
			org.opensaml.ws.security.SecurityPolicyException,
			java.util.zip.DataFormatException,
			org.opensaml.ws.message.MessageException,
			org.opensaml.xml.security.SecurityException, IOException, Exception
	{
		try
		{
			Response samlResponse = null;

			// System.out.println("HttpHandler:decodeSAMLRequest");
			if (this.logger.isDebugEnabled())
			{
				this.logger.debug("HttpHandler:decodeSAMLResponse");
			}
			// First see whether we have a GET or POST so we know where to look
			// for the data
			if (request.getMethod().equalsIgnoreCase("POST"))
			{
				// System.out.println("HttpHandler:decodeSAMLRequest - Found POST");
				if (this.logger.isDebugEnabled())
				{
					this.logger
							.debug("HttpHandler:decodeSAMLResponse - Found POST");
				}

				HTTPPostDecoder decode = new HTTPPostDecoder(
						new BasicParserPool());

				InputStream is = request.getInputStream();
				String resp = Util.convertStreamToString(is, true);
				String urlDecoded = null;
				this.logger
						.debug("Post Decoding:request is post type=" + resp);

				RelayStateMgmt rsm = new RelayStateMgmt(resp,
						HttpHandler.RESPONSE_PARAM);
				String relState = rsm.getRelayState();
				if (relState != null)
				{
					logger.debug("Relaystate=" + relState);
					this.setRelayState(relState);
				}
				// log.debug("Relaystate="+rsm.getRelayState());
				resp = rsm.getRequest();

				if (resp.startsWith(HttpHandler.RESPONSE_PARAM))
				{
					// log.debug("Request is of HTTPPost AuthnRequest type");
					String data[] = resp.split("=");
					urlDecoded = data[1];
					this.logger
							.debug("Request is of HTTPPost SAML Response type with data="
									+ data[1]);
				}

				// is.close();
				decode.setPostString(urlDecoded);
				HttpServletRequestAdapter adapter = new HttpServletRequestAdapter(
						request);
				this.context = new BasicSAMLMessageContext();
				context.setInboundMessageTransport(adapter);
				decode.decode(context);
				// Save the SAML Request as a SAML Object
				samlResponse = (Response) context.getInboundMessage();
				if (!samlResponse.isSigned())
				{
					this.logger
							.info("Post LogoutRequest Message is not signed,throwing exception");
					throw new Exception();
				}
			}
			else if (request.getMethod().equalsIgnoreCase("GET"))
			{
				// System.out.println("HttpHandler:decodeSAMLRequest - Found GET");
				if (this.logger.isDebugEnabled())
				{
					this.logger
							.debug("HttpHandler:decodeSAMLResponse - Found GET");
				}
				HTTPRedirectDeflateDecoder decode = new HTTPRedirectDeflateDecoder(
						new BasicParserPool());
				HttpServletRequestAdapter adapter = new HttpServletRequestAdapter(
						request);
				this.context = new BasicSAMLMessageContext();

				context.setInboundMessageTransport(adapter);

				decode.decode(context);
				// Save the SAML Request as a SAML Object
				samlResponse = (Response) context.getInboundMessage();

				// samlRequest = (AuthnRequest) decode.getSAMLMessage();
				// Now save it as a String in case we need it later
				byte[] b = Base64.decode(request
						.getParameter(HttpHandler.RESPONSE_PARAM));

				byte[] i = new byte[b.length * 3];
				this.xmlSAMLRequest = new String(b);
				java.util.zip.Inflater inflater = new java.util.zip.Inflater(
						true);
				inflater.setInput(b);
				inflater.inflate(i);
				this.xmlSAMLRequest = new String(i);

				// Now save the Relay State as an encoded value. We only return
				// this to the SP, so no need to Base64 decode it.
				this.relayState = adapter
						.getParameterValue(HttpHandler.RELAY_STATE_PARAM);
			}
			else
			{
				// bad things happened here
			}
			// return samlResponse
			return samlResponse;
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}
	
	public String printAuthnRequest(AuthnRequest auth)
			throws MarshallingException
	{
		// Now we must build our representation to put into the html form to
		// be submitted to the idp
		Marshaller marshaller = org.opensaml.xml.Configuration
				.getMarshallerFactory().getMarshaller(auth);
		Element authDOM = marshaller.marshall(auth);
		StringWriter rspWrt = new StringWriter();
		XMLHelper.writeNode(authDOM, rspWrt);
		String requestXML = rspWrt.toString();
		logger.info("AuthnRequest= " + requestXML);
		return requestXML;
	}

	public String printSamlResponse(Response auth) throws MarshallingException
	{
		// Now we must build our representation to put into the html form to
		// be submitted to the idp
		Marshaller marshaller = org.opensaml.xml.Configuration
				.getMarshallerFactory().getMarshaller(auth);
		Element authDOM = marshaller.marshall(auth);
		StringWriter rspWrt = new StringWriter();
		XMLHelper.writeNode(authDOM, rspWrt);
		String responseXML = rspWrt.toString();
		logger.info("SAMLResponse= " + responseXML);
		System.out.println("SAMLResponse= " + responseXML);
		return responseXML;
	}
}
