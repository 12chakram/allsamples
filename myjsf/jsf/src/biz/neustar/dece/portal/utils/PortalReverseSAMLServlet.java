/**
 * 
 */
package biz.neustar.dece.portal.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

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

import biz.neustar.dece.backing.bean.sections.login.LoginNavigationBean;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.RSAMLParamsDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.dvo.RSAMLParamsDVO.Action;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;
import biz.neustar.dece.ui.service.UIReverseSAMLService;
import biz.neustar.dece.ui.service.UIUserService;

public class PortalReverseSAMLServlet extends HttpServlet
{
	private static final long		serialVersionUID		= 1L;
	private static final Logger		logger					= Logger.getLogger(UIReverseSAMLService.class);

	private UIReverseSAMLService	uiReverseSAMLService	= ServiceLocator.getUiReverseSAMLService();
	private UIUserService	uiUserService	= ServiceLocator.getUiUserService();
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// UserSignInDVO userSignInDVO = null;
		HttpSession usrSession = null;		
		RSAMLParamsDVO rSAMLParamsDVO = uiReverseSAMLService.getRSAMLParams(request.getQueryString(), request.getParameterMap());
		ResourceBundle errorTxtsBundle= ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.errorTxts");
		HashMap<String,String> errorValueMap = new HashMap<String, String>();
		
		//uncomment below snippet to test RSAML independently
		/*rSAMLParamsDVO = new RSAMLParamsDVO();
		rSAMLParamsDVO.setPortalNodeUserId("urn:dece:userid:org:dece:9934581B501E0F08E0401F0A0F994829");
		rSAMLParamsDVO.setAction(Action.AUTHENTICATE);
		rSAMLParamsDVO.setActionEnum("login");
		rSAMLParamsDVO.setTargetURL("http://172.24.5.4/ssp/views/accountDashboardPage.jsf");*/
		
