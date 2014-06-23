/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * ForgotPasswordBean.java
 */
package biz.neustar.dece.backing.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;

/**
 * The <code>ForgotPasswordBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version	$Revision: 1.7 
 */

@ManagedBean(name="forgotPassword")
@RequestScoped
public class ForgotPasswordBean {

	private static final Logger logHandler = Logger.getLogger(ForgotPasswordBean.class);
	
	private String username;
	private Long secretQuestionId;
	private LinkedHashMap<String, Long> secretQuestionMap;
	private String answer;	
	private boolean isValidForm = false;
	private ResourceBundle errorsBundle;
	private HashMap<String,String> errorValueMap;
	

	
	public ForgotPasswordBean() {		
		secretQuestionMap = null;
		SessionUtils sessionVars = new SessionUtils();
		errorsBundle  = sessionVars.getErrorTxtsBundle();
		errorValueMap = new HashMap<String,String>();		
	}
	
	public Long getSecretQuestionId() {
		return secretQuestionId;
	}

	public void setSecretQuestionId(Long secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
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
			secretQuestionMap = CommonValidations.getSecretQuestionMap("ForgotPassword");
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		return secretQuestionMap;		
	}

	
	public void setSecretQuestionMap(LinkedHashMap<String, Long> secretQuestionMap) {
		this.secretQuestionMap = secretQuestionMap;
	}

	/**
	 * Method calls when user Clicks on Submit Button.
	 * @return
	 */
	
	public String submit(){		
		
		boolean tokenFlag=false;
		
		if(validateSubmittedValues()){
			/*try {
				logHandler.info("Forgot Password - Post submitted Values, to invoke Service API");
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				tokenFlag=ServiceLocator.getUiLoginService().forgotPassword(username,request.getRemoteHost());
				logHandler.info("Forgot Password - Service API request to generate token, successful");
				return "troubleSignInSuccessPage";
				   	
			 } catch (UIDeceException ex) {	
				 errorValueMap.put("isValidForm",String.valueOf(tokenFlag));
				ExceptionUtils.processUIDECEException("FORGOT_PASSWORD","ClassName:ForgotPasswordBean_MethodName:submit",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}*/
		}
		//popupRenderer = gotoCustomerCare;
		return null;
	}

	/**
	 * Methods used to validate the form
	 * @return
	 */
	private boolean validateSubmittedValues(){
		
		isValidForm = true;
		logHandler.info("Forgot Password - Call to validate submitted values");
		
		//validate username
		if("".equals(username)){
			errorValueMap.put("usernameError",errorsBundle.getString("username_required"));	
			isValidForm = false;
		}			
		
		// validate if secretQuestion was chosen
		/*if(0L == secretQuestionId ){
			errorValueMap.put("secretQuestionError",errorsBundle.getString("question_mandatory"));
			isValidForm = false;
		}
		
		if("".equals(answer) ){
			errorValueMap.put("secretAnswerError",errorsBundle.getString("answer_unlockprofile"));
			isValidForm = false;
		}	*/	
		
		if(!isValidForm)
			errorValueMap.put("isValidForm",String.valueOf(isValidForm));
		logHandler.info("Forgot Password - Validation completed with Status: "+isValidForm);
		return isValidForm;
	}
	
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
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
		//This is added more to have a similar functionality, if need be it can be incorporated with a revised reSendEmail method 
		return "homePage";
	}
	public String confirmContinueAction() {
		return "homePage";
	}

	public long getCurrentTime() {
		return new Date().getTime();
	}
}