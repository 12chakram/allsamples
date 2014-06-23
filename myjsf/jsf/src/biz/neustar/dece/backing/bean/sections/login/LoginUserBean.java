/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * LoginUserBean.java
 */
package biz.neustar.dece.backing.bean.sections.login;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.service.UILoginService;
import biz.neustar.dece.ui.service.UIUserService;

/**
 * The <code>LoginUserBean.java</code> class encapsulates objects defined for DECE. 
 * @author CompuGain.
 * @version $Revision: 1.14 $ $Date: 2012/07/06 04:44:45 $
 */
@ManagedBean(name="loginUserBean")
@RequestScoped
public class LoginUserBean {
	private static final Logger loginUserLogger = Logger.getLogger(LoginUserBean.class);
	private String userName;
	private String password;
	private boolean rememberMe;
	private boolean alreadyLoggedIn=false;
	private boolean exceededAtttempts=false;
	private String forErrStatus;
	private FacesContext context = FacesContext.getCurrentInstance();
	SessionUtils sessionUtils=new SessionUtils();
	private HashMap<String,String> errorValueMap;
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	private String forCaptchaRefresh;
	private String captchaId ;
	
	

	
	public String getCaptchaId() {
		return captchaId;
	}
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}
	private String captchaValue;
	
	
	public String getCaptchaValue() {
		return captchaValue;
	}
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
	public LoginUserBean() {
		
		errorValueMap = new HashMap<String,String>();		
		checkCookie();		
		if(null!=session && null!=session.getAttribute("SignIn"))
				alreadyLoggedIn=true;
		captchaId = session.getId();
		if(null!=session && session.getAttribute("pageError") != null){
			if(null!=session.getAttribute("accountLockedErr") && session.getAttribute("accountLockedErr").equals("forAccountLockedError"))
			{
				forErrStatus="accountLocked";
				session.removeAttribute("accountLockedErr");
			}
			else if(null!=session.getAttribute("memberRemovedErr") && session.getAttribute("memberRemovedErr").equals("forMemberRemovedError")){
				forErrStatus="memberRemoved";
				session.removeAttribute("memberRemovedErr");
			}
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
	
	public long getCurrentTime() {
		return new Date().getTime();
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
					HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
					UserSignInDVO signin = service.login(userName.trim(), password.trim(),request.getRemoteHost());
					if(signin != null && signin.getUserName() != null){
						loginUserLogger.info("SignIn success; Privilege : "+signin.getPrivilege()+"; isAcceptedRecentTermsAndConditions: "+signin.getNextPage());
						LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
						loginNavigationBean.setDetailsIntoSession(signin,session);
						if("GB".equalsIgnoreCase(signin.getCountry().toString()))
							sessionUtils.localeSet("en-uk");
						else
							sessionUtils.localeSet("en-us");
						
						if(rememberMe){
							session.setAttribute("password", password.trim());
							session.setAttribute("RememberMe", true);
						}
						errorValueMap.remove("pageError");
					 } else {
						 loginUserLogger.error("SignIn Failed UserSignInDVO Object is null");
					}
				} catch (UIDeceException ex) {
					session.setAttribute("clgUser", userName.trim());
					ExceptionUtils.processUIDECEException("HOMEPAGE_LOGIN","ClassName:LoginUserBean_MethodName:doLogin",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
					Iterator<UIError> itrObj=ex.getErrors().iterator();
				while(itrObj.hasNext())
				{
					UIError uiErrObj = itrObj.next();
					String errorCodeObj = uiErrObj.getErrorCode();
				if(errorCodeObj.equalsIgnoreCase("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS"))
					{
						forErrStatus="ExceededAttempts";
						this.exceededAtttempts=true;
						if(null!= forCaptchaRefresh && forCaptchaRefresh.equals("no"))
							forCaptchaRefresh="doRefresh";
						errorValueMap.put("pageError",errorsBundle.getString("ACCOUNT_USER_EXCEEDED_ALLOWED_FAILED_LOGIN_ATTEMPTS"));
					}
					else if(errorCodeObj.equalsIgnoreCase("ACCOUNT_USER_STATUS_LOCKED"))
					{
						forErrStatus="locked";
						errorValueMap.put("pageError",errorsBundle.getString("ACCOUNT_USER_STATUS_LOCKED"));
					}
			     }
				
             }

			}
		}
		
	}
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
		//captcha validation
		if(exceededAtttempts)
		{
			forErrStatus="";
			HttpServletRequest request = (HttpServletRequest)context. getExternalContext().getRequest();
			String captchaValue = request.getParameter("recaptcha_response_field");
			if(captchaValue==null || "".equals(captchaValue))
			{
				validationFlag=false;
				forCaptchaRefresh="yes";
				errorValueMap.put("captchaError", errorsBundle.getString("CAPTCHA_INPUT_NOT_PROVIDED"));
			}
			else
			{
				RecaptchBean recaptchaBean = new RecaptchBean();
				validationFlag=recaptchaBean.validateRecaptcha();
				if(!validationFlag){
					forCaptchaRefresh="yes";
					errorValueMap.put("captchaError", errorsBundle.getString("CAPTCHA_INPUT_MISMATCH"));
				}
				else
					forCaptchaRefresh="no";
			}
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
              String nodeUserId = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64(session.getAttribute("userId").toString().getBytes())),"UTF-8");
              String loggedClientIp = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64(((UserAuthDVO)session.getAttribute("Auth")).getClientIP().getBytes())),"UTF-8");
              String clientBrowserType = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64(((HttpServletRequest)context.getExternalContext().getRequest()).getHeader("user-agent").getBytes())),"UTF-8");
              //String userNameEncoded = URLEncoder.encode(new String(org.ajax4jsf.util.base64.Base64.encodeBase64(userName.toString().getBytes())),"UTF-8");
              String passwordEncoded = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64(session.getAttribute("password").toString().getBytes())),"UTF-8");
              UserSignInDVO signin = (UserSignInDVO)session.getAttribute("SignIn");
              int maxAgeTime=signin.getCookieExpiryTimeInSeconds();
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
              if("GB".equalsIgnoreCase(signin.getCountry().toString())){
            	  LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
            	  loginNavigationBean.loginAction();
              }
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
                                String nodeUserID = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));
                                if(null!=nodeUserID && !"".equals(nodeUserID)){
                                	UIUserService service= ServiceLocator.getUiUserService();
                                	userName = service.getUserName(nodeUserID);
                                }
                              
                          } else if(cookieName.equals("uvpwd")){
                                password = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));
                          }           
                          else if(cookieName.equals("uvClientIp")){
                                clientIP  = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));                   
                          }     
                          else if(cookieName.equals("uvLoggedBrowser")){
                                clientLoggedBrowser  = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));                   
                          }     
                  }
                    String browserName=((HttpServletRequest)context.getExternalContext().getRequest()).getHeader("user-agent");
                    String clientIpAddress=((HttpServletRequest)context.getExternalContext().getRequest()).getRemoteHost();
                    if(null != userName && null != password && clientIpAddress.equalsIgnoreCase(clientIP) && browserName.equalsIgnoreCase(clientLoggedBrowser)){
                    			//doLogin();
                    		/* LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
                    		 String doLoginReturn  = loginNavigationBean.loginAction();
                          if(doLoginReturn != null && doLoginReturn.equalsIgnoreCase("pendingAccountDashboardPage")){
                                loginUserLogger.info("Redirecting to dashboardPage from login based on valid credentials in cookies");
                                //context.getExternalContext().redirect("../views/pendingAccountDashboardPage.jsf");
                          } else if(doLoginReturn != null && doLoginReturn.equalsIgnoreCase("pendingAccountDashboardPage")){
                        	     loginUserLogger.info("Redirecting to termsConditions from login based on valid credentials in cookies");
                              // context.getExternalContext().redirect("../views/accountDashboardPage.jsf");
                          }*/
                    }
              }
        } catch (Exception e) {
              ExceptionUtils.processException("HOMEPAGE_USERLOGIN", "ClassName:LoginUserBean_MethodName:checkCookie", e, errorValueMap, errorsBundle);
        }
  }
	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}
	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}

	public void setForErrStatus(String forErrStatus) {
		this.forErrStatus = forErrStatus;
	}
	public String getForErrStatus() {
		return forErrStatus;
	}
	public boolean isExceededAtttempts() {
		return exceededAtttempts;
	}
	public void setExceededAtttempts(boolean exceededAtttempts) {
		this.exceededAtttempts = exceededAtttempts;
	}
	public String getForCaptchaRefresh() {
		return forCaptchaRefresh;
	}
	public void setForCaptchaRefresh(String forCaptchaRefresh) {
		this.forCaptchaRefresh = forCaptchaRefresh;
	}
	
	public String authHandler() throws UIDeceException, Exception {
		final ServletContext servContext;
		servContext  = (ServletContext) FacesContext.getCurrentInstance(). getExternalContext().getContext();
		
		if(servContext.getAttribute("shostLogin").equals("true")){
			
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
			try 
			{
				System.out.println("hereeeeeee in authRequest");
				request.getRequestDispatcher("/authRequest").forward(request, response);
			} 

			catch (ServletException e) 
			{
				e.printStackTrace();
				throw new ServletException(e);
			}
			
			catch (IOException e) 
			{
				e.printStackTrace();
				throw new IOException(e);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception(e);
			}
			
		}else {
			return "loginPage";
		}
		return null;
	}
	
}