		String portalHomeURL=null;
		Object homeURL = getServletContext().getAttribute("portalglobalUrl");
		if(null!= homeURL)
		{
			portalHomeURL = homeURL.toString();
		}
		if ( rSAMLParamsDVO != null )
		{
			Action action = rSAMLParamsDVO.getAction();
			if ( action != null )
			{
				switch ( action )
				{
					case AUTHENTICATE:
						try
						{
							UserSignInDVO userSignInDVO = uiReverseSAMLService.loginByNodeUserId(rSAMLParamsDVO.getPortalNodeUserId());
							//if UserSignInDVO is null then redirect user to home page. This is done since if portalNodeUserId is invalid method will return null and does not throw an exception 
							if(null==userSignInDVO)
							{
								//response.sendRedirect("public/homePage.jsf");	
								response.sendRedirect(portalHomeURL);
							}
							usrSession = request.getSession(true);
							if ( !usrSession.isNew() )
							{
								usrSession.invalidate();
								usrSession = request.getSession(true);
							}
							usrSession.setAttribute("currUserViewId", rSAMLParamsDVO.getPortalNodeUserId());
							// Load FacesContext and set currentInstance
				            InnerFacesContext.setFacesContextAsCurrentInstance(getFacesContext(request, response, usrSession.getServletContext()));
							LoginNavigationBean loginNavigationBean = new LoginNavigationBean();
							loginNavigationBean.setDetailsIntoSession(userSignInDVO,usrSession);
							usrSession.setAttribute("RSAMLParamsDVO", rSAMLParamsDVO);
							usrSession.setAttribute("SignIn", userSignInDVO);

							loginNavigationBean.loginAction();
							String targetURL = rSAMLParamsDVO.getTargetURL();
							usrSession.setAttribute("targetURL", targetURL);
							if (userSignInDVO.getNextPage().equals(UINextPageEnum.DASHBOARD) || targetURL.contains("/views/touPage.jsf")){
								if(targetURL.contains("/views/touPage.jsf") && userSignInDVO.getNextPage().equals(UINextPageEnum.TOU_SELF))
									usrSession.setAttribute("touAvailableFlag", "YES");
								else
									usrSession.setAttribute("touAvailableFlag", "NO");
								response.sendRedirect(targetURL);
							}else if (userSignInDVO.getNextPage().equals(UINextPageEnum.HOSTAGE)) {
								response.sendRedirect("public/hostagePage.jsf");
							}else
								response.sendRedirect("views/pendingAccountDashboardPage.jsf");
						}
						catch ( UIDeceException e )
                        {
							ExceptionUtils.processUIDECEException("Reverse SAML Servlet","ClassName:PortalReverseSAMLServlet:doGet", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorTxtsBundle);
							usrSession = request.getSession(true);
							Iterator<UIError> itr=e.getErrors().iterator();
							UIError errorInstance = itr.next();
							String errCode = errorInstance.getErrorCode();
							usrSession.setAttribute("pageError",errCode);
							if("TOU_NOT_ACCEPTED_BY_CLG".equals(errCode) || "COPPA_NOT_ACCEPTED_BY_CLG".equals(errCode) || "ACCOUNT_USER_STATUS_BLOCKED_CLG".equals(errCode))
							{
								String clgUserName="";
								try {
									clgUserName=uiUserService.getUserName(rSAMLParamsDVO.getPortalNodeUserId());
									usrSession.setAttribute("clgUser",clgUserName);
								} catch (UIDeceException e1) {
									ExceptionUtils.processUIDECEException("Reverse SAML Servlet","ClassName:PortalReverseSAMLServlet:doGet", e1, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorTxtsBundle);
								}
							}
                              logger.warn("Exception caught while authenticating user by nodeUserId : "+ e);
                              //TODO
                              //Set any error messages in session (session may not be there at this point, not sure how it works in portal)  to show on homepage when login fails.
								//response.sendRedirect("public/homePage.jsf");	
							response.sendRedirect(portalHomeURL); 
                        }

						break;
					case LOGOUT:
                        usrSession = request.getSession(false);
                        RSAMLParamsDVO rsamlParamsDVO = null;
                        if ( usrSession != null && usrSession.getAttribute("RSAMLParamsDVO") != null )
                        {
                              rsamlParamsDVO = (RSAMLParamsDVO) usrSession.getAttribute("RSAMLParamsDVO");
                              usrSession.invalidate();
                        }
                        response.sendRedirect(uiReverseSAMLService.getLogoutURLWithQueryString(request.getQueryString(), request.getParameterMap(),rsamlParamsDVO));
			            break;
			   }
			}
			else
            {
				if(logger.isDebugEnabled())   {           
					logger.debug("rSAMLParamsDVO :::" +rSAMLParamsDVO.toString());
				}
                invalidateSession (request);
				//response.sendRedirect("public/homePage.jsf");	
				response.sendRedirect(portalHomeURL);
            }

		}
		else
        {
			if(logger.isDebugEnabled())   {           
				logger.debug("rSAMLParamsDVO is null" );
			}
            invalidateSession (request);
			//response.sendRedirect("public/homePage.jsf");	
			response.sendRedirect(portalHomeURL);
        }

	}

	private FacesContext getFacesContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {

            FacesContextFactory contextFactory  = (FacesContextFactory)FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY); 
            Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

            facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);

            // set a new viewRoot, otherwise context.getViewRoot returns null
            UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
            facesContext.setViewRoot(view);                
        }
        return facesContext;
    }
	
	// You need an inner class to be able to call FacesContext.setCurrentInstance
    // since it's a protected method
    private abstract static class InnerFacesContext extends FacesContext {
        protected static void setFacesContextAsCurrentInstance(FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }   

	private void sendErrorResponseBackToReverseSAML( HttpServletRequest request, HttpServletResponse response)
	{
		// From Pugaz: We need further inputs on this.
		// Praveen: Ignore this for now. If there are any errors, wed don't know
		// where to redirect.
		// For now, we will just invalidate session (if exists)
		invalidateSession(request);
	}

	private void invalidateSession( HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if ( session != null )
		{
			session.removeAttribute("RSAMLParamsDVO");
			session.removeAttribute("SignIn");
			session.invalidate();
		}
	}
	
}
