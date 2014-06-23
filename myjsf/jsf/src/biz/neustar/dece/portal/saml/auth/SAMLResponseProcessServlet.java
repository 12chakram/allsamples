package biz.neustar.dece.portal.saml.auth;

import java.io.IOException;

import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.binding.security.SAMLProtocolMessageXMLSignatureSecurityPolicyRule;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.xml.ConfigurationException;

import biz.neustar.dece.backing.bean.sections.login.LoginNavigationBean;
import biz.neustar.dece.common.reference.DeceErrorID;
import biz.neustar.dece.portal.saml.exception.InvalidSignatureException;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.service.UILoginService;
import biz.neustar.dece.ui.service.UIReverseSAMLService;

public class SAMLResponseProcessServlet extends HttpServlet
{
	private SignatureVerifier deceSignVerifier;
	private HttpHandler httpHandler;
	private static Logger logger = Logger.getLogger(SAMLResponseProcessServlet.class);
	private UILoginService	uiLoginService	= ServiceLocator.getUiLoginService();

	public void init()
	{
		try
		{	
			httpHandler = new HttpHandler();
		}
		catch (ConfigurationException e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
			try
			{
				throw e;
			}
			catch (ConfigurationException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		try
		{
			processResponse(req, resp);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// forward to error page
			try
			{
				throw e;
			}
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			processResponse(request, response);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			// forward to error page
			try
			{
				System.out.println("throwing here finally......");
				// instead of throwing back, we have to handle the exception here
				throw new Exception(DeceErrorID.INTERNAL_SERVER_ERROR, e);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	protected void processResponse(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		try
		{
			Response samlResponse = null;
			HttpSession usrSession = null;
			String returnPath = null;	
			String targetURL = null;
			String tokenType = null;
			
			samlResponse = this.httpHandler.decodeSAMLResponse(request);
			
			BasicSAMLMessageContext basicContext = this.httpHandler.getContext();
					
			((SAMLMessageContext) basicContext)
					.setInboundSAMLMessageAuthenticated(false);

			basicContext
					.setPeerEntityRole(SPSSODescriptor.DEFAULT_ELEMENT_NAME);

			String issuer = basicContext.getInboundMessageIssuer();
			
			logger.info("issuer:" + issuer);

			// Verify the signature.
			if (request.getMethod().equalsIgnoreCase("GET"))
			{
				logger.info("Trying to verify the signature during GET operation");
				this.deceSignVerifier = new SignatureVerifier(
						(String) getServletConfig().getServletContext()
								.getAttribute("samlPortalMetadata"), "GET");
				try
				{
					this.deceSignVerifier.getSignatureRule().evaluate(
							basicContext);
					logger.info("Signature is validated successfully");
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					throw new InvalidSignatureException();
				}
			}
			else
			{
				this.deceSignVerifier = new SignatureVerifier(
						(String) getServletConfig().getServletContext()
								.getAttribute("samlPortalMetadata"), "POST");
				logger.info("Trying to verify the signature during POST operation......");
				SAMLProtocolMessageXMLSignatureSecurityPolicyRule spms = this.deceSignVerifier
						.buildSAMLProtocolMessageXMLSignatureSecurityPolicyRule(deceSignVerifier
								.getSignatureTrustEngine());
				try
				{
					spms.evaluate(basicContext);
					logger.info("Signature is validated successfully");
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					System.out.println("throwing here1");
					throw new InvalidSignatureException();
				}
			}

			try
			{	
				httpHandler.printSamlResponse(samlResponse);
				
				SAMLResponseBean resBean = new SAMLResponseBean();
				
				SAMLTokenValidator dsamlTokenValidator = new SAMLTokenValidator();
				
				dsamlTokenValidator.getAccountID(samlResponse.getAssertions().get(0));
				
				String userId = dsamlTokenValidator.getNameID(samlResponse.getAssertions().get(0));
				
				tokenType = dsamlTokenValidator.getToken(samlResponse);
				
				logger.info("userId=" + userId);
				
				UserSignInDVO userSignInDVO = uiLoginService.loginByNodeUserIdForShost(userId);
				
				//if UserSignInDVO is null then redirect user to home page. This is done since if 
				//portalNodeUserId is invalid so method will return null and does not throw an exception
				if(null==userSignInDVO)
				{
					response.sendRedirect("public/homePage.jsf");
				}
				usrSession = request.getSession(true);
				if ( !usrSession.isNew() )
				{
					usrSession.invalidate();
					usrSession = request.getSession(true);
				}
				
				// Load FacesContext and set currentInstance
				InnerFacesContext.setFacesContextAsCurrentInstance(getFacesContext(
								request, response, usrSession.getServletContext()));
				LoginNavigationBean loginNavigationBean = new LoginNavigationBean();
				loginNavigationBean.setDetailsIntoSession(userSignInDVO,usrSession);
				
				usrSession.setAttribute("shost", "shost");
				usrSession.setAttribute("SignIn", userSignInDVO);

				returnPath = loginNavigationBean.loginAction();
				
				if(tokenType==null)
				{
					if(!"pendingAccountDashboardPage".equalsIgnoreCase(returnPath))
					{
						targetURL = "views/mediaPage.jsf";
					}
					else
					{
						if(usrSession.getAttribute("toShowPopupOf")!=null && usrSession.getAttribute("toShowPopupOf").equals("EMAIL_CONFIRMATION_PENDING"))
						{
							targetURL = "views/pendingAccountDashboardPage.jsf";
						}
						else
						{
							targetURL = "views/mediaPage.jsf";
						}
					}
				}
				else
				{
					if(!tokenType.equals("ResetPassword"))
					{
						if(!"pendingAccountDashboardPage".equalsIgnoreCase(returnPath))
						{
							targetURL = "views/mediaPage.jsf";
						}
						else
						{
							if(usrSession.getAttribute("toShowPopupOf")!=null && usrSession.getAttribute("toShowPopupOf").equals("EMAIL_CONFIRMATION_PENDING"))
							{
								targetURL = "views/pendingAccountDashboardPage.jsf";
							}
							else
							{
								targetURL = "views/mediaPage.jsf";
							}
						}
					}
					else
					{
						targetURL = "views/userProfilePage.jsf";
					}
				}
				
				usrSession.setAttribute("targetURL", targetURL);
				response.sendRedirect(targetURL);
			}
			catch (Exception ex)
			{
				logger.info(ex.getMessage(), ex);
				System.out.println("throwing here2");
				throw ex;
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("throwing here3");
			throw e;
		}
	}
	
	// You need an inner class to be able to call
	// FacesContext.setCurrentInstance since it's a protected method
	private abstract static class InnerFacesContext extends FacesContext
	{
		protected static void setFacesContextAsCurrentInstance(
				FacesContext facesContext)
		{
			FacesContext.setCurrentInstance(facesContext);
		}
	}
    
	private FacesContext getFacesContext(HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null)
		{
			FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder
					.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
			LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder
					.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
			Lifecycle lifecycle = lifecycleFactory
					.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

			facesContext = contextFactory.getFacesContext(servletContext,
					request, response, lifecycle);

			logger.info("servletContext.getContextPath: "
					+ servletContext.getContextPath());

			// set a new viewRoot, otherwise context.getViewRoot returns null
			UIViewRoot view = facesContext.getApplication().getViewHandler()
					.createView(facesContext, "");

			facesContext.setViewRoot(view);
		}
		return facesContext;
	}
}
