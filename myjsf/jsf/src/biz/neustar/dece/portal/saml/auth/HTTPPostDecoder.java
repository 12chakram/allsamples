package biz.neustar.dece.portal.saml.auth;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.BaseSAML2MessageDecoder;
import org.opensaml.ws.message.MessageContext;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.DatatypeHelper;

public class HTTPPostDecoder extends BaseSAML2MessageDecoder
{
	/** Class logger. */
	private final Logger log = Logger.getLogger(HTTPPostDecoder.class);
	private String postString;

	public String getPostString()
	{
		return postString;
	}

	public void setPostString(String postString)
	{
		this.postString = postString;
	}

	public HTTPPostDecoder()
	{

	}

	/**
	 * Constructor.
	 * 
	 * @param pool
	 *            parser pool used to de-serialize messages
	 */
	public HTTPPostDecoder(ParserPool pool)
	{
		super(pool);
	}

	/** {@inheritDoc} */
	public String getBindingURI()
	{
		return SAMLConstants.SAML2_POST_BINDING_URI;
	}

	/** {@inheritDoc} */
	protected boolean isIntendedDestinationEndpointURIRequired1(
			SAMLMessageContext samlMsgCtx)
	{
		return isMessageSigned(samlMsgCtx);
	}

	/** {@inheritDoc} */
	protected void doDecode(MessageContext messageContext)
			throws MessageDecodingException
	{
		if (!(messageContext instanceof SAMLMessageContext))
		{
			log.error("Invalid message context type, this decoder only support SAMLMessageContext");
			throw new MessageDecodingException(
					"Invalid message context type, this decoder only support SAMLMessageContext");
		}

		if (!(messageContext.getInboundMessageTransport() instanceof HTTPInTransport))
		{
			log.error("Invalid inbound message transport type, this decoder only support HTTPInTransport");
			throw new MessageDecodingException(
					"Invalid inbound message transport type, this decoder only support HTTPInTransport");
		}

		SAMLMessageContext samlMsgCtx = (SAMLMessageContext) messageContext;

		HTTPInTransport inTransport = (HTTPInTransport) samlMsgCtx
				.getInboundMessageTransport();
		if (!inTransport.getHTTPMethod().equalsIgnoreCase("POST"))
		{
			throw new MessageDecodingException(
					"This message deocoder only supports the HTTP POST method");
		}

		String relayState = inTransport.getParameterValue("RelayState");
		samlMsgCtx.setRelayState(relayState);
		log.debug("Decoded SAML relay state of: {}" + relayState);

		InputStream base64DecodedMessage = getBase64DecodedMessage(inTransport);
		SAMLObject inboundMessage = (SAMLObject) unmarshallMessage(base64DecodedMessage);
		samlMsgCtx.setInboundMessage(inboundMessage);
		samlMsgCtx.setInboundSAMLMessage(inboundMessage);
		log.debug("Decoded SAML message");

		populateMessageContext(samlMsgCtx);
	}

	/**
	 * Gets the Base64 encoded message from the request and decodes it.
	 * 
	 * @param transport
	 *            inbound message transport
	 * 
	 * @return decoded message
	 * 
	 * @throws MessageDecodingException
	 *             thrown if the message does not contain a base64 encoded SAML
	 *             message
	 */
	protected InputStream getBase64DecodedMessage(HTTPInTransport transport)
			throws MessageDecodingException
	{
		log.debug("Getting Base64 encoded message from request");
		String rawMessage = this.getPostString();
		String encodedMessage = null;
		if (rawMessage == null)
		{
			log.error("Either SAMLRequest message in AuthnRequest using POST method was null or had some issues");
			throw new MessageDecodingException(
					"Unable to Base64 decode SAML message");
		}
		try
		{
			encodedMessage = URLDecoder.decode(rawMessage, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new MessageDecodingException(e);
		}
		if (DatatypeHelper.isEmpty(encodedMessage))
		{
			encodedMessage = transport.getParameterValue("SAMLResponse");
		}

		if (DatatypeHelper.isEmpty(encodedMessage))
		{
			log.error("Request did not contain either a SAMLRequest or "
					+ "SAMLResponse paramter.  Invalid request for SAML 2 HTTP POST binding.");
			throw new MessageDecodingException(
					"No SAML message present in request");
		}

		log.debug("Base64 decoding SAML message:\n{}" + encodedMessage);
		byte[] decodedBytes = Base64.decode(encodedMessage);
		if (decodedBytes == null)
		{
			log.error("Unable to Base64 decode SAML message");
			throw new MessageDecodingException(
					"Unable to Base64 decode SAML message");
		}

		log.debug("Decoded SAML message:\n{}" + new String(decodedBytes));
		return new ByteArrayInputStream(decodedBytes);
	}

	@Override
	protected boolean isIntendedDestinationEndpointURIRequired(
			SAMLMessageContext samlMsgCtx)
	{
		return isMessageSigned(samlMsgCtx);
	}
}
