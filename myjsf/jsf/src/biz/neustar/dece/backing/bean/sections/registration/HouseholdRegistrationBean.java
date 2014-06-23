/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HouseholdRegistrationBean.java
 */
package biz.neustar.dece.backing.bean.sections.registration;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
 * @version $Revision: 1.19 $ $Date: 2012/06/27 13:21:49 $
 */

@ManagedBean(name= "householdRegistrationBean")
@RequestScoped
public class HouseholdRegistrationBean {
	private static final Logger householdRegistrationLogger = Logger.getLogger(HouseholdRegistrationBean.class);
	SessionUtils sessionUtils = new SessionUtils();
	UserDVO userDVO=new UserDVO();
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private UIUtilService uiUtilService;
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
	private TreeMap<String,String> supportingCountryMap;

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
	 * termsOfUse 
	 * countryName
	 * neustar2.0
	 */
	//Default constructor.
	public HouseholdRegistrationBean() {
		errorValueMap = new HashMap<String, String>();	
		registrationValueMap=new HashMap();	
		supportingCountryMap=new TreeMap<String,String>();
		geoProfileBeanObj = new GeoProfileBean();
		gettingCountriesFromConfig();
		if(null != geoProfileBeanObj && null != geoProfileBeanObj.getCountry())
				registrationValueMap.put("countryName", geoProfileBeanObj.getCountry());
	}
	
