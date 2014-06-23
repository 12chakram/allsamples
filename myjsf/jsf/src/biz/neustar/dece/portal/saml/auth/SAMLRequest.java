package biz.neustar.dece.portal.saml.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;

import javax.servlet.http.HttpServletResponse;

import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.signature.SignatureException;
 
public class SAMLRequest
{
	public void submitRequest(SAMLRequestParams samlRequestParams,
			HttpServletResponse response) throws FileNotFoundException,
			CertificateException, ClassCastException, ConfigurationException,
			IOException, MarshallingException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, SignatureException,
			MessageEncodingException
	{
		HTTPRedirectDeflateEncoderImpl encoder = new HTTPRedirectDeflateEncoderImpl(
				samlRequestParams, response);
		encoder.encode();
	}
}
