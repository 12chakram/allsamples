package biz.neustar.dece.portal.saml.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.signature.SignatureException;

import biz.neustar.dece.common.reference.DeceErrorID;
import biz.neustar.dece.ui.UIDeceException;

public class SAMLAuthenRequestServlet extends HttpServlet
{
	private final Logger logger = Logger.getLogger(SAMLAuthenRequestServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<String,String> errorValueMap = new HashMap<String, String>();
		ResourceBundle errorTxtsBundle= ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.errorTxts");
		try
		{
			processRequest(request, response);
		}
		catch (Exception e)
		{
			try
			{
				System.out.println("throwing here finally");
				throw new Exception(DeceErrorID.INTERNAL_SERVER_ERROR, e);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			// ExceptionUtils.processUIDECEException("SAML AUTHREQUEST",
			// "ClassName:SAMLAuthenRequestServlet:doPost", e,
			// APIErrorMapUtil.uiErrorCodeFieldMap,
			// errorValueMap, errorTxtsBundle);

		}
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws UIDeceException, Exception
	{
		SAMLRequestParams params = new SAMLRequestParams();

		ServletContext context = getServletConfig().getServletContext();

		params.setCertificateURL((String) context.getAttribute("samlPublicKey"));

		params.setCertificateKeyURL((String) context
				.getAttribute("samlPrivateKey"));

		params.setCoordinatorURL((String) context.getAttribute("shostUrl"));

		params.setIssuer("urn:dece:org:org:dece:10");
		
		params.setPolicies("urn:dece:type:policy:UserLinkConsent");

		try
		{
			new SAMLRequest().submitRequest(params, response);
		}
		catch (MessageEncodingException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (CertificateException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (ClassCastException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (ConfigurationException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (MarshallingException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (SignatureException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			throw e;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			logger.error(exception.getStackTrace());
			throw exception;
		}
	}
}
