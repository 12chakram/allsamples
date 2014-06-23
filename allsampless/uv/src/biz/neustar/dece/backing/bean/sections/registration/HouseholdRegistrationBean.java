
/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HouseholdRegistrationBean.java
 */
package biz.neustar.dece.backing.bean.sections.registration;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import biz.neustar.dece.backing.bean.sections.common.GeoProfileBean;
import biz.neustar.dece.backing.bean.sections.login.LoginUserBean;
import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.DateUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.AccountDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.enumeration.UICountry;
import biz.neustar.dece.ui.enumeration.UIUserVerificationTokenType;
import biz.neustar.dece.ui.enumeration.UserPrivilege;
import biz.neustar.dece.ui.service.UIAccountService;
import biz.neustar.dece.ui.service.UIUtilService;

/**
 * The <code>HouseholdRegistrationBean.java</code> class encapsulates objects defined for DECE. 
 * @author Compugain.
 * @version $Revision: 1.45 $ $Date: 2011/08/29 22:25:54 $
 */
public class HouseholdRegistrationBean {
	private static final Logger householdRegistrationLogger = Logger.getLogger(HouseholdRegistrationBean.class);
	SessionUtils sessionUtils = new SessionUtils();
	UserDVO userDVO=new UserDVO();
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
	private UIUtilService uiUtilService;
	private LinkedHashMap<String, Long> secretQuestionMap;
	private Map<String, String> dobMonthDetailsList;
	private Map<String, String> dobDateDetailsList;
	private Map<String, String> dobYearDetailsList;
	private String step1Render="step1FirstPage";	
	// HashMap is used over independent variables for each error field for better readability and maintainability.	
	private HashMap<String,String> errorValueMap;
	private GeoProfileBean geoProfileBeanObj=null;
	/**
	 * Keys used in the errorValueMap for display input errors to user
	 */
	
	/**
	 * HashMap is used over independent variables for each input field for better readability and maintainability. 
	 * This is also based on the fact all the values are required in step 3 pass  to the createAccount API
	 */	
	private HashMap registrationValueMap; 
	private HashMap supportingCountryMap;

