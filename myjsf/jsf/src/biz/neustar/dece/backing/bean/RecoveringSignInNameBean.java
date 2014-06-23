package biz.neustar.dece.backing.bean;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.AmbiguityUsers;
import biz.neustar.dece.backing.bean.sections.login.LoginNavigationBean;
import biz.neustar.dece.backing.bean.sections.login.RecaptchBean;
import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.domain.portal.EmailAmbiguityUsers;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;
import biz.neustar.dece.ui.enumeration.UIUserAgeClass;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;
import biz.neustar.dece.ui.service.UIUserService;


/**
 * The <code>RecoveringSignInNameBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version	$Revision: 1.7 
 */
@ManagedBean(name="recoveringSignInNameBean")
@RequestScoped
public class RecoveringSignInNameBean {
	
	private static final Logger logHandler = Logger.getLogger( RecoveringSignInNameBean.class);
	private String emailAddress;
	private String selectedUserName;
	private String emailCode;
	private String newPassword;
	private String confirmNewPassword;
	private boolean isValid;
	private ResourceBundle errorsBundle;
	private HashMap<String,String> errorValueMap;
	private boolean forStatus;
	//private EmailAmbiguityUsers emailAmbiguityUsers;
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
	FacesContext context = FacesContext.getCurrentInstance();
	private Map<String,List<EmailAmbiguityUsers>> userNamesMap;
	private List<AmbiguityUsers> ambiguityUsersList;
	private boolean confirmResendEmail;
	private boolean forResendStatus;
	private int countValue=0;
	private boolean cookieCheck;

	
	public boolean isConfirmResendEmail() {
		return confirmResendEmail;
	}

	public void setConfirmResendEmail(boolean confirmResendEmail) {
		this.confirmResendEmail = confirmResendEmail;
	}

	public String getSelectedUserName() {
		return selectedUserName;
	}

