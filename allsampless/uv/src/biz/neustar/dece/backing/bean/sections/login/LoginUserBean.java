//LoginUserBean.java

/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * LoginUserBean.java
 */
package biz.neustar.dece.backing.bean.sections.login;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.service.UILoginService;
import biz.neustar.dece.ui.service.UIUserService;

/**
 * The <code>LoginUserBean.java</code> class encapsulates objects defined for DECE. 
 * @author Compugain.
 * @version $Revision: 1.42 $ $Date: 2011/08/25 20:56:27 $
 */

public class LoginUserBean {
    private static final Logger loginUserLogger = Logger.getLogger(LoginUserBean.class);
	private String userName;
	private String password;
	private boolean rememberMe;
	private FacesContext context = FacesContext.getCurrentInstance();
	SessionUtils sessionUtils=new SessionUtils();
	private HashMap<String,String> errorValueMap;
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
   	
	public LoginUserBean() {
		errorValueMap = new HashMap<String,String>();
		checkCookie();
		if(null!=session && session.getAttribute("pageError") != null){
			errorValueMap.put("pageError", errorsBundle.getString(session.getAttribute("pageError").toString()));
			session.removeAttribute("pageError");
		}
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	
	/**
	 * Login Credential Check and Navigation control 
	 * @return navigation string.
	 */
	public void doLogin() {	
		if (validateFields()) {
			UILoginService service = ServiceLocator.getUiLoginService();
			if (service != null && userName != null && password != null) {
				try {
					if(session!=null && null==session.getAttribute("CLG") && null==session.getAttribute("CLD")){
						if(!session.isNew()){
							session.invalidate();
							session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
						}
					}
					UserSignInDVO signin = service.login(userName.trim(), password.trim());
					if(signin != null && signin.getUserName() != null){
						loginUserLogger.info("SignIn success; Privilege : "+signin.getPrivilege()+"; isAcceptedRecentTermsAndConditions: "+signin.getNextPage());
						setDetailsIntoSession(signin);
						if("UK".equalsIgnoreCase(signin.getCountry().toString()))
							sessionUtils.localeSet("en-uk");
						else
							sessionUtils.localeSet("en-us");
						session.setAttribute("password", password.trim());
						if(rememberMe)
							session.setAttribute("RememberMe", true);
						errorValueMap.remove("pageError");
					 } else {
						 loginUserLogger.error("SignIn Failed UserSignInDVO Object is null");
					}
				} catch (UIDeceException ex) {
					session.setAttribute("clgUser", userName);
					ExceptionUtils.processUIDECEException("HOMEPAGE_LOGIN","ClassName:LoginUserBean_MethodName:doLogin",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				}
			}
		}
	}
	/**
	 * perform Login action
	 * @return String
	 */
	/*public String loginAction(){
				UserSignInDVO signin = (UserSignInDVO)sessionUtils.getSession().getAttribute("SignIn");
				ChildYouthUserDVO firstUser=null;
				String userId=null;
				String userDisplayName="";
				if(null!=session && null!=session.getAttribute("RememberMe") && "true".equalsIgnoreCase(session.getAttribute("RememberMe").toString())){
					if("UK".equalsIgnoreCase(signin.getCountry().toString())){
						sessionUtils.getSession().setAttribute("toShowPopupOf", "COOKIE_REMEMBERME");
						return "pendingAccountDashboardPage";
					}
					else{
						addToCookie();
					}
				}
				if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD))
					return "accountDashboardPage";
				else if (signin.getNextPage().equals(UINextPageEnum.TOU_SELF)){
					sessionUtils.getSession().setAttribute("toShowPopupOf", "TOU_SELF");
					/*String status=signin.getAccountStatus().toString();
					if("PENDING".equalsIgnoreCase(status)){
						sessionUtils.getSession().setAttribute("toShowPopupOf", "PENDING_TOU_SELF");
						return "pendingAccountDashboardPage";
					}else*/
						/*return "pendingAccountDashboardPage";
				}else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_SECURITY_QUESTIONS)){
					sessionUtils.getSession().setAttribute("toShowPopupOf", "SECRET_QUESTIONS");
					AccountDashboardBean accuntDashBoard=new AccountDashboardBean();
					accuntDashBoard.getUserDetailsForQuestions();
					return "pendingAccountDashboardPage";
				}
				else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_TOU_OR_COPPA_ON_BEHALF_OF_CHILD_OR_YOUTH)){
					List<ChildYouthUserDVO> childYouthList=signin.getChildYouthUserList();
					if(null!=childYouthList){
						if((null==session.getAttribute("CLG"))&& (null==session.getAttribute("CLD"))){
							firstUser=childYouthList.get(0);
							userDisplayName=firstUser.getDisplayName();
							userId=firstUser.getNodeUserId();
						}else{
							String CLD = sessionUtils.getSession().getAttribute("CLD").toString();
							sessionUtils.getSession().removeAttribute("CLD");
							sessionUtils.getSession().removeAttribute("CLG");
							Iterator<ChildYouthUserDVO> itr = childYouthList.iterator();
							while(itr.hasNext())
							{
								firstUser=itr.next();
								if(firstUser.getNodeUserId().contains(CLD))
								{
									userDisplayName=firstUser.getDisplayName();
									userId=firstUser.getNodeUserId();
									session.setAttribute("ChildCoppaAccepted","NO");
								}
							}
						}
						if(null==sessionUtils.getSession().getAttribute("ADMIN_POPUP")){
							sessionUtils.getSession().setAttribute("ADMIN_POPUP", "YES");
							sessionUtils.getSession().setAttribute("toShowPopupOf", "ADMIN_POPUP");
						}else if(firstUser.isEulaAcceptanceRequired())
							sessionUtils.getSession().setAttribute("toShowPopupOf", "TOU_OTHERS");
						else if (firstUser.isCoppaAcceptanceRequired())
							sessionUtils.getSession().setAttribute("toShowPopupOf", "COPPA_OTHERS");
						sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);
						sessionUtils.getSession().setAttribute("userNodeId", userId);
					}
					return "pendingAccountDashboardPage";
				}else if (signin.getNextPage().equals(UINextPageEnum.HOSTAGE))
					return "hostagePage";
				else if (signin.getNextPage().equals(UINextPageEnum.DASHBOARD_EMAIL_NOT_VERIFIED)){
					if(null==sessionUtils.getSession().getAttribute("ADMIN_POPUP")){
						sessionUtils.getSession().setAttribute("toShowPopupOf", "EMAIL_CONFIRMATION_PENDING");
						sessionUtils.getSession().setAttribute("DASHBOARD_EMAIL_EXP_TIME", signin.getDisplayEmailVerificationExpiryDate());
					}
					return "pendingAccountDashboardPage";
				}
		return null;
	}*/
	
	/**
	 * Validates Login Fields
	 * @return boolean
	 */
	private boolean validateFields() {
		boolean validationFlag = true;
		if(userName.trim() == null || "".equals(userName.trim()) || password.trim() == null || "".equals(password.trim())) {
			validationFlag = false;
			errorValueMap.put("pageError", errorsBundle.getString("invalidCredentialsError"));
		}
		return validationFlag;
	}

	/**
	 * setting the  cookie values 
	 * @return void
	 */
	public void addToCookie(){
        try {
        	  String cookieName = null;
              HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
              String nodeUserId = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(session.getAttribute("userId").toString().getBytes())),"UTF-8");
              String loggedClientIp = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(((UserAuthDVO)session.getAttribute("Auth")).getClientIP().getBytes())),"UTF-8");
              String clientBrowserType = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(((HttpServletRequest)context.getExternalContext().getRequest()).getHeader("user-agent").getBytes())),"UTF-8");
              //String userNameEncoded = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(userName.toString().getBytes())),"UTF-8");
              String passwordEncoded = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(session.getAttribute("password").toString().getBytes())),"UTF-8");
              UserSignInDVO signin = (UserSignInDVO)sessionUtils.getSession().getAttribute("SignIn");
              int maxAgeTime=signin.getCookieExpiryTimeInSeconds()/3600;
              Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();
              for(int i = 0; i<cookie.length; i++){
            	  cookieName = cookie[i].getName();
            	  if(cookieName.equals("uvNodeUserId")){
            		 cookie[i].setValue(nodeUserId);
            		 cookie[i].setMaxAge(maxAgeTime);
            		 cookie[i].setPath("/");
            		 response.addCookie(cookie[i]);
            	  }else{
            		  Cookie userIdCookie = new Cookie("uvNodeUserId", nodeUserId);
            		  userIdCookie.setMaxAge(maxAgeTime);
    	              userIdCookie.setPath("/");
    	              response.addCookie(userIdCookie);
            	  }
	             
	              if(cookieName.equals("uvClientIp")){
	            	  cookie[i].setValue(loggedClientIp); 
	            	  cookie[i].setMaxAge(maxAgeTime);
	            	  cookie[i].setPath("/");
	            	  response.addCookie(cookie[i]);
	              }else{
	            	  Cookie clientIp = new Cookie("uvClientIp", loggedClientIp);
	            	  clientIp.setMaxAge(maxAgeTime);
	 	              clientIp.setPath("/");
	 	              response.addCookie(clientIp);
	              }
	              
	              if(cookieName.equals("uvLoggedBrowser")){
	            	  cookie[i].setValue(clientBrowserType); 
	            	  cookie[i].setMaxAge(maxAgeTime);
	            	  cookie[i].setPath("/");
	            	  response.addCookie(cookie[i]);
	              }else{
	            	  Cookie loggedBrowsertype = new Cookie("uvLoggedBrowser", clientBrowserType);
	            	  loggedBrowsertype.setMaxAge(maxAgeTime);
	            	  loggedBrowsertype.setPath("/");
	 	              response.addCookie(loggedBrowsertype);
	              }
	              
	              if(cookieName.equals("uvpwd")){
	            	  cookie[i].setValue(passwordEncoded); 
	            	  cookie[i].setMaxAge(maxAgeTime);
	            	  cookie[i].setPath("/");
	            	  response.addCookie(cookie[i]);
	              }else{
	            	  Cookie passwordCookie = new Cookie("uvpwd", passwordEncoded);
	            	  passwordCookie.setMaxAge(maxAgeTime);
	            	  passwordCookie.setPath("/");
	 	              response.addCookie(passwordCookie);
	              }
              }
              session.removeAttribute("password");
              session.removeAttribute("RememberMe");
              LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
              loginNavigationBean.loginAction();
              loginUserLogger.info("Added the Login encoded credentials to Cookies");               
        } catch (Exception e) {
              ExceptionUtils.processException("HOMEPAGE_USERLOGIN", "ClassName:LoginUserBean_MethodName:addToCookie", e, errorValueMap, errorsBundle);
        }
	}
	
	public void removeFromSession(){
        try {
              session.removeAttribute("RememberMe");
              session.removeAttribute("password");
              LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
              loginNavigationBean.loginAction();
        } catch (Exception e) {
              ExceptionUtils.processException("HOMEPAGE_USERLOGIN", "ClassName:LoginUserBean_MethodName:removeFromSession", e, errorValueMap, errorsBundle);
        }
	}

	/**
	 * Checks Cookies for Remember Me
	 * @return
	 */
		private void checkCookie(){
        try { 
              String cookieName = null;
              String clientIP = null;
              String clientLoggedBrowser=null;
              Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();
          	FacesContext context = FacesContext.getCurrentInstance();
              if(cookie != null && cookie.length > 0){
                    for(int i = 0; i<cookie.length; i++){
                          cookieName = cookie[i].getName();
                          if(cookieName.equals("uvNodeUserId")){
                                String nodeUserID = new String(org.ajax4jsf.util.base64.Base64.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));
                                 UIUserService service= ServiceLocator.getUiUserService();
                                userName =service.getUserName(nodeUserID);
                              
                          } else if(cookieName.equals("uvpwd")){
                                password = new String(org.ajax4jsf.util.base64.Base64.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));                   
                          }           
                          else if(cookieName.equals("uvClientIp")){
                                clientIP  = new String(org.ajax4jsf.util.base64.Base64.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));                   
                          }     
                          else if(cookieName.equals("uvLoggedBrowser")){
                                clientLoggedBrowser  = new String(org.ajax4jsf.util.base64.Base64.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));                   
                          }     
                  }
                    String browserName=((HttpServletRequest)context.getExternalContext().getRequest()).getHeader("user-agent");
                    String clientIpAddress=((HttpServletRequest)context.getExternalContext().getRequest()).getRemoteHost();
                    if(null != userName && null != password && clientIpAddress.equalsIgnoreCase(clientIP) && browserName.equalsIgnoreCase(clientLoggedBrowser)){
                   
                    			//doLogin();
                    		 LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
                    		 String doLoginReturn  = loginNavigationBean.loginAction();
                          if(doLoginReturn != null && doLoginReturn.equalsIgnoreCase("pendingAccountDashboardPage")){
                                loginUserLogger.info("Redirecting to dashboardPage from login based on valid credentials in cookies");
                                //context.getExternalContext().redirect("../views/pendingAccountDashboardPage.jsf");
                          } else if(doLoginReturn != null && doLoginReturn.equalsIgnoreCase("pendingAccountDashboardPage")){
                        	     loginUserLogger.info("Redirecting to termsConditions from login based on valid credentials in cookies");
                              // context.getExternalContext().redirect("../views/accountDashboardPage.jsf");
                          }
                    }
              }
        } catch (Exception e) {
              ExceptionUtils.processException("HOMEPAGE_USERLOGIN", "ClassName:LoginUserBean_MethodName:checkCookie", e, errorValueMap, errorsBundle);
        }
  }


	
	/**
	 * Add Login details to Cookie
	 * @param UserSignInDVO
	 */
	private void setDetailsIntoSession(UserSignInDVO signin){
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String remoteHost = request.getRemoteHost();
		request = null;
		UserAuthDVO auth = new UserAuthDVO();
		auth.setClientIP(remoteHost);
		loginUserLogger.info("IP Address: "+remoteHost);
		auth.setNodeAccountId(signin.getNodeAccountId());
		auth.setNodeUserId(signin.getNodeUserId());		
		//create the required attributes in session
		session.setAttribute("householdName", signin.getAccountName());
		session.setAttribute("User", signin.getUserName());
		session.setAttribute("userId", signin.getNodeUserId());
		session.setAttribute("userAge", signin.getUserAgeClass().toString());
		session.setAttribute("privilege", signin.getPrivilege());
		session.setAttribute("country", signin.getCountry().toString());
		session.setAttribute("Auth", auth);
		session.setAttribute("SignIn", signin);
	}
}

