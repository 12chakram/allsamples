/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * UnlockProfileBean.java
 */
package biz.neustar.dece.backing.bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.exception.DECESSPException;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
/**
 * The <code>UnlockProfileBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.8 
 */

@ManagedBean(name="unlockProfile")
@RequestScoped
public class UnlockProfileBean {

	private static final Logger logHandler = Logger.getLogger(UnlockProfileBean.class);
	
	private LinkedHashMap<String, Long> secretQuestionMap;
	
	private String username;
	private Long secretQuestionId;		
	private String answer;
	private String errorMessages;	
	private String unlockErrorMessage;
	private ResourceBundle errorsBundle;
	private HashMap<String,String> errorValueMap;
	

	
public UnlockProfileBean() {
		
	secretQuestionMap = null;
	SessionUtils sessionVars = new SessionUtils();
	errorsBundle  = sessionVars.getErrorTxtsBundle();
	errorValueMap = new HashMap<String,String>();	
		
	}
	
	
	public String getUnlockErrorMessage() {
		return unlockErrorMessage;
	}
	public void setUnlockErrorMessage(String unlockErrorMessage) {
		this.unlockErrorMessage = unlockErrorMessage;
	}
	
	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}	

	public LinkedHashMap<String, Long> getSecretQuestionMap() {
		
		try {
			secretQuestionMap = CommonValidations.getSecretQuestionMap("UnlockProfile");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return secretQuestionMap;		
	}
	public void setSecretQuestionMap(
			LinkedHashMap<String, Long> secretQuestionMap) {
		this.secretQuestionMap = secretQuestionMap;
	}
	public Long getSecretQuestionId() {
		return secretQuestionId;
	}


	public void setSecretQuestionId(Long secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
	}


	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}


	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}

	/**
	 * Method calls when user Clicks on Submit Button.
	 * @return
	 * @throws DECESSPException
	 */
	public String submit() throws DECESSPException{		
		
		boolean tokenFlag=false;
		
		if(validateSubmittedValues()){
			try {
				logHandler.info("Unlock My Profile - Post submitted Values, to invoke Service API");
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				tokenFlag=ServiceLocator.getUiLoginService().unlockMe(username,secretQuestionId,answer,request.getRemoteHost());
				logHandler.info("Unlock My Profile - Service API request to generate token,status: "+tokenFlag);	
				return "unlockConfirmPage";
				   
			 } catch (UIDeceException ex) {
				 errorValueMap.put("isValidForm",String.valueOf(tokenFlag));
				 ExceptionUtils.processUIDECEException("UNLOCK_MY_PROFILE","ClassName:UnlockProfileBean_MethodName:submit",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
		}		
		return null;
	}

	/**
	 * Methods used to validate the form	
	 * @return
	 */
	private boolean validateSubmittedValues(){
		
		boolean isValidForm = true;
			logHandler.info("UnlockMyProfile - Call to validate submitted values");
		//validate username
		if("".equals(username)){
			errorValueMap.put("usernameError",errorsBundle.getString("username_required"));	
			isValidForm = false;
		}			
		
		// validate if secretQuestion was chosen
		if(0L == secretQuestionId ){
			errorValueMap.put("secretQuestionError",errorsBundle.getString("question_mandatory"));
			isValidForm = false;
		}
		
		if("".equals(answer) ){
			errorValueMap.put("secretAnswerError",errorsBundle.getString("answer_unlockprofile"));
			isValidForm = false;
		}
		
		if(!isValidForm)
			errorValueMap.put("isValidForm", String.valueOf(isValidForm));
		logHandler.info("UnlockMyProfile - Validation completed with Status: "+isValidForm);
		
		return isValidForm;
	}
	/**
	 * calls when user Clicks on Cancel Button
	 * @return
	 */
	public String cancel(){		
		return "homePage";
	}
	

	public String customerService() {		
		return "customerServicePage";
	}
	public String confirmResendEmailAction() {
		return "homePage";
	}
	public String confirmContinueAction() {
		return "homePage";
	}
}