	public void setSelectedUserName(String selectedUserName) {
		this.selectedUserName = selectedUserName;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	public RecoveringSignInNameBean()
	{
		SessionUtils sessionVars = new SessionUtils();
		if(null!=sessionVars)
		{
			errorsBundle  = sessionVars.getErrorTxtsBundle();
			errorValueMap = new HashMap<String,String>();
			if(null!=sessionVars.getSession() && null!=sessionVars.getSession().getAttribute("emailUsers"))
				setAmbiguityUsersList((List<AmbiguityUsers>)sessionVars.getSession().getAttribute("emailUsers"));
		}	
		checkCookie();
	}
	
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
		
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	// this method is called when the user enters the email address and that email address have multiple account
	public String submitRecoveringSignIn(){

		boolean token=false;
		if(validateValues()){
			try{
				logHandler.info("Recovering Sign-in Name - Post submitted Values, to invoke Service API");
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				token=ServiceLocator.getUiLoginService().forgotPassword(emailAddress.trim(),request.getRemoteHost());
				logHandler.info("Recovering Sign-In - Service API request to generate token, successful");
				if(token){
					session.setAttribute("recoveryEmailAddress",emailAddress.trim());
					if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sendAgain")!=null){
						forResendStatus=true;
					}
					if(null!=session && null!=session.getAttribute("countValue"))
						session.removeAttribute("countValue");
					return "confirmMyDetailsPage";
				}
			}catch(UIDeceException exception){
				if(!cookieCheck)
				{
					addCookie();
					cookieCheck=true;
				}
				else
				{
					checkCookie();
					if(countValue<3)
						addCookie();
				}
				ExceptionUtils.processUIDECEException("RECOVERING SIGN-IN NAME","ClassName:RecoveringSignInNameBean_MethodName:recoveringSignIn",exception, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				Iterator<UIError> itrObj=exception.getErrors().iterator();
				String errorCodeObj="";
				while(itrObj.hasNext())
				{
					UIError uiErrObj = itrObj.next();
					errorCodeObj = uiErrObj.getErrorCode();
				}
				if(errorCodeObj.equalsIgnoreCase("UNEXPECTED_ERROR"))
					{
						errorValueMap.put("pageError", errorsBundle.getString("recoverySigninErr"));
					}
				}
		}
		return null;
	}
	// this method is called for validating when the user didn't enter email address  
	private boolean validateValues(){
		isValid = true;
		logHandler.info("Recovering Sign-In - Call to validate submitted values");
		if("".equals(emailAddress.trim()) || null==emailAddress){
			errorValueMap.put("emailAddressError",errorsBundle.getString("memberEmailReq"));	
			isValid = false;
		}
		errorValueMap.put("emailAddressError", CommonValidations.emailValidation(getEmailAddress().trim()));
        if(!"".equals(errorValueMap.get("emailAddressError"))){
              isValid = false;
              return false;
        }else{
        	errorValueMap.remove("emailAddressError");
        }
        if(forStatus)
		{
			HttpServletRequest request = (HttpServletRequest)context. getExternalContext().getRequest();
			String captchaValue = request.getParameter("recaptcha_response_field");
			if(captchaValue==null || "".equals(captchaValue))
			{
				isValid=false;
				errorValueMap.put("captchaError", errorsBundle.getString("CAPTCHA_INPUT_NOT_PROVIDED"));
			}
			else
			{
				RecaptchBean recaptchaBean = new RecaptchBean();
				isValid=recaptchaBean.validateRecaptcha();
				if(!isValid){
					errorValueMap.put("captchaError", errorsBundle.getString("CAPTCHA_INPUT_MISMATCH"));
				}
			}
		}
		return isValid;
	}
	
	
	
	public boolean resendEmail(){
		try{
			confirmResendEmail=ServiceLocator.getUiUtilService().resendEmail(emailAddress.trim(), UIUserVerificationTokenType.FORGOT_PASSWORD);
			if(!confirmResendEmail)
			{
			errorValueMap.put("pageError",errorsBundle.getString("emailResendForExisted"));
			}
		}
		catch(UIDeceException ex)
		{
			ExceptionUtils.processUIDECEException("LoggedUser_RESENDEMAIL","ClassName:HeaderBean_MethodName:resendEmail",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return confirmResendEmail;
	}
	
	
	// if the user didn't selected any one of the radio button error is displayed
	private boolean validateEmailDisambiguation()
    {
          boolean ischecked=true;
          
          if("".equals(selectedUserName)){
                errorValueMap.put("selectedUserNameError",errorsBundle.getString("select_membership")); 
                ischecked=false;
          }
          return ischecked;
    }
    
    //this method is called after the user selects one of the radio button and click on the continue button.....
    public String submitEmailDisambiguation()
    {
          logHandler.info("RecoveringSignInName------Control goes to sendEmailPage---" +selectedUserName);
          if(validateEmailDisambiguation())
          {
	 				 if(session!=null && session.getAttribute("emailToken")!=null)
					 {
						 session.setAttribute("selectedUsrNodeId",selectedUserName);
						 if(null!=session.getAttribute("emailAmbiguityUsers"))
						 {
							 List<EmailAmbiguityUsers> emailAmbigutyUserListObj=(List<EmailAmbiguityUsers>) session.getAttribute("emailAmbiguityUsers");
							 if(!emailAmbigutyUserListObj.isEmpty())
							 {
								 for (Iterator iterator = emailAmbigutyUserListObj.iterator(); iterator.hasNext();) {
									EmailAmbiguityUsers emailAmbiguityUserObj = (EmailAmbiguityUsers) iterator.next();
									if(emailAmbiguityUserObj.getNodeUserId().equals(session.getAttribute("selectedUsrNodeId")))
									{
										if(emailAmbiguityUserObj.getUserAgeClass().equals(UIUserAgeClass.YOUTH))
										{
											HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
											UserAuthDVO  auth=new UserAuthDVO();
											auth.setClientIP(request.getRemoteHost());
											auth.setInternalAccountId(emailAmbiguityUserObj.getInternalAccountId());
											auth.setNodeAccountId(emailAmbiguityUserObj.getNodeAccountId());
											auth.setNodeUserId(emailAmbiguityUserObj.getNodeUserId());
											auth.setSessionId(session.getId());
											try {
													boolean isAccepted=ServiceLocator.getUiUserService().isLatestTermsAndConditionsAccepted(auth);
													if(isAccepted)
													{
														session.setAttribute("givenName", emailAmbiguityUserObj.getFirstName());
														if(null==emailAmbiguityUserObj.getSurName())
															session.setAttribute("surname",null);
															else
																session.setAttribute("surname",emailAmbiguityUserObj.getSurName());
														return "createNewPasswordPage";
													}
													else
													{
														 session.setAttribute("clgUser", emailAmbiguityUserObj.getFirstName().trim());
									                   	 session.setAttribute("dispFor","TOU_NOT_ACCEPTED_BY_CLG");
									                   	try
									                   	{
									                   		context.getExternalContext().redirect("../public/pendingUserMessagePage.jsf");
									                   	}
									                   	catch (IOException e) {
															e.printStackTrace();
														}
													}
												} 
											catch (UIDeceException e)
											{
												 ExceptionUtils.processUIDECEException("RECOVERING SIGN-IN NAME","ClassName:RecoveringSignInNameBean_MethodName:submitEmailDisambiguation",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
											}
											
										}
										else
										{
											session.setAttribute("givenName", emailAmbiguityUserObj.getFirstName());
											if(null==emailAmbiguityUserObj.getSurName())
											session.setAttribute("surname",null);
											else
												session.setAttribute("surname",emailAmbiguityUserObj.getSurName());
											 return "createNewPasswordPage";
										}
									}
									
								}
							 }
							 
						 }
	        	  }
	 				 else
	 				 {
	 					 LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
	 					 loginNavigationBean.navigateToExpiryPage();
	 				 }
        	}
        return null;
    }
   
	public void setForResendStatus(boolean forResendStatus) {
		this.forResendStatus = forResendStatus;
	}

	public boolean isForResendStatus() {
		return forResendStatus;
	}
	
	public String confirmResendEmailAction() {
		return "homePage";
	}
	public String confirmContinueAction() {
		return "homePage";
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

    
    
    public void navigateToHomePage(){
        String portalHomeURL=null;
        FacesContext context=FacesContext.getCurrentInstance();
        if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl"))
        {
              portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
              try {
                    context.getExternalContext().redirect(portalHomeURL);
              } catch (IOException e) {
                    e.printStackTrace();
              }
        }
  }
  
  /**
  * method to show the home page
  * 
   */
  public void navigationToUnauthHomePage(){
        
		if(session!=null)
              session.invalidate();
        navigateToHomePage();
  }


  /**
  * method to show the Credential Recovery page
  * 
   */
  public void navigationToUnauthCredentialRecoveryPage(){
        if(session!=null)
              session.invalidate();
        navigateToCredentialRecoveryPage();
  }

	  public void navigateToCredentialRecoveryPage()
	  {
	        try{
	              FacesContext context=FacesContext.getCurrentInstance();
	              context.getExternalContext().redirect("../public/credentialRecoveryPage.jsf");
	                    }
	        catch (IOException ioe) {
	              if(logHandler.isDebugEnabled())   {           
	                    logHandler.debug(ioe.getMessage());
	              }
	        }
	  }
 
 
// this method is called when the user calls create new password page  and the new password is set for the user 
 	public String submitCreateNewPWD(){
	 if(validateNewPassword()){
		 try{
			 HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			 if(session!=null)
			 {
				 UserSignInDVO userSignInDVO; 
				 if(session.getAttribute("emailToken")!=null && session.getAttribute("selectedUsrNodeId")!=null && null!=session.getAttribute("givenName"))
				 {
					 if(null==session.getAttribute("surname"))
						 userSignInDVO=ServiceLocator.getUiLoginService().resetPassword(session.getAttribute("selectedUsrNodeId").toString(),session.getAttribute("emailToken").toString(),session.getAttribute("givenName").toString(),"null",newPassword,request.getRemoteHost());
					 else
						  userSignInDVO=ServiceLocator.getUiLoginService().resetPassword(session.getAttribute("selectedUsrNodeId").toString(),session.getAttribute("emailToken").toString(),session.getAttribute("givenName").toString(),session.getAttribute("surname").toString(),newPassword,request.getRemoteHost()); 
					 if(userSignInDVO!=null)
					 {
						 session.removeAttribute("emailToken");
						 session.removeAttribute("emailAmbiguityUsers");
						 if(null!=session.getAttribute("emailUsers"))
			       			  session.removeAttribute("emailUsers");
						 session.removeAttribute("givenName");
						 session.removeAttribute("surname");
						 session.setAttribute("tokenRST", "yes");
						 session.setAttribute("targetURL", "RESETPWD");
	                     LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
	                     loginNavigationBean.setDetailsIntoSession(userSignInDVO,session);
	                     if(userSignInDVO.getNextPage().equals(UINextPageEnum.DASHBOARD))
	                     {
	                    	 session.setAttribute("fromReset", "true");
		                     session.setAttribute("currUserViewId", userSignInDVO.getNodeUserId());
	                         try {
	                              context.getExternalContext().redirect("../views/userProfilePage.jsf");
	                         } catch (IOException e) {
	                              e.printStackTrace();
	                         } 
	                     }
	                     else if(!"ADULT".equalsIgnoreCase(userSignInDVO.getUserAgeClass().toString()) && userSignInDVO.getNextPage().equals(UINextPageEnum.TOU_SELF)){
	                    	 session.setAttribute("clgUser", userSignInDVO.getUserName().trim());
	                    	 session.setAttribute("dispFor","TOU_NOT_ACCEPTED_BY_CLG");
	                    	 try{
	           	              		context.getExternalContext().redirect("../public/pendingUserMessagePage.jsf");
	           	             }catch (IOException ioe) {
		           	              if(logHandler.isDebugEnabled())   {           
		           	                    logHandler.debug(ioe.getMessage());
		           	              }
	           	             }
	                     }                 	 
	                     else
	                     {
	                    	 session.setAttribute("currUserViewId", userSignInDVO.getNodeUserId());
		                     String retpath=loginNavigationBean.loginAction();
		                     if(null!=retpath)
		                     {
		                    	 return retpath;			                     
		                     }
	                     }
	
					 }
				 }
			 else if(null!=session.getAttribute("username") && null!=session.getAttribute("token"))
				 {
					 userSignInDVO= ServiceLocator.getUiLoginService().resetPassword(session.getAttribute("username").toString(),session.getAttribute("token").toString(),newPassword, request.getRemoteHost());
					 if(userSignInDVO!=null)
					 {
						 session.removeAttribute("username");
						 session.removeAttribute("token");
	                     LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
	                     loginNavigationBean.setDetailsIntoSession(userSignInDVO,session);
	                     
	                     String returnPath=loginNavigationBean.loginAction();
	                     if("mediaSection".equalsIgnoreCase(returnPath)){
	                                     try {
	                                                     context.getExternalContext().redirect("../views/mediaPage.jsf");
	                                     } catch (IOException e) {
	                                                     e.printStackTrace();
	                                     }
	                     }else if("pendingAccountDashboardPage".equalsIgnoreCase(returnPath)){
	                                     try {
	                                                     context.getExternalContext().redirect("../views/pendingAccountDashboardPage.jsf");
	                                     } catch (IOException e) {
	                                                     e.printStackTrace();
	                                     }
	                     }
	
					 }
				 }
			 }
			 else
			 {
				 LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				 loginNavigationBean.navigateToExpiryPage();
			 }
		 }
		 catch(UIDeceException exception){
			 ExceptionUtils.processUIDECEException("RECOVERING SIGN-IN NAME","ClassName:RecoveringSignInNameBean_MethodName:submitCreateNewPWD",exception, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			 Iterator<UIError> itrObj=exception.getErrors().iterator();
				while(itrObj.hasNext())
				{
					UIError uiErrObj = itrObj.next();
					String errorCodeObj = uiErrObj.getErrorCode();
					String errorDesc=uiErrObj.getErrorDescription();
					if(errorCodeObj.equalsIgnoreCase("ACCOUNT_USER_PASSWORD_NOT_VALID") || errorDesc.contains("ACCOUNT_USER_PASSWORD_NOT_VALID")){
						errorValueMap.put("passwordError", errorsBundle.getString("password_contains_account_info"));
						errorValueMap.remove("pageError");
					}
				}
		 }
	 }
	 return null;
 	}
 
 //for validating the new password when the user request for creating new password
 	private boolean validateNewPassword(){
		isValid = true;
		logHandler.info("Create New Password Method - Call to validate submitted values");
		
		//validate new Password
		if(null==newPassword || "".equals(newPassword.trim())){
			errorValueMap.put("passwordError",errorsBundle.getString("pass_not_entered"));	
			isValid = false;
		}else if(null==confirmNewPassword || "".equals(confirmNewPassword.trim())){
			errorValueMap.put("confirmPasswordError",errorsBundle.getString("confirm_password_not_entered_reenter"));	
			isValid = false;
		}else if(newPassword.equals(confirmNewPassword)){
			errorValueMap.put("passwordError",CommonValidations.passwordValidation(newPassword));
			if (null!=errorValueMap.get("passwordError") && !"".equals(errorValueMap.get("passwordError").toString()))
				isValid=false;
			else
				errorValueMap.remove("passwordError");			
		}
		else{
			errorValueMap.put("confirmPasswordError",errorsBundle.getString("Password_confirm_password_not_same"));
			isValid = false;
		}
		return isValid;
	}
	
	public String cancel(){		
		return "homePage";
	}
	public void navigationToUnAuthLoginPage(){
	         if(session!=null)
	               session.invalidate();
	         navigationToLoginPage();
	   }

	public void navigationToLoginPage()
	   {
	         try{
	               FacesContext context=FacesContext.getCurrentInstance();
	               context.getExternalContext().redirect("../public/loginPage.jsf");
	               }
	         catch (IOException ioe) {
	               if(logHandler.isDebugEnabled())   {           
	                     logHandler.debug(ioe.getMessage());
	               }
	         }
	   }
	/**
     * method to show the Help page
     * 
      */
     public void navigateToUnAuthHelpPage(){
           try{
                 
                 FacesContext context=FacesContext.getCurrentInstance();
                 context.getExternalContext().redirect("../public/helpPage.jsf");
           }catch (IOException ioe) {
                 if(logHandler.isDebugEnabled())   {           
                       logHandler.debug(ioe.getMessage());
                 }
           }
     }
     

     public void closePopUp()
 	{
 		emailAddress="";
 		forResendStatus = false;
 		try{
 			FacesContext context=FacesContext.getCurrentInstance();
 			context.getExternalContext().redirect("../public/confirmMyDetailsPage.jsf");
 		}catch (IOException ioe) {
 			if(logHandler.isDebugEnabled())   {           
 				logHandler.debug(ioe.getMessage());
 			}
 		}
 	}

     /**
 	 * setting the  cookie values 
 	 * @return void
 	 */
 	public void addCookie(){
         try {
         	  String cookieName = null;
         	  HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
               HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
               String remoteIpObj = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64((request.getRemoteHost().getBytes())),"UTF-8"));
               String countVal = URLEncoder.encode(new String(org.ajax4jsf.util.base64.URL64Codec.encodeBase64((new Integer(countValue).toString().getBytes())),"UTF-8"));
               int maxAgeTime=86400;
               Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();
               for(int i = 0; i<cookie.length; i++){
             	  cookieName = cookie[i].getName();
             	  if(cookieName.equals("uvUserClientIP")){
             		 cookie[i].setValue(remoteIpObj);
             		 cookie[i].setMaxAge(maxAgeTime);
             		 cookie[i].setPath("/");
             		 response.addCookie(cookie[i]);
             	  }else{
             		  Cookie userIdCookie = new Cookie("uvUserClientIP", remoteIpObj);
             		  userIdCookie.setMaxAge(maxAgeTime);
     	              userIdCookie.setPath("/");
     	              response.addCookie(userIdCookie);
             	  }
 	             
 	              if(cookieName.equals("uvFailureCount")){
 	            	  cookie[i].setValue(countVal); 
 	            	  cookie[i].setMaxAge(maxAgeTime);
 	            	  cookie[i].setPath("/");
 	            	  response.addCookie(cookie[i]);
 	              }else{
 	            	  Cookie clientIp = new Cookie("uvFailureCount", countVal);
 	            	  clientIp.setMaxAge(maxAgeTime);
 	 	              clientIp.setPath("/");
 	 	              response.addCookie(clientIp);
 	              }
               }
         } catch (Exception e) {
               ExceptionUtils.processException("RecoveringSignInNameBean", "ClassName:RecoveringSignInNameBean_MethodName:addCookie", e, errorValueMap, errorsBundle);
         }
 	}
 	
 	
 	/**
	 * Checks Cookies for count
	 * @return void
	 */
		private void checkCookie(){
        try { 
              String cookieName = null;
              String remoteIpAddObj = null;
              String  recoveryValObj=null;
              Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();
          	FacesContext context = FacesContext.getCurrentInstance();
              if(cookie != null && cookie.length > 0){
                    for(int i = 0; i<cookie.length; i++){
                          cookieName = cookie[i].getName();
                          if(cookieName.equals("uvUserClientIP")){
                                remoteIpAddObj = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));
                              
                          } else if(cookieName.equals("uvFailureCount")){
                            recoveryValObj = new String(org.ajax4jsf.util.base64.URL64Codec.decodeBase64(URLDecoder.decode(cookie[i].getValue().trim(),"UTF-8").getBytes()));
                          }           
                  }
                    if(((HttpServletRequest)context.getExternalContext().getRequest()).getRemoteHost().equals(remoteIpAddObj))
                    {
                    	countValue=Integer.parseInt(recoveryValObj);
                    	if(countValue<3)
                    		countValue=countValue+1;
                    }
              }
        } catch (Exception e) {
              ExceptionUtils.processException("HOMEPAGE_USERLOGIN", "ClassName:LoginUserBean_MethodName:checkCookie", e, errorValueMap, errorsBundle);
        }
  }
	public Map<String,List<EmailAmbiguityUsers>> getUserNamesMap() {
		return userNamesMap;
	}

	public void setUserNamesMap(Map<String,List<EmailAmbiguityUsers>> userNamesMap) {
		this.userNamesMap = userNamesMap;
	}

	public int getCountValue() {
		return countValue;
	}

	public void setCountValue(int countValue) {
		this.countValue = countValue;
	}

	public boolean isForStatus() {
		return forStatus;
	}

	public void setForStatus(boolean forStatus) {
		this.forStatus = forStatus;
	}

	public List<AmbiguityUsers> getAmbiguityUsersList() {
		return ambiguityUsersList;
	}

	public void setAmbiguityUsersList(List<AmbiguityUsers> ambiguityUsersList) {
		this.ambiguityUsersList = ambiguityUsersList;
	}

	public boolean isCookieCheck() {
		return cookieCheck;
	}

	public void setCookieCheck(boolean cookieCheck) {
		this.cookieCheck = cookieCheck;
	}
	
}