	/*
	 * Keys used in the registrationValueMap for capturing inputs
	 * dobMonthValue
	 * dobDateValue
	 * dobYearValue
	 * guardianEmail
	 * householdName
	 * householdCountry
	 * userName
	 * displayName
	 * emailAddress
	 * password
	 * confirmPassword
	 * secretQuestion1
	 * secretAnswer1
	 * secretQuestion2
	 * secretAnswer2
	 * termsOfUse 
	 * countryName
	 */
	//Default constructor.
	public HouseholdRegistrationBean() {
		errorValueMap = new HashMap<String, String>();	
		registrationValueMap=new HashMap();	
		supportingCountryMap=new HashMap();
		geoProfileBeanObj = new GeoProfileBean();
		gettingCountriesFromConfig();
		if(null != geoProfileBeanObj && null != geoProfileBeanObj.getCountry())
				registrationValueMap.put("countryName", geoProfileBeanObj.getCountry().trim().equalsIgnoreCase("UK")?"UK":"US");
		uiUtilService=(UIUtilService)ServiceLocator.getUiUtilService();
		try {
			secretQuestionMap = CommonValidations.getSecretQuestionMap("CreateHousehold");
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("HOUSEHOLD_REGISTRATION","ClassName:HouseholdRegistrationBean_MethodName:getSecqsMap",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
	}
	
	private void gettingCountriesFromConfig()
	{
		FacesContext context=FacesContext.getCurrentInstance();	
		if(null!=context.getExternalContext().getApplicationMap().get("supportedCountry"))
		{
			String supportedCountry = (String) context.getExternalContext().getApplicationMap().get("supportedCountry");
			String[] supportedCountryList = supportedCountry.substring(0).split(",");
			for(int i=0;i<supportedCountryList.length;i++)
			{
				String availableCountry=supportedCountryList[i];
				int indexOfSplit=availableCountry.indexOf("(");
				supportingCountryMap.put(availableCountry.substring(0, indexOfSplit), availableCountry.substring(indexOfSplit+1, availableCountry.indexOf(")")));
			}
		}
	}
	
	public String getStep1Render() {
		return step1Render;
	}
	public void setStep1Render(String step1Render) {
		this.step1Render = step1Render;
	}
	public HashMap getRegistrationValueMap() {
		return registrationValueMap;
	}
	public void setRegistrationValueMap(HashMap registrationValueMap) {
		this.registrationValueMap = registrationValueMap;
	}

	public LinkedHashMap<String, Long> getSecretQuestionMap() {
		return secretQuestionMap;
	}
	public void setSecretQuestionMap(LinkedHashMap<String, Long> secretQuestionMap) {
		this.secretQuestionMap = secretQuestionMap;
	}
	public Map<String, String> getDobMonthDetailsList() {
		if(dobMonthDetailsList==null){
			dobMonthDetailsList = DateUtil.getDobMonthMap();
		}
		return dobMonthDetailsList;
	}
	public void setDobMonthDetailsList(Map<String, String> dobMonthDetailsList) {
		this.dobMonthDetailsList = dobMonthDetailsList;
	}
	
	public Map<String, String> getDobDateDetailsList() {
		if(dobDateDetailsList==null){
			dobDateDetailsList=DateUtil.getDobDateMap();
		}
	   return dobDateDetailsList;
	}
	public void setDobDateDetailsList(Map<String, String> dobDateDetailsList) {
		this.dobDateDetailsList = dobDateDetailsList;
	}
	public Map<String, String> getDobYearDetailsList() {
		if(dobYearDetailsList==null){
			dobYearDetailsList = DateUtil.getDobYearMap();	 
		}
		return dobYearDetailsList;
	}
	public void setDobYearDetailsList(Map<String, String> dobYearDetailsList) {
		this.dobYearDetailsList = dobYearDetailsList;
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	public HashMap getSupportingCountryMap() {
		return supportingCountryMap;
	}

	public void setSupportingCountryMap(HashMap supportingCountryMap) {
		this.supportingCountryMap = supportingCountryMap;
	}
	
	/**
	 * Feb 07,2011.
	 * Navigate to step3 action.
	 * @return
	 */
	public String continueStep1Action(){
		if(null!=registrationValueMap.get("countryName")){
			sessionUtils.getSession().setAttribute("country", registrationValueMap.get("countryName").toString());
			if("UK".equalsIgnoreCase(registrationValueMap.get("countryName").toString()))
				sessionUtils.localeSet("en-uk");
			else
				sessionUtils.localeSet("en-us");
		}
		String memberAgeGrp = DateUtil.getAgeGroup(registrationValueMap.get("dobMonthValue").toString(),registrationValueMap.get("dobDateValue").toString(), registrationValueMap.get("dobYearValue").toString());
		if(validateDOB(memberAgeGrp)){
			if("ADULT".equalsIgnoreCase(memberAgeGrp)){	
				step1Render="regStep2Page";
				return "registrationStep1Page";
			}else{			
				step1Render="step1SecondPage";			
				return "registrationStep1Page";
			}
		}
		return null;
	}
	/**
	 * Feb 07,2011.
	 * Navigate step2 to step3 page.
	 * @return
	 */
	public String continueStep2Action(){
		if(null!=registrationValueMap.get("countryName")){
            sessionUtils.getSession().setAttribute("country", registrationValueMap.get("countryName").toString());
            if("UK".equalsIgnoreCase(registrationValueMap.get("countryName").toString()))
                  sessionUtils.localeSet("en-uk");
            else
                  sessionUtils.localeSet("en-us");
      }

		if(validateHouseholdName()){
			step1Render="regStep3Page";			
			return "registrationStep1Page";
		}else{
			step1Render="regStep2Page";
			return "registrationStep1Page";		
		}
	}
	/**
	 * Feb 07,2011.
	 * Navigate to step2 action.
	 * @return
	 */
	public String submitEmailAction(){		
		//validate email address
		String email=registrationValueMap.get("guardianEmail").toString().trim();
		if("".equals(email) || email==null)
			errorValueMap.put("emailError", errorsBundle.getString("parentEmailReq"));
		else
			errorValueMap.put("emailError", CommonValidations.emailValidation(email));
		if(!"".equals(errorValueMap.get("emailError"))){
			step1Render="step1SecondPage";
			return "registrationStep1Page";
		}else{
			try{
				boolean status=ServiceLocator.getUiUtilService().sendEmailToLegalGuardianForCreateAccount(email);
				if(status){
					step1Render="step1ThirdPage";
					return "registrationStep1Page";
				}
			}catch (UIDeceException ex) {
				ExceptionUtils.processUIDECEException("HOUSEHOLD_REGISTRATION","ClassName:HouseholdRegistrationBean_MethodName:submitEmailAction",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
		}
		return null;
	 }
	
	/**
	 * Feb 3,2011.
	 * User name availability check method
	 * @return boolean
	 * @exception UIDeceException
	 */
	public Boolean checkAvailabilityAction() {
		try {
			step1Render="regStep3Page";
			// Check if input is a valid username format so that a RMI roundtrip can be avoided
			errorValueMap.put("usernameError",CommonValidations.userNameValidation(registrationValueMap.get("userName").toString()));
			//if valid username format then check in server 
			if("".equals(errorValueMap.get("usernameError"))){
				if(!ServiceLocator.getUiUserService().checkUserIDAvailablity(registrationValueMap.get("userName").toString().trim()))	{
					errorValueMap.remove("usernameError");
					registrationValueMap.put("userCheckFlag","TRUE");
					return Boolean.TRUE;
				 }
				else{
					errorValueMap.put("usernameError",errorsBundle.getString("user_already_exists"));
				}
			}
		} catch (UIDeceException e) {
			e.printStackTrace();
		}
		registrationValueMap.put("userCheckFlag","FALSE");
		return Boolean.FALSE;
	}
	
	/**
	 * Feb 3,2011.
	 * Submits registration form
	 * @return String.
	 * @exception UIDeceException
	 */	
	public String registrationSubmitAction(){
		if(null!=registrationValueMap.get("countryName")){
			sessionUtils.getSession().setAttribute("country", registrationValueMap.get("countryName").toString());
			if("UK".equalsIgnoreCase(registrationValueMap.get("countryName").toString()))
				sessionUtils.localeSet("en-uk");
			else
				sessionUtils.localeSet("en-us");
		}
		UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
		//set all the values to UserDVO. 
		userDVO.setCountry(UICountry.valueOf(registrationValueMap.get("countryName").toString()));
		userDVO.setBirthDate(DateUtil.getDate(registrationValueMap.get("dobMonthValue").toString(),registrationValueMap.get("dobDateValue").toString(),registrationValueMap.get("dobYearValue").toString()));
		userDVO.setUserName(registrationValueMap.get("userName").toString());
		userDVO.setDisplayName(registrationValueMap.get("displayName").toString());
		userDVO.setEmail(registrationValueMap.get("emailAddress").toString());
		userDVO.setPassword(registrationValueMap.get("password").toString());
		
		/*userDVO.setSecretQuestionOneId(Long.valueOf(registrationValueMap.get("secretQuestion1").toString()));
		userDVO.setSecretQuestionOneAnswer(registrationValueMap.get("secretAnswer1").toString().trim());
		userDVO.setSecretQuestionTwoId(Long.valueOf(registrationValueMap.get("secretQuestion2").toString()));
		userDVO.setSecretQuestionTwoAnswer(registrationValueMap.get("secretAnswer2").toString().trim());
		*/
		userDVO.setSecretQuestionOneId(registrationValueMap.get("secretQuestion1").toString().equals("0")? null:Long.valueOf(registrationValueMap.get("secretQuestion1").toString()));
		userDVO.setSecretQuestionOneAnswer(registrationValueMap.get("secretAnswer1").toString().trim().equals("")? null:registrationValueMap.get("secretAnswer1").toString().trim());
		userDVO.setSecretQuestionTwoId(registrationValueMap.get("secretQuestion2").toString().equals("0")? null:Long.valueOf(registrationValueMap.get("secretQuestion2").toString()));
		userDVO.setSecretQuestionTwoAnswer(registrationValueMap.get("secretAnswer2").toString().trim().equals("")? null:registrationValueMap.get("secretAnswer2").toString().trim());
		
		userDVO.setEulaPolicyAccepted(Boolean.valueOf(registrationValueMap.get("termsOfUse").toString()));
		userDVO.setPrivilege(UserPrivilege.FULL);
		try {			
			if (validateSubmittedValues()) {
				householdRegistrationLogger.info("Starting: Post form inputs validation, about to create household");								
				//set householdName,householdCountry to AccountDVO
				AccountDVO acDVO=new AccountDVO();
				acDVO.setName(registrationValueMap.get("householdName").toString());
				if(registrationValueMap.get("countryName")!=null && !registrationValueMap.get("countryName").toString().equals("")){
					if(registrationValueMap.get("countryName").toString().equalsIgnoreCase("UK"))
						acDVO.setCountry(UICountry.UK);
					else
						acDVO.setCountry(UICountry.US);
					}
				if(userDVO.getDisplayName()==null || "".equalsIgnoreCase(userDVO.getDisplayName().trim())){	
					userDVO.setDisplayName(userDVO.getUserName());
				}					
				acDVO= uiAccountService.createAccount(acDVO, userDVO);
				householdRegistrationLogger.info("Household: "+ acDVO.getName()+" created successfully");
				LoginUserBean ul = new LoginUserBean();
				ul.setUserName(userDVO.getUserName());
				ul.setPassword(userDVO.getPassword());
				ul.doLogin();
				SessionUtils sessionVars = new SessionUtils();
				sessionVars.getSession().setAttribute("nodeUser", acDVO.getNodeUserId());
				sessionVars.getSession().removeAttribute("DASHBOARD_EMAIL_NOT_VERIFIED");//This is to avoid email popup from registration page.
				return "createHouseholdSuccessPage";
			}else{
				step1Render="regStep3Page";
				return "registrationStep1Page";
			}
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("HOUSEHOLD_REGISTRATION","ClassName:HouseholdRegistrationBean_MethodName:registrationSubmitAction",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return null;
	}
	
	/**
	 * Feb 3,2011.
	 * Validates for registration form fields.
	 * @return boolean
	 */
	public boolean validateSubmittedValues() {		
		boolean isValidForm = true;		
		//validate username (inside checkavailability usnername patterns also validated)		
		if(!checkAvailabilityAction()){
			isValidForm = false;
		}
		//validate password
		if("".equals(userDVO.getPassword().trim())){
			errorValueMap.put("passwordError", errorsBundle.getString("pass_not_entered"));
			isValidForm=false;
		}else if("".equals(registrationValueMap.get("confirmPassword").toString().trim())){
			errorValueMap.put("confirmPasswordError", errorsBundle.getString("confirm_password_mandatory"));
			isValidForm=false;
		}else if(userDVO.getPassword().equals(registrationValueMap.get("confirmPassword"))){
			errorValueMap.put("passwordError",CommonValidations.passwordValidation(userDVO, userDVO.getPassword()));
			if(null!=errorValueMap.get("passwordError") && ""!=errorValueMap.get("passwordError")){
				if(errorValueMap.get("passwordError").toString().equalsIgnoreCase(errorsBundle.getString("insufficientPassword")))
					errorValueMap.put("passwordError", errorsBundle.getString("insufficientPassword_self"));
			}
			if (!"".equals(errorValueMap.get("passwordError"))) 
				isValidForm=false;
			else
				errorValueMap.remove("passwordError");
		}else{
			errorValueMap.put("confirmPasswordError", errorsBundle.getString("Password_confirm_password_not_same"));
			isValidForm=false;
			}
		//validate email address
		errorValueMap.put("emailError", CommonValidations.emailValidation(userDVO.getEmail()));
			if(!"".equals(errorValueMap.get("emailError"))){
				if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("email_not_matches")))
					errorValueMap.put("emailError", errorsBundle.getString("household_email_format"));
				isValidForm = false;
			}
			else
				errorValueMap.remove("emailError");
			
		//validations for secretQuestions and Answers
		if(!(userDVO.getSecretQuestionOneId()==null)){
			if("".equals(userDVO.getSecretQuestionOneAnswer()) || null==userDVO.getSecretQuestionOneAnswer()){
				errorValueMap.put("secAns1Error", errorsBundle.getString("enter_answer"));
				isValidForm=false;
			}else
				errorValueMap.remove("secAns1Error");
		}
		if(!(userDVO.getSecretQuestionTwoId()==null)){
			if("".equals(userDVO.getSecretQuestionTwoAnswer()) || null==userDVO.getSecretQuestionTwoAnswer()){
				errorValueMap.put("secAns2Error", errorsBundle.getString("enter_answer"));
				isValidForm=false;
			}else
				errorValueMap.remove("secAns2Error");
		}
		//Evaluate if same questions have been selected only if both secret questions 1 & 2 have valid questions selected 
		if(!(userDVO.getSecretQuestionOneId()==null) && !(userDVO.getSecretQuestionTwoId()==null) && userDVO.getSecretQuestionOneId().equals(userDVO.getSecretQuestionTwoId())){			
			errorValueMap.put("secQuestion2Error", errorsBundle.getString("sec2selection"));
			isValidForm=false;
		}
		
		//if only present the answer without selecting the question
		if(userDVO.getSecretQuestionOneId()==null){
			if(!(userDVO.getSecretQuestionOneAnswer()==null)){
				errorValueMap.put("secQuestion1Error", errorsBundle.getString("SELECT_VALID_QUESTION"));
				isValidForm=false;
			}else
				errorValueMap.remove("secQuestion1Error");
		}
		if(userDVO.getSecretQuestionTwoId()==null){
			if(!(userDVO.getSecretQuestionTwoAnswer()==null)){
				errorValueMap.put("secQuestion2Error", errorsBundle.getString("SELECT_VALID_QUESTION"));
				isValidForm =false;
			}else
				errorValueMap.remove("secQuestion2Error");
		}
			
		// validation for before build 1.0.1.0	
		/*if(0L==userDVO.getSecretQuestionOneId()){
			errorValueMap.put("secQuestion1Error", errorsBundle.getString("SELECT_VALID_QUESTION"));
			isValidForm=false;
		}else
			errorValueMap.remove("secQuestion1Error");
		if(0L == userDVO.getSecretQuestionTwoId()){
			errorValueMap.put("secQuestion2Error", errorsBundle.getString("SELECT_VALID_QUESTION"));
			isValidForm =false;
		}else
			errorValueMap.remove("secQuestion2Error");
		//Evaluate if same questions have been selected only if both secret questions 1 & 2 have valid questions selected 
		if(!(0L==userDVO.getSecretQuestionOneId())&& !(0L == userDVO.getSecretQuestionTwoId()) && userDVO.getSecretQuestionOneId().equals(userDVO.getSecretQuestionTwoId())){			
			errorValueMap.put("secQuestion2Error", errorsBundle.getString("sec2selection"));
			isValidForm=false;
		}
		//only to check if answer is present
		if("".equals(userDVO.getSecretQuestionOneAnswer()) || null==userDVO.getSecretQuestionOneAnswer()){
			errorValueMap.put("secAns1Error", errorsBundle.getString("enter_answer"));
			isValidForm=false;
		}else
			errorValueMap.remove("secAns1Error");
		if("".equals(userDVO.getSecretQuestionTwoAnswer()) || null==userDVO.getSecretQuestionTwoAnswer()){
			errorValueMap.put("secAns2Error", errorsBundle.getString("enter_answer"));
			isValidForm=false;
		}else
			errorValueMap.remove("secAns2Error");*/
	
		if (!userDVO.getEulaPolicyAccepted()) {
			errorValueMap.put("termsOfUseError", errorsBundle.getString("terms_and_cond_error_household"));
			isValidForm = false;
		}else
			errorValueMap.remove("termsOfUseError");
		return isValidForm;
	}
	
	/**
	 * Feb 3,2011.
	 * Resend email code. 
	 * @return boolean.
	 * @exception UIDeceException.
	 */
	public boolean resendEmail(){
		boolean resentEmail=false;
		SessionUtils sessionVars = new SessionUtils();
		String nodeUser = sessionVars.getSession().getAttribute("nodeUser").toString();
		sessionVars.getSession().removeAttribute("nodeUser");
		try{
		uiUtilService = ServiceLocator.getUiUtilService();
		resentEmail=uiUtilService.resendEmail(nodeUser, UIUserVerificationTokenType.VALIDATE_EMAIL);
		}
		catch(UIDeceException ex)
		{
			ExceptionUtils.processUIDECEException("HOUSEHOLD_RESENDEMAIL","ClassName:HouseholdRegistrationBean_MethodName:resendEmail",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return resentEmail;
	}
	
	/**
	 * Feb 07,2011.
	 * Validation method for house hold name.
	 * @return 
	 */
	public Boolean validateHouseholdName() {
		String householdName=registrationValueMap.get("householdName").toString();		
		errorValueMap.put("householdValidationFlag","TRUE");
		errorValueMap.put("houseHoldNameError",CommonValidations.houseHoldNameValidation(householdName.trim()));
		if(!"".equals(errorValueMap.get("houseHoldNameError")))
			errorValueMap.put("householdValidationFlag","FALSE");			
		else
			errorValueMap.remove("houseHoldNameError");	  
		return Boolean.valueOf(errorValueMap.get("householdValidationFlag"));		
	}
	/**
	 * Feb 28,2011
	 * Validate Date of Birth.
	 * @param memberAgeGrp
	 * @return
	 */
	public boolean validateDOB(String memberAgeGrp){
		boolean isValidForm=true;		
		if("DOBInValid".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dob_mandatory"));
		}
		else if("MonthAndDateMandatory".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobDateMonth_mandatory"));
		} 
		else if("DateAndYearMandatory".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobDateYear_mandatory"));
		} 
		else if("MonthAndYearMandatory".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobMonthYear_mandatory"));
		} 
		else if("DateInValid".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobDate_mandatory"));
		} 
		else if("MonthInValid".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobMonth_mandatory"));
		} 
		else if("YearInValid".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("dobYear_mandatory"));
		} 
		else if("InValidDOB".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			errorValueMap.put("ageGroupError", errorsBundle.getString("DOB_INVALID"));
		}else{
			errorValueMap.remove("ageGroupError");
		}		
		return isValidForm;
	}
	/**
	 * Apr 11,2011.
	 * Navigate step1 page.
	 * @return
	 */
	public String goToStep1(){
			step1Render="step1FirstPage";
			return "registrationStep1Page";		
	}
	public String goToStep2(){
		step1Render="regStep2Page";
		return "registrationStep1Page";		
	}

	
}








