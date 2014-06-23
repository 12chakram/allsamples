/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * ConfirmMyDetailsBean.java
 */
package biz.neustar.dece.backing.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.AmbiguityUsers;
import biz.neustar.dece.backing.bean.sections.login.LoginNavigationBean;
import biz.neustar.dece.domain.portal.EmailAmbiguityUsers;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;
/**
 * The <code>ConfirmMyDetailsBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.30
 */
@ManagedBean(name="confirmMyDetails")
@RequestScoped
public class ConfirmMyDetailsBean {
	
	private static final Logger logHandler = Logger.getLogger(ConfirmMyDetailsBean.class);
	//UI fields
	private String token;
	private String userName;
	private String captchaValue;
	//pop UI fields
	private String password;
	private String confirmPassword;
	private String tokenError;
	private String userNameError;
	private String captchaError;
	private String passwordError;
	private String confirmPasswordError;
	private String message;
	private Boolean popupRenderer=Boolean.FALSE;
	private ResourceBundle errorsBundle;
	public HashMap<String,String> errorValueMap;
	public HashMap<String,String> popUpErrorValueMap;
	public boolean queryParamConfirmRequest = false;
	FacesContext context = FacesContext.getCurrentInstance();
	private HttpSession session = (HttpSession) context. getExternalContext().getSession(true);
	HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
	private String queryStringTypeObj="genericErr";
	private String stepsRender="";
	private String activationCode;
	private String confirmResendEmail="";
	private String resendEmailErr;
	private String userACName;
	private String accountName;
	private String emailSignAdd="";
	

	public ConfirmMyDetailsBean() {		
		errorValueMap = new HashMap<String, String>();
		popUpErrorValueMap = new HashMap<String, String>();
		errorsBundle = (new SessionUtils()).getErrorTxtsBundle();
		if(context.getExternalContext().getRequestParameterMap().get("token")==null && context.getExternalContext().getRequestParameterMap().get("pageName")==null){
			stepsRender="confirmPage";
		}else if(context.getExternalContext().getRequestParameterMap().get("pageName")!=null){
			if(context.getExternalContext().getRequestParameterMap().get("pageName").toString().equals("sendEmail")){
				stepsRender="sendEmail";
			}else if(context.getExternalContext().getRequestParameterMap().get("pageName").toString().equals("confirmPage") || context.getExternalContext().getRequestParameterMap().get("token")==null){
				stepsRender="confirmPage";
			}
		}
		queryParamConfirmDetails();
	}

