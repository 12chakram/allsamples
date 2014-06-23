/**
 * 
 */
package biz.neustar.dece.portal.saml.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.parse.XMLParserException;
import org.opensaml.xml.signature.SignatureException;

public class HTTPRedirectDeflateEncoderImpl extends HTTPRedirectDeflateEncoder
{
	private final Logger logger = Logger.getLogger(HTTPRedirectDeflateEncoderImpl.class);
	private AuthnRequest authnRequest;
	private SAMLAuthnRequest samlAuthnRequest;
	private BasicSAMLMessageContext basicSAMLMessageContext;
	private String actionURL = null;
	private String contextURL = null;

	/**
	 * @throws Exception
	 * @throws XMLParserException
	 * 
	 */
	private String generateRelayState()
	{
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[80];
		random.nextBytes(bytes);
		return new String(bytes);
	}

	public void encode() throws MessageEncodingException
	{
		super.encode(this.basicSAMLMessageContext);
	}

	public String getContextURL()
	{
		return contextURL;
	}

	public void setContextURL(String contextURL)
	{
		this.contextURL = contextURL;
	}

	public AuthnRequest getAuthnRequest()
	{
		return authnRequest;
	}

	public void setAuthnRequest(AuthnRequest authnRequest)
	{
		this.authnRequest = authnRequest;
	}

	public SAMLAuthnRequest getSamlAuthnRequest()
	{
		return samlAuthnRequest;
	}

	public void setSamlAuthnRequest(SAMLAuthnRequest samlAuthnRequest)
	{
		this.samlAuthnRequest = samlAuthnRequest;
	}

	public String getActionURL()
	{
		return actionURL;
	}

	public void setActionURL(String actionURL)
	{
		this.actionURL = actionURL;
	}

	public HTTPRedirectDeflateEncoderImpl(SAMLRequestParams samlRequestParams,
			HttpServletResponse response) throws FileNotFoundException,
			CertificateException, ConfigurationException, IOException,
			ClassCastException, MarshallingException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, SignatureException
	{
		samlAuthnRequest = new SAMLAuthnRequest(
				samlRequestParams.getCertificateKeyURL(),
				samlRequestParams.getCertificateURL());

		this.setContextURL(samlRequestParams.getCoordinatorURL());
		samlAuthnRequest.setBindingUriFormat(SAMLAuthnRequest.REDIRECT_BINDING);
		samlAuthnRequest.setActionURL(samlRequestParams.getCoordinatorURL());
		// samlAuthnRequest.setLoc(loc);
		samlAuthnRequest.setDisplay(1);

		authnRequest = samlAuthnRequest.buildAuthnRequest(samlRequestParams);
		System.out.println(new HttpHandler().printAuthnRequest(authnRequest));
		this.basicSAMLMessageContext = new BasicSAMLMessageContext();
		this.basicSAMLMessageContext.setRelayState(this.generateRelayState());
		Endpoint endPoint = samlAuthnRequest.getEndPoint(this.getContextURL());
		System.out.println("URL from endpoint=" + endPoint.getLocation());

		this.basicSAMLMessageContext.setPeerEntityEndpoint(endPoint);
		this.basicSAMLMessageContext.setOutboundSAMLMessage(authnRequest);

		HttpServletResponseAdapter adapter = new HttpServletResponseAdapter(
				response, false);
		this.basicSAMLMessageContext.setOutboundMessageTransport(adapter);
		this.basicSAMLMessageContext
				.setOutboundSAMLMessageSigningCredential(this.samlAuthnRequest
						.getSamlCommon().getCredential());
	}
}