	// method for getting country names from config.properties
	private void gettingCountriesFromConfig(){
		FacesContext context=FacesContext.getCurrentInstance();	
		if(null!=context.getExternalContext().getApplicationMap().get("supportedCountry")){
			String supportedCountry = (String) context.getExternalContext().getApplicationMap().get("supportedCountry");
			String[] supportedCountryList = supportedCountry.split(",");
			for(int i=0;i<supportedCountryList.length;i++){
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
	
	/**
	 * Feb 07,2011.
	 * Navigate to step3 action.
	 * @return
	 */
	//Method for getting Country and validating DateOfBirth
	public String continueStep1Action(){
		if(null!=registrationValueMap.get("countryName"))
			sessionUtils.getSession().setAttribute("country", registrationValueMap.get("countryName").toString());
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
	 * Navigate to step2 action.
	 * @return
	 */
	//Method for getting the email id and validating the email
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
	//Method for validating Sing-in name
	public Boolean checkAvailabilityAction() {
		try {
			step1Render="regStep2Page";
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
	 * Apr 20,2012.
	 * Email availability check method
	 * @return boolean
	 * @exception UIDeceException
	 */
	//Method for validating Email Address
	public void checkEmailAvailabilityAction(){
		try{
			step1Render="regStep2Page";
			//validate email address
				errorValueMap.put("emailError", CommonValidations.emailValidation(registrationValueMap.get("emailAddress").toString().trim()));
				if(!"".equals(errorValueMap.get("emailError"))){
					if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("email_not_matches")))
						errorValueMap.put("emailError", errorsBundle.getString("household_email_format"));
						errorValueMap.put("emailType", "errorMsg");
				}
				else if(!ServiceLocator.getUiUserService().checkUserEmailIDAvailablity(registrationValueMap.get("emailAddress").toString().trim())){
					errorValueMap.remove("emailError");
					errorValueMap.remove("emailType");
				}
				else{
					errorValueMap.put("emailError",errorsBundle.getString("emailAddress_already_exists"));
					errorValueMap.put("emailType", "hintMsg");
				}
		}
		catch(UIDeceException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Feb 3,2011.
	 * Submits registration form
	 * @return String.
	 * @exception UIDeceException
	 */	
	//Method for set user details into API.
	public String registrationSubmitAction(){
		UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
		//set all the values to UserDVO. 
		userDVO.setCountry(UICountry.valueOf(registrationValueMap.get("countryName").toString()));
		userDVO.setBirthDate(DateUtil.getDate(registrationValueMap.get("dobMonthValue").toString(),registrationValueMap.get("dobDateValue").toString(),registrationValueMap.get("dobYearValue").toString()));
		userDVO.setUserName(registrationValueMap.get("userName").toString());
		userDVO.setDisplayName(registrationValueMap.get("firstName").toString().trim());
		userDVO.setSurName(registrationValueMap.get("lastName").toString().trim());
		userDVO.setEmail(registrationValueMap.get("emailAddress").toString().trim());
		userDVO.setPassword(registrationValueMap.get("password").toString());
		
		userDVO.setEulaPolicyAccepted(Boolean.valueOf(registrationValueMap.get("termsOfUse").toString()));
		userDVO.setPrivilege(UserPrivilege.FULL);
		try {		
			if (validateSubmittedValues()) {
				householdRegistrationLogger.info("Starting: Post form inputs validation, about to create household");								
				//set householdName,householdCountry to AccountDVO
				AccountDVO acDVO=new AccountDVO();
				acDVO.setName(userDVO.getSurName().toString());
				if(registrationValueMap.get("countryName")!=null && !registrationValueMap.get("countryName").toString().equals("")){
						acDVO.setCountry(UICountry.valueOf(registrationValueMap.get("countryName").toString()));	
				}
				/*if(userDVO.getDisplayName()==null || "".equalsIgnoreCase(userDVO.getDisplayName().trim())){	
					userDVO.setDisplayName(userDVO.getUserName());
					registrationValueMap.put("displayName", userDVO.getDisplayName().toString());
				}*/					
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
				step1Render="regStep2Page";
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
	//Method for validating all the fields entered by the user
	public boolean validateSubmittedValues() {		
		boolean isValidForm = true;		
		
		//First Name validation
		if(null==userDVO.getDisplayName() || "".equals(userDVO.getDisplayName().toString())){
			errorValueMap.put("firstNameError", errorsBundle.getString("firstName_not_entered"));
			isValidForm = false;
		}else
			errorValueMap.remove("firstNameError");
		//Last Name validation
		if(null==userDVO.getSurName() || "".equals(userDVO.getSurName().toString())){
			errorValueMap.put("lastNameError", errorsBundle.getString("lastName_not_entered"));
			isValidForm = false;
		}else
			errorValueMap.remove("lastNameError");
		
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
		if("".equals(userDVO.getEmail().trim()) || userDVO.getEmail()==null){
			errorValueMap.put("emailError", errorsBundle.getString("memberEmailReq"));
			errorValueMap.put("emailType", "errorMsg");
			isValidForm = false;
		}
		else{
			errorValueMap.put("emailError", CommonValidations.emailValidation(userDVO.getEmail()));
			if(!"".equals(errorValueMap.get("emailError"))){
				if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("email_not_matches")))
					errorValueMap.put("emailError", errorsBundle.getString("household_email_format"));
					errorValueMap.put("emailType", "errorMsg");
					isValidForm = false;
			}
			else{
				try {
					if(!ServiceLocator.getUiUserService().checkUserEmailIDAvailablity(userDVO.getEmail().toString().trim())){
						errorValueMap.remove("emailError");
						errorValueMap.remove("emailType");
					}
					else{
						errorValueMap.put("emailError",errorsBundle.getString("emailAddress_already_exists"));
						errorValueMap.put("emailType", "hintMsg");
					}
				} catch (UIDeceException e) {
					e.printStackTrace();
				}
			}
		}
			
			
		if (!userDVO.getEulaPolicyAccepted()) {
			errorValueMap.put("termsOfUseError", errorsBundle.getString("terms_and_cond_error_household"));
			isValidForm = false;
		}else
			errorValueMap.remove("termsOfUseError");
		if(!isValidForm)
			registrationValueMap.put("isValidForm", isValidForm);
		return isValidForm;
	}
	
	/**
	 * Feb 3,2011.
	 * Resend email code. 
	 * @return boolean.
	 * @exception UIDeceException.
	 */
	//Method for resends email to user's email id 
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
	//Method for validating Household name
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
	//Method for validating user's DateOfBirth
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
		if(!isValidForm)
			registrationValueMap.put("isValidForm", isValidForm);
		return isValidForm;
	}
	/**
	 * Apr 11,2011.
	 * Navigate step1 page.
	 * @return
	 */
	//Method for Navigating user to verifyRegistrationDOB step
	public String goToStep1(){
			step1Render="step1FirstPage";
			return "registrationStep1Page";		
	}

	public TreeMap<String, String> getSupportingCountryMap() {
		return supportingCountryMap;
	}

	public void setSupportingCountryMap(TreeMap<String, String> supportingCountryMap) {
		this.supportingCountryMap = supportingCountryMap;
	}
}