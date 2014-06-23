package biz.neustar.dece.portal.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.neustar.dece.ui.dvo.RSAMLParamsDVO;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
public class AuthenticationFilter implements Filter {
	//public static final String HOME_URL = "/public/homePage.jsf";
	public static final String AUTH_PUBLIC_CHECK_URL = "public";
	private FilterConfig fConfig;

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean authorized = false;
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		if (httpRequest.getRequestURI().contains(AUTH_PUBLIC_CHECK_URL) || httpRequest.getRequestURI().contains("rfRes") || httpRequest.getRequestURI().contains("javax.faces.resource") ||httpRequest.getRequestURI().contains("resources") || httpRequest.getRequestURI().contains("richfaces") || httpRequest.getRequestURI().contains("ajax4jsf")|| httpRequest.getRequestURI().contains("a4j")
				|| ((httpRequest.getSession(false) != null && httpRequest.getSession(false).getAttribute("User") != null))) {
			authorized = true;
		}
		if (authorized) {
			HttpSession usrSession= httpRequest.getSession(false);
			if(usrSession!=null && usrSession.getAttribute("User")!=null && httpRequest.getRequestURI().contains("/public/homePage.jsf")){
				if(usrSession.getAttribute("toShowPopupOf")==null)
					((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath()+"/views/mediaPage.jsf");
				else
					chain.doFilter(request, response);
			}
			RSAMLParamsDVO rSAMLDVO=null;
			if(null!=usrSession)
				rSAMLDVO = (RSAMLParamsDVO)usrSession.getAttribute("RSAMLParamsDVO");
			if(usrSession!=null && httpRequest.getRequestURI().contains("/views/touPage.jsf"))
			{
				if(null!=rSAMLDVO)
					chain.doFilter(request, response);
				else
					((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath()+"/views/mediaPage.jsf");	
			}
			else
				chain.doFilter(request, response);
		} else {
				if(null!= httpRequest.getParameter("visitfrom") && httpRequest.getParameter("visitfrom").equalsIgnoreCase("email"))
				{
					String portalHomeURL = (String)httpRequest.getSession().getServletContext().getAttribute("portalglobalUrl");
					
					if(null!=portalHomeURL)
					{
						((HttpServletResponse) response).sendRedirect(portalHomeURL);
						
					}
				}else if(null!= httpRequest.getParameter("visitfrom") && httpRequest.getParameter("visitfrom").equalsIgnoreCase("xml")){
					String portalGlobalLoginUrl = (String)httpRequest.getSession().getServletContext().getAttribute("portalGlobalLoginUrl");
					
					if(null!=portalGlobalLoginUrl)
					{
						((HttpServletResponse) response).sendRedirect(portalGlobalLoginUrl);
						
					}
				}
				else
				{
			((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath()+"/public/expiryPage.jsf");
				}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		if (fConfig != null) {
			// fConfig.getInitParameter("count");
		}
	}

}
