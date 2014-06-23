package biz.neustar.dece.backing.bean.sections.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;

@ManagedBean(name="hostageBean")
@RequestScoped
public class HostageBean {
	private UserDVO userDVO;
 	private SessionUtils sessionUtilsObj = new SessionUtils();
 	private Logger hostageLogger = Logger.getLogger(HostageBean.class);
 	private String emailUpdate;
 	private String errorMessage;
 	private String infoMessage;
 	private HashMap<String, String> errorValueMap = new HashMap<String, String>();
 	private ResourceBundle errorsBundle = sessionUtilsObj.getErrorTxtsBundle();
	private String validateForm="false";
	private boolean forResendStatus;
	private String emailErrMsg;
	public UserDVO getUserDVO() {
		sessionUtilsObj.getSession().removeAttribute("User");
		sessionUtilsObj.getSession().removeAttribute("SignIn");	// added when fix for defect 4057 was included to display a message in signiniframe page
		if(userDVO == null)
			getUser();
		return userDVO;
	}

	public void setUserDVO(UserDVO userDVO) {
		this.userDVO = userDVO;
	}

	public String getEmailUpdate() {
		return emailUpdate;
	}

	public void setEmailUpdate(String emailUpdate) {
		this.emailUpdate = emailUpdate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	public String getValidateForm() {
		return validateForm;
	}

	public void setValidateForm(String validateForm) {
		this.validateForm = validateForm;
	}
	
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}

	private void getUser(){
		try {
			UserAuthDVO  auth = sessionUtilsObj.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("HOSTAGE_PAGE");
			userDVO = ServiceLocator.getUiUserService().getUserProfile(auth, auth.getNodeUserId());
		} catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("HOSTAGE_PAGE", "ClassName:HostageBean_MethodName:getuser", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);			
		}		
	}
	
	public String updateUserEmail(){
		try {
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("HOSTAGE_PAGE");
			userDVO = ServiceLocator.getUiUserService().getUserProfile(auth, sessionUtilsObj.getUserAuth().getNodeUserId());
			if(userDVO != null && emailUpdate != null && !"".equals(emailUpdate)){
				errorValueMap.put("emailError",CommonValidations.emailValidation(emailUpdate).toString());
				if(!"".equals(errorValueMap.get("emailError"))){
					if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("email_not_matches")))
						errorValueMap.put("emailError", errorsBundle.getString("household_email_format"));
					return errorMessage=errorValueMap.get("emailError");
				}
				if(userDVO.getEmail().equals(emailUpdate)){
					//resendEmail();
					return errorMessage=errorsBundle.getString("enterDiffEmailError");
				}else{
					userDVO.setEmail(emailUpdate);
					hostageLogger.info("Changed User Email Address in userDVO Object : "+userDVO.toString());
					userDVO = ServiceLocator.getUiUserService().updateUserProfile(auth, userDVO);
					hostageLogger.info("Updated userDVO Object from API is : "+userDVO.toString());
					infoMessage = "email Address updated";
					validateForm="true";
					errorMessage = "";
					forResendStatus=true;
				}
				return null;
			} else {
				hostageLogger.info("No Email Address entered, cannot update");
				infoMessage = "";
				errorMessage = errorsBundle.getString("emailReq");
			}
		} catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("HOSTAGE_PAGE", "ClassName:HostageBean_MethodName:getuser", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);			

		}
		return null;
	}
	
	public String resendEmail(){
		
		try {
			boolean resentEmail = ServiceLocator.getUiUtilService().resendEmail(sessionUtilsObj.getUserAuth().getNodeUserId(),UIUserVerificationTokenType.VALIDATE_EMAIL);
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
	}

	public void setForResendStatus(boolean forResendStatus) {
		this.forResendStatus = forResendStatus;
	}

	public boolean isForResendStatus() {
		return forResendStatus;
	}

	public void closePopUp()
	{
		emailUpdate="";
		forResendStatus=false;
		try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../public/hostagePage.jsf");
		}catch (IOException ioe) {
			if(hostageLogger.isDebugEnabled())   {           
				hostageLogger.debug(ioe.getMessage());
			}
		}
	}

	public void setEmailErrMsg(String emailErrMsg) {
		this.emailErrMsg = emailErrMsg;
	}

	public String getEmailErrMsg() {
		return emailErrMsg;
	}

}