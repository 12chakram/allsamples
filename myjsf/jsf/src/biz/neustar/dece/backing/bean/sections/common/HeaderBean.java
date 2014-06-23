/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HeaderBean.java
 */
package biz.neustar.dece.backing.bean.sections.common;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;

/**
 * The <code>HeaderBean.java</code> class encapsulates objects defined for DECE. 
 * @author Compugain.
 * @version $Revision: 1.5 $ $Date: 2012/06/19 13:04:23 $
 */
@ManagedBean(name="headerBean")
@RequestScoped
public class HeaderBean {
	private static final Logger headerBeanLogger = Logger.getLogger(HeaderBean.class);
	private String userName;
	private String emailAddress;
	private String householdName;	
	private boolean confirmResendEmail;
	SessionUtils sessionUtils = new SessionUtils();
	private FacesContext context=FacesContext.getCurrentInstance();
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private ResourceBundle errorBundleLabels=sessionUtils.getErrorTxtsBundle();
	
	public String getUserName() {
		if(null==userName){
			UserDVO loggedInUser;
			try {
				loggedInUser = ServiceLocator.getUiUserService().getUserProfile(sessionUtils.getUserAuth(),sessionUtils.getUserId());
				if(loggedInUser.getDisplayName()!=null){
					userName = loggedInUser.getDisplayName();
				}else{
					userName =loggedInUser.getUserName();
				}
			} catch (UIDeceException ex) {			
				ExceptionUtils.processUIDECEException("HEADER_USERNAME_GET", "ClassName:HeaderBean_MethodName:getUserName", ex, APIErrorMapUtil.uiErrorCodeFieldMap,errorValueMap, errorBundleLabels);
			}
		}		
		return userName;
	}
	public String getEmailAddress() {
		if(null==emailAddress){
			UserDVO loggedInUser;
			try {
				loggedInUser = ServiceLocator.getUiUserService().getUserProfile(sessionUtils.getUserAuth(),sessionUtils.getUserId());
				emailAddress=loggedInUser.getEmail();
			} catch (UIDeceException ex) {			
				ExceptionUtils.processUIDECEException("HEADER_EMAIL_GET", "ClassName:HeaderBean_MethodName:getEmailAddress", ex, APIErrorMapUtil.uiErrorCodeFieldMap,errorValueMap, errorBundleLabels);
			}	
		}	
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUserId() {		
			return sessionUtils.getUserId();
		}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHouseholdName() {
		if(null==householdName){
			UserSignInDVO signin= (UserSignInDVO)sessionUtils.getSession().getAttribute("SignIn");
			householdName=fixedNameLength(signin.getAccountName()); 
		}
		return householdName;
	}
	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}
	public String signOut() {
		try {
			if(null!=sessionUtils.getSession() && null!=sessionUtils.getSession().getAttribute("householdRemoved")){
				context.getExternalContext().redirect("../public/logout.jsf?errorCode=ACCOUNT_CLOSED");
			}else if(null!=sessionUtils.getSession() && null!=sessionUtils.getSession().getAttribute("selfMemberRemoved")){
				context.getExternalContext().redirect("../public/logout.jsf?errorCode=SELF_MEMBER_REMOVED");
			}
			else{
				context.getExternalContext().redirect("../public/logout.jsf");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
	/**
	 * Mar 15 ,2011.
	 * Resend email code. 
	 * @return boolean.
	 * @exception UIDeceException.
	 */
	public boolean resendEmail(){
		String nodeUser = sessionUtils.getUserAuth().getNodeUserId().toString();
		try{
			confirmResendEmail=ServiceLocator.getUiUtilService().resendEmail(nodeUser, UIUserVerificationTokenType.VALIDATE_EMAIL);
			if(!confirmResendEmail)
			{
			errorValueMap.put("pageError",errorBundleLabels.getString("emailResendForExisted"));
			}
		}
		catch(UIDeceException ex)
		{
			ExceptionUtils.processUIDECEException("LoggedUser_RESENDEMAIL","ClassName:HeaderBean_MethodName:resendEmail",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundleLabels);
		}
		return confirmResendEmail;
	}
	/**
	 * March 04,2011.
	 * Method is used to display userName with fixed length.
	 * @param userName
	 * @return String
	 */
	private String fixedNameLength(String userName){
		int lenghtValue=userName.length();
		if(lenghtValue<13){
		  return userName;
		}else{
			userName=userName.substring(0, 12)+"...";
			return userName;
		}		
	}
	public String goHome() {
		HttpSession session = sessionUtils.getSession();
		if(session != null && session.getAttribute("User") != null)
			return "mediaSection";
		return "homePage";
	}
	public void setConfirmResendEmail(boolean confirmResendEmail) {
		this.confirmResendEmail = confirmResendEmail;
	}
	public boolean isConfirmResendEmail() {
		return confirmResendEmail;
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
}