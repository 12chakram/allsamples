/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * UserLogout.java
 */
package biz.neustar.dece.backing.bean;

import java.io.IOException;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.neustar.dece.portal.utils.SessionUtils;

/**
 * The <code>UserLogout.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.3 $ $Date: 2012/04/20 09:49:33 $
 */
@ManagedBean(name="logout")
@RequestScoped
public class UserLogout {	
	private String logoutMessage;
	SessionUtils sessionUtils=new SessionUtils();
	HttpSession session;
	
	public UserLogout(){
		try {							
				FacesContext context=FacesContext.getCurrentInstance();	
				removeFromCookie();
				HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
				if(session != null && session.getAttribute("User") != null)
					session.invalidate();
				
				if(context.getExternalContext().getRequestParameterMap().get("errorCode") != null)
				{				
					HttpSession session1 = (HttpSession) context. getExternalContext().getSession(true);
					if(null!=context.getExternalContext().getRequestParameterMap().get("errorCode") && context.getExternalContext().getRequestParameterMap().get("errorCode").equalsIgnoreCase("ACCOUNT_CLOSED"))
					{
						session1.setAttribute("accountLockedErr", "forAccountLockedError");
						session1.setAttribute("pageError", context.getExternalContext().getRequestParameterMap().get("errorCode"));
					}else if(null!=context.getExternalContext().getRequestParameterMap().get("errorCode") && context.getExternalContext().getRequestParameterMap().get("errorCode").equalsIgnoreCase("SELF_MEMBER_REMOVED")){
						session1.setAttribute("memberRemovedErr", "forMemberRemovedError");
						session1.setAttribute("pageError", context.getExternalContext().getRequestParameterMap().get("errorCode"));
					}
					
					else{
						session1.setAttribute("pageError", context.getExternalContext().getRequestParameterMap().get("errorCode"));
					}
					if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl") && null!=context.getExternalContext().getApplicationMap().get("portalGlobalLoginUrl"))
					{
						if(null!=context.getExternalContext().getRequestParameterMap().get("errorCode") && (context.getExternalContext().getRequestParameterMap().get("errorCode").equalsIgnoreCase("ACCOUNT_CLOSED") || context.getExternalContext().getRequestParameterMap().get("errorCode").equalsIgnoreCase("SELF_MEMBER_REMOVED"))){
							String portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalGlobalLoginUrl");
							context.getExternalContext().redirect(portalHomeURL);
						}else{
							//PortalGlobalUrls portalGlobal = (PortalGlobalUrls) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
							String portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
							context.getExternalContext().redirect(portalHomeURL);
						}
						
					}
				}
				else
				{
					if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl")){
						/*PortalGlobalUrls portalGlobal = (PortalGlobalUrls) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
						context.getExternalContext().redirect(portalGlobal.getHomePageURL());*/
						String portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
						context.getExternalContext().redirect(portalHomeURL);
						
					}
				}
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
/**
 * Jan 18,2011.
 * Remove cookie method.
 */
	private void removeFromCookie(){
		try {	
			String cookieName = null;
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();			
			if(cookie != null && cookie.length > 0){
				for(int i = 0; i<cookie.length; i++){
					cookieName = cookie[i].getName();			
					if(cookieName.equals("uvNodeUserId")){
						response.setContentType("text/html");
						cookie[i].setMaxAge(0);
						cookie[i].setValue("");
						cookie[i].setPath("/");
						response.addCookie(cookie[i]);
						
					}else if(cookieName.equals("uvpwd")){
						cookie[i].setMaxAge(0);
						cookie[i].setValue("");
						cookie[i].setPath("/");
						response.addCookie(cookie[i]);
					
					}
					else if(cookieName.equals("uvClientIp")){
						cookie[i].setMaxAge(0);
						cookie[i].setValue("");
						cookie[i].setPath("/");
						response.addCookie(cookie[i]);
						
					}
					else if(cookieName.equals("uvLoggedBrowser")){
						cookie[i].setMaxAge(0);
						cookie[i].setValue("");
						cookie[i].setPath("/");
						response.addCookie(cookie[i]);
						
					}
			    }
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public String getLogoutMessage() {
		return logoutMessage;
	}

	public void setLogoutMessage(String logoutMessage) {
		this.logoutMessage = logoutMessage;
	}
}