	private void queryParamConfirmDetails(){
			queryParamConfirmRequest = false;
			Map<String,String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if(requestParameterMap.get("token") != null){
				token = requestParameterMap.get("token").trim().toString();
				logHandler.info("verifyUserToken from url query Parameters");
				queryParamConfirmRequest = true;
				if(null!=context.getExternalContext().getRequestParameterMap().get("type") && !"".equals(context.getExternalContext().getRequestParameterMap().get("type").toString().trim())){
					submit();
				}
			}
			if(requestParameterMap.get("CLG") != null && requestParameterMap.get("CLD") != null)
			{
				if(null!=session)
				{
					if(null!=session.getAttribute("userId") && session.getAttribute("userId").toString().contains(requestParameterMap.get("CLG")))
					{
						session.setAttribute("page", "confirmMyDetails");
						session.setAttribute("CLG", requestParameterMap.get("CLG"));
						session.setAttribute("CLD", requestParameterMap.get("CLD"));
						try {
							if(session.getAttribute("SignIn")==null)
							{
								if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl"))
								{
									context.getExternalContext().redirect(getHomePageURL());
								}
							}
							else{
								UserSignInDVO userSignInDVO=ServiceLocator.getUIPolicyService().getUserSignDVOForCoppaAcceptance((UserAuthDVO)session.getAttribute("Auth"));
								LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
								loginNavigationBean.setDetailsIntoSession(userSignInDVO,session);
								session.setAttribute("ADMIN_POPUP", "YES");
								
								String returnPath= loginNavigationBean.loginAction();
								if(!"NO".equals(session.getAttribute("ChildCoppaAccepted"))){
									session.removeAttribute("ADMIN_POPUP");
									session.removeAttribute("toShowPopupOf");
									try {
										if("mediaSection".equalsIgnoreCase(returnPath))
										context.getExternalContext().redirect("../views/mediaPage.jsf");
									} catch (IOException e) {
										e.printStackTrace();
									}
								}else if("COPPA_OTHERS".equals(session.getAttribute("toShowPopupOf")) || "ADMIN_POPUP".equals(session.getAttribute("toShowPopupOf")) || "JCOPPA_OTHERS".equals(session.getAttribute("toShowPopupOf"))){
									if(null!=userSignInDVO.getCountry()&& "GB".equalsIgnoreCase(userSignInDVO.getCountry().toString()))
									{
									session.removeAttribute("ChildCoppaAccepted");
									session.setAttribute("toShowPopupOf", "JCOPPA_OTHERS");
									session.removeAttribute("ADMIN_POPUP");
									}
									else
									{
										session.removeAttribute("ChildCoppaAccepted");
										session.setAttribute("toShowPopupOf", "COPPA_OTHERS");
										session.removeAttribute("ADMIN_POPUP");
									}
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
								}else if("TOU_OTHERS".equals(session.getAttribute("toShowPopupOf"))){
									session.removeAttribute("ChildCoppaAccepted");
									session.removeAttribute("ADMIN_POPUP");
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
						}catch (UIDeceException ex) {					
							ExceptionUtils.processUIDECEException("CONFIRM_MY_DEATILS","ClassName:ConfirmMyDetailsBean_MethodName:submit",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
						} 
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					else
					{
						LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
						loginNavigationBean.navigationToUnauthHomePage();
					}
			}
				else
				{
					LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
					loginNavigationBean.navigationToUnauthHomePage();
				}
			}
	}
	/**
	 * method to submit the form 
	 * @return
	 */
	public String submit(){
			String returnPath=null;	
			try {
				if(session!=null){
					if(!session.isNew()){
						session.invalidate();
						session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);
					}
				}
				session.setAttribute("recoveryEmailAddress",emailSignAdd);
				session.setAttribute("page", "confirmMyDetails");
				UserSignInDVO userSignInDVOObj  = ServiceLocator.getUiLoginService().verifyUserToken(token,request.getRemoteHost());
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				if(userSignInDVOObj != null && userSignInDVOObj.getUserName() != null){
					logHandler.info("userSignInDVOObj.getUserTokenType(): "+userSignInDVOObj.getUserTokenType());
					loginNavigationBean.setDetailsIntoSession(userSignInDVOObj,session);
					if(userSignInDVOObj.getNextPage().equals(UINextPageEnum.HOSTAGE)){
						try {
							context.getExternalContext().redirect("../public/hostagePage.jsf");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(userSignInDVOObj.getNextPage().equals(UINextPageEnum.RESET_PASSWORD))
							{
								try {
										session.setAttribute("username", userSignInDVOObj.getUserName());
										session.setAttribute("token", token);
										context.getExternalContext().redirect("../public/createNewPasswordPage.jsf");
									} catch (IOException e) {
										e.printStackTrace();
									}
							}
					
					else if(UIUserVerificationTokenType.FORGOT_PASSWORD.equals(userSignInDVOObj.getUserTokenType())){						
						logHandler.info("Token Verfied as FORGOT PASSWORD");
						returnPath=loginNavigationBean.loginAction();
						SessionUtils ssUtils = new SessionUtils();
						session=ssUtils.getSession();
						session.setAttribute("toShowPopupOf", "FORGOT_PASSWORD");
						session.setAttribute("token", token);
						//returnPath = "accountDashboardPage";
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
					}else if(!"ADULT".equalsIgnoreCase(userSignInDVOObj.getUserAgeClass().toString()) && userSignInDVOObj.getNextPage().equals(UINextPageEnum.TOU_SELF)){
                   	 session.setAttribute("clgUser", userSignInDVOObj.getUserName().trim());
                   	 session.setAttribute("dispFor","TOU_NOT_ACCEPTED_BY_CLG");
                   	 try{
          	              		context.getExternalContext().redirect("../public/pendingUserMessagePage.jsf");
          	             }catch (IOException ioe) {
	           	              if(logHandler.isDebugEnabled())   {           
	           	                    logHandler.debug(ioe.getMessage());
	           	              }
          	             }
                    }  
					else{
						logHandler.info("verifyUserToken success; Privilege : "+userSignInDVOObj.getPrivilege()+"; isAcceptedRecentTermsAndConditions: "+userSignInDVOObj.getNextPage());
						if(session.getAttribute("userLocale") == null){
							SessionUtils sessionUtilsObj = new SessionUtils();
							sessionUtilsObj.localeSet(null);						
						}
						session.removeAttribute("toShowPopupOf");
						returnPath=loginNavigationBean.loginAction();
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
				else if(userSignInDVOObj.getNextPage().equals(UINextPageEnum.EMAIL_AMBIGUITY))
				{
					 Map<String,List<EmailAmbiguityUsers>> emailAmbigutyUsersMap;
					 Map<AmbiguityUsers,String> userNamesMap=new LinkedHashMap<AmbiguityUsers,String>();
					 List<AmbiguityUsers> ambuguityList=new ArrayList<AmbiguityUsers>();
					 StringBuffer userDisplayName;
					 session.setAttribute("emailToken", token);
					 try{
									emailAmbigutyUsersMap=userSignInDVOObj.getEmailAmbigutyUsers();
									for(Iterator<String> iterator=emailAmbigutyUsersMap.keySet().iterator();iterator.hasNext();)
									{
										String userFullName=iterator.next();
										List<EmailAmbiguityUsers> emailAmbigutyUserList=emailAmbigutyUsersMap.get(userFullName);	
										session.setAttribute("emailAmbiguityUsers",emailAmbigutyUserList);
										if(!emailAmbigutyUserList.isEmpty()){
											for(Iterator<EmailAmbiguityUsers> emailAmbigutyUserIterator= emailAmbigutyUserList.iterator();emailAmbigutyUserIterator.hasNext();)
											{
												AmbiguityUsers ambiguityUsersobj=new AmbiguityUsers();
												 EmailAmbiguityUsers emailAmbiguityUsers=(EmailAmbiguityUsers)emailAmbigutyUserIterator.next();
												 accountName=emailAmbiguityUsers.getAccountName();
												   if(null==emailAmbiguityUsers.getSurName())
	                                                 {
													   userDisplayName=new StringBuffer();
													   userDisplayName.append(emailAmbiguityUsers.getFirstName()).append(" (from the <span class=\"violet_color_link\">"+fixedNameLength(accountName)+"</span>"+" account)");
	                                                 }
												   else{
													   userDisplayName=new StringBuffer();
													   userDisplayName.append(emailAmbiguityUsers.getFirstName()).append(" ").append(emailAmbiguityUsers.getSurName()).append(" (from the <span class=\"violet_color_link\">"+fixedNameLength(accountName)+"</span>"+" account)");
												   }
												   ambiguityUsersobj.setDisplayName(userDisplayName.toString());
												   ambiguityUsersobj.setNodeUserId(emailAmbiguityUsers.getNodeUserId());
												   ambuguityList.add(ambiguityUsersobj);
                                           }
                                     }
									}
									session.setAttribute("emailUsers",ambuguityList);
									context.getExternalContext().redirect("../public/emailDisambiguationPage.jsf");
					 }catch(Exception e){
							e.printStackTrace();
						}
				}
				else {
					logHandler.error("verifyUserToken Failed UserSignInDVO Object is null");
				}
			} catch (UIDeceException ex) {					
				ExceptionUtils.processUIDECEException("CONFIRM_MY_DEATILS","ClassName:ConfirmMyDetailsBean_MethodName:submit",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				Iterator<UIError> itrObj=ex.getErrors().iterator();
				String errorCodeObj="";
				while(itrObj.hasNext())
				{
					UIError uiErrObj = itrObj.next();
					errorCodeObj = uiErrObj.getErrorCode();
				}
				Map<String,String> queryStringObj = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				if(queryStringObj.get("type") != null && errorCodeObj.equalsIgnoreCase("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID") )
				{
					queryStringTypeObj = queryStringObj.get("type").trim().toString();
					if(queryStringTypeObj.equals("RST"))
					   errorValueMap.put("pageError",errorsBundle.getString("FORGOTPWD_TKN_EXPIRED"));
					else if(queryStringTypeObj.equals("ECF"))
						errorValueMap.put("pageError",errorsBundle.getString("EMAILCNF_TKN_EXPIRED"));
					else if(queryStringTypeObj.equals("ULK"))
						errorValueMap.put("pageError",errorsBundle.getString("UNLOCK_TKN_EXPIRED"));
					else
						queryStringTypeObj="genericErr";
				}
				else if(errorCodeObj.equalsIgnoreCase("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID"))
				{
					//errorValueMap.put("emailCodeError"," unable to process the reuest");
					 errorValueMap.put("pageError",errorsBundle.getString("REQUEST_ACCOUNT_USER_TOKEN_CREDENTIALS_INVALID"));
					 return "confirmMyDetails";
				}
				else if(errorCodeObj.equalsIgnoreCase("TOU_NOT_ACCEPTED_BY_CLG") || errorCodeObj.equalsIgnoreCase("COPPA_NOT_ACCEPTED_BY_CLG"))
				{
					queryStringTypeObj="clgNotAccepted";
				}
				//returnPath= null;
			}			
			
		return returnPath;
	}

	

	public ResourceBundle getBundle() {
		return errorsBundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.errorsBundle = bundle;
	}

	public String getConfirmPasswordError() {
		return confirmPasswordError;
	}

	public void setConfirmPasswordError(String confirmPasswordError) {
		this.confirmPasswordError = confirmPasswordError;
	}
	

	public String getTokenError() {
		return tokenError;
	}

	public void setTokenError(String tokenError) {
		this.tokenError = tokenError;
	}


	public String getUserNameError() {
		return userNameError;
	}

	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getCaptchaError() {
		return captchaError;
	}

	public void setCaptchaError(String captchaError) {
		this.captchaError = captchaError;
	}

	public String getStepsRender() {
		return stepsRender;
	}

	public void setStepsRender(String stepsRender) {
		this.stepsRender = stepsRender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
	public Boolean getPopupRenderer() {
		return popupRenderer;
	}

	public void setPopupRenderer(Boolean popupRenderer) {
		this.popupRenderer = popupRenderer;
	}

	public long getCurrentTime() {
		return new Date().getTime();
	}	
	
	public HashMap<String, String> getPopUpErrorValueMap() {
		return popUpErrorValueMap;
	}

	public void setPopUpErrorValueMap(HashMap<String, String> popUpErrorValueMap) {
		this.popUpErrorValueMap = popUpErrorValueMap;
	}

	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	public String getQueryStringTypeObj() {
		return queryStringTypeObj;
	}

	public void setQueryStringTypeObj(String queryStringTypeObj) {
		this.queryStringTypeObj = queryStringTypeObj;
	}
	
	private String getHomePageURL()
	{
		String portalHomeURL=null;
		FacesContext context=FacesContext.getCurrentInstance();
		if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl"))
		{
			portalHomeURL = (String) context.getExternalContext().getApplicationMap().get("portalglobalUrl");			
		}
		return portalHomeURL;
	}
	
/*public String resendEmail()
{
		try {
				boolean resentEmail = ServiceLocator.getUiUtilService().resendEmail(
																		sessionUtilsObj.getUserAuth().getNodeUserId(),
																		UIUserVerificationTokenType.VALIDATE_EMAIL
																	);
			if(resentEmail){
				validateForm="true";
				return null;
			} else {
				infoMessage = "";
				emailErrMsg =  errorsBundle.getString("emailResendForExisted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/



// for Validating the code entered after submitting the email address the activation code is send through email notification and
// this method is called when the user enters activation code....

public String submitActivationCode(){
	boolean token=false;
	if(validateActivationCode()){
		this.token=activationCode;
		if(null!=session && null!=session.getAttribute("recoveryEmailAddress"))
		this.emailSignAdd=session.getAttribute("recoveryEmailAddress").toString();
		String retPath=submit();
		return retPath;
	}
	return null;
}

// to validate activation code when entered by the user
private boolean validateActivationCode(){
	boolean isValid = true;
	logHandler.info("Activation Code - Call to validate submitted values");
	
	if("".equals(activationCode) || null==activationCode){
		errorValueMap.put("pageError",errorsBundle.getString("emailCode_required"));	
		isValid = false;
	}
	logHandler.info("Activation Code validation - Validation completed with Status: "+isValid);
	return isValid;
}

public String getUserACName() {
	return userACName;
}

public void setUserACName(String userACName) {
	this.userACName = userACName;
}

public String getAccountName() {
	return accountName;
}

public void setAccountName(String accountName) {
	this.accountName = accountName;
}


public String resendEmailFromRecoverySignIn()
{
	try{
		boolean token=false;
		if(null!=session && null!=session.getAttribute("recoveryEmailAddress"))
		{
			confirmResendEmail="false";
			token=ServiceLocator.getUiLoginService().forgotPassword(session.getAttribute("recoveryEmailAddress").toString(),request.getRemoteHost());
			//token=ServiceLocator.getUiUtilService().resendEmail(session.getAttribute("recoveryEmailAddress").toString(), UIUserVerificationTokenType.FORGOT_PASSWORD);
			if(token)
			{
				confirmResendEmail="true";
			}
			else
			{
				errorValueMap.put("pageError", errorsBundle.getString("emailResendForExisted"));
			}
		}
	}
	catch(UIDeceException ex)
	{
		ExceptionUtils.processUIDECEException("LoggedUser_RESENDEMAIL","ClassName:ConfirmMyDetails_MethodName:resendEmailFromRecoverySignIn",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
	}
	
	return null;
}

public String navigateToRecoveryPage()
{
	if(null!=session && null!=session.getAttribute("recoveryEmailAddress"))
		session.removeAttribute("recoveryEmailAddress");
	try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../public/recoveringSignInNamePage.jsf");
		}catch (IOException ioe) {
			if(logHandler.isDebugEnabled())   {           
				logHandler.debug(ioe.getMessage());
			}
		}
	return null;
}
/*check account name for 12 characters
 * 
 * 
 */
private String fixedNameLength(String accountName){
	int lenghtValue=accountName.length();
	if(lenghtValue<13){
	  return accountName;
	}else{
		accountName=accountName.substring(0, 12)+"...";
		return accountName;
	}		
}

public String getConfirmResendEmail() {
	return confirmResendEmail;
}

public void setConfirmResendEmail(String confirmResendEmail) {
	this.confirmResendEmail = confirmResendEmail;
}

public String getResendEmailErr() {
	return resendEmailErr;
}

public void setResendEmailErr(String resendEmailErr) {
	this.resendEmailErr = resendEmailErr;
}

public String getEmailSignAdd() {
	return emailSignAdd;
}

public void setEmailSignAdd(String emailSignAdd) {
	this.emailSignAdd = emailSignAdd;
}

}