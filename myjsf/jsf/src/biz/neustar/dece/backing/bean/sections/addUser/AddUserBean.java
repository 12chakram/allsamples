/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * AddUserBean.java
 */
package biz.neustar.dece.backing.bean.sections.addUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.DateUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.DashboardUserDVO;
import biz.neustar.dece.ui.dvo.ParentalControlsDVO;
import biz.neustar.dece.ui.dvo.RatingsDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserParentalControlsDVO;
import biz.neustar.dece.ui.enumeration.CAFilmParentalControlRating;
import biz.neustar.dece.ui.enumeration.CATvParentalControlRating;
import biz.neustar.dece.ui.enumeration.ParentalControlMovieRating;
import biz.neustar.dece.ui.enumeration.ParentalControlTVRating;
import biz.neustar.dece.ui.enumeration.UICountry;
import biz.neustar.dece.ui.enumeration.UKParentalControlRating;
import biz.neustar.dece.ui.enumeration.UserPrivilege;

/**
 * The <code>AddUserBean.java</code> class encapsulates objects defined for DECE.
 * @author Compugain.
 * @version $Revision: 1.23 $ $Date: 2012/07/10 17:29:52 $
 */

@SuppressWarnings("unused")
@ManagedBean(name="addUserBean")
@RequestScoped
public class AddUserBean {
	private static final Logger addUserBeanLogger = Logger.getLogger(AddUserBean.class);
	SessionUtils sessionUtils = new SessionUtils();
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
	private Map<String, String> dobMonthDetailsList;
	private Map<String, String> dobDateDetailsList;
	private Map<String, String> dobYearDetailsList;
	//private LinkedHashMap<String, Long> secretQuestionMap;
	private ArrayList<String> parentalControlMovieList;
	private ArrayList<String> parentalControlTVList;
	private ArrayList<String> ukParentalControlList;
	private HashMap<String,String> errorValueMap;
	private String stepsRender="step1Page";
	private String successRender="addUserSuccessStep";	
	private String parentalControlsOfStandardUser;
	private List<DashboardUserDVO> usersMemberList=new ArrayList<DashboardUserDVO>();	
	private Integer activeMembers;	
	private String userDisplayName;
	private int movieSliderValue, tvSliderValue, ukRatingSliderValue,camovievalue,catvvalue;
	UserDVO userDVO=new UserDVO();	
	private HashMap addUserValueMap;
	private String addedUserAgeClass;
	private TreeMap<String, String> supportingCountryMap;
	/*
	 * Keys used in the addUserValueMap for capturing inputs
	 * dobMonthValue
	 * dobDateValue
	 * dobYearValue
	 * country
	 * memberUserName
	 * memberDisplayName
	 * memberEmailAddress
	 * password
	 * confirmPassword
	 * secretQuestion1
	 * secretAnswer1
	 * secretQuestion2
	 * secretAnswer2
	 * termsOfUse
	 * countryName
	 */
	
	
	//Default Constructor.
	public AddUserBean(){
		errorValueMap = new HashMap<String, String>();
		addUserValueMap=new HashMap();
		supportingCountryMap =  new TreeMap<String, String>();
		gettingCountriesFromConfig();
		
			if(null != sessionUtils && null != sessionUtils.getSession())
				if(null != sessionUtils.getSession().getAttribute("country")){
					addUserValueMap.put("countryName", sessionUtils.getSession().getAttribute("country"));
				}
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
		
	public int getMovieSliderValue() {
		return movieSliderValue;
	}

	public void setMovieSliderValue(int movieSliderValue) {
		this.movieSliderValue = movieSliderValue;
	}

	public int getUkRatingSliderValue() {
		return ukRatingSliderValue;
	}
	public void setUkRatingSliderValue(int ukRatingSliderValue) {
		this.ukRatingSliderValue = ukRatingSliderValue;
	}
	public int getTvSliderValue() {
		return tvSliderValue;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public String getAddedUserAgeClass() {
		return addedUserAgeClass;
	}
	public void setAddedUserAgeClass(String addedUserAgeClass) {
		this.addedUserAgeClass = addedUserAgeClass;
	}
	public void setTvSliderValue(int tvSliderValue) {
		this.tvSliderValue = tvSliderValue;
	}	
	public Integer getActiveMembers() {
		return activeMembers;
	}
	public void setActiveMembers(Integer activeMembers) {
		this.activeMembers = activeMembers;
	}
	public List<DashboardUserDVO> getUsersMemberList() {
		return usersMemberList;
	}
	public void setUsersMemberList(List<DashboardUserDVO> usersMemberList) {
		this.usersMemberList = usersMemberList;
	}
	public String getSuccessRender() {
		return successRender;
	}
	public void setSuccessRender(String successRender) {
		this.successRender = successRender;
	}	
	public String getStepsRender() {
		return stepsRender;
	}
	
	public ArrayList<String> getUkParentalControlList() {
		if(ukParentalControlList == null){
			ukParentalControlList = new ArrayList<String>();
			ukParentalControlList.add(labelsBundle.getString("notAvailable"));
			ukParentalControlList.add(labelsBundle.getString("uku"));
			ukParentalControlList.add(labelsBundle.getString("ukpg"));
			ukParentalControlList.add(labelsBundle.getString("uk12"));
			//ukParentalControlList.add(labelsBundle.getString("uk12a"));
			ukParentalControlList.add(labelsBundle.getString("uk15"));
			ukParentalControlList.add(labelsBundle.getString("uk18"));
			ukParentalControlList.add(labelsBundle.getString("ukr18"));
		}
		return ukParentalControlList;
	}
	public void setUkParentalControlList(ArrayList<String> ukParentalControlList) {
		this.ukParentalControlList = ukParentalControlList;
	}	
	public ArrayList<String> getParentalControlMovieList() {
		if(parentalControlMovieList == null){
			parentalControlMovieList = new ArrayList<String>();
			parentalControlMovieList.add(labelsBundle.getString("notAvailable"));
			parentalControlMovieList.add(labelsBundle.getString("G"));
			parentalControlMovieList.add(labelsBundle.getString("pg"));
			parentalControlMovieList.add(labelsBundle.getString("pg-13"));
			parentalControlMovieList.add(labelsBundle.getString("r"));
			parentalControlMovieList.add(labelsBundle.getString("nc-17"));
		}
		return parentalControlMovieList;	
	}
	public void setParentalControlMovieList(ArrayList<String> parentalControlMovieList) {
		this.parentalControlMovieList = parentalControlMovieList;
	}
	public ArrayList<String> getParentalControlTVList() {
		if(parentalControlTVList == null){
			parentalControlTVList = new ArrayList<String>();		
			parentalControlTVList.add(labelsBundle.getString("notAvailable"));
			parentalControlTVList.add(labelsBundle.getString("tvy"));
			parentalControlTVList.add(labelsBundle.getString("tvy7"));
			parentalControlTVList.add(labelsBundle.getString("tvy7fv"));
			parentalControlTVList.add(labelsBundle.getString("tvpg"));
			parentalControlTVList.add(labelsBundle.getString("tv14"));
			parentalControlTVList.add(labelsBundle.getString("tvma"));
		}
		return parentalControlTVList;
	}
	public void setParentalControlTVList(ArrayList<String> parentalControlTVList) {
		this.parentalControlTVList = parentalControlTVList;
	}
	public void setStepsRender(String stepsRender) {
		this.stepsRender = stepsRender;
	}
	public SessionUtils getSessionUtils() {
		return sessionUtils;
	}
	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
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
	public HashMap getAddUserValueMap() {
		return addUserValueMap;
	}
	public void setAddUserValueMap(HashMap addUserValueMap) {
		this.addUserValueMap = addUserValueMap;
	}
	@SuppressWarnings("unchecked")
	public Boolean checkAvailabilityAction() {
		try {
			// Check if input is a valid user name format so that a RMI roundtrip can be avoided
			errorValueMap.put("usernameError", CommonValidations.userNameValidation(addUserValueMap.get("memberUserName").toString()));
			if(errorValueMap.get("usernameError").equals(errorsBundle.getString("insufficientUsername")))
				errorValueMap.put("usernameError",errorsBundle.getString("insufficientUsername_others"));
			 //if user name is in valid format then check into the server
			if ("".equals(errorValueMap.get("usernameError"))) {
				if (!ServiceLocator.getUiUserService().checkUserIDAvailablity(addUserValueMap.get("memberUserName").toString().trim())) {
					errorValueMap.remove("usernameError");
					addUserValueMap.put("userCheckFlag", "TRUE");
					return Boolean.TRUE;
				} else {
					errorValueMap.put("usernameError", errorsBundle.getString("user_already_exists"));
				}
			}
		} catch (UIDeceException e) {
			e.printStackTrace();
		}
		addUserValueMap.put("userCheckFlag", "FALSE");
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
			errorValueMap.put("emailError", CommonValidations.emailValidation(addUserValueMap.get("memberEmailAddress").toString().trim()));
			if(!"".equals(errorValueMap.get("emailError"))){
				if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("emailReq")))
					errorValueMap.put("emailError", errorsBundle.getString("memberEmailReq"));
					errorValueMap.put("emailType", "errorMsg");
					addUserValueMap.put("isValidForm", Boolean.FALSE);
			}
			else if(!ServiceLocator.getUiUserService().checkUserEmailIDAvailablity(addUserValueMap.get("memberEmailAddress").toString().trim())){
				errorValueMap.remove("emailError");
				errorValueMap.remove("emailType");
				addUserValueMap.remove("isValidForm");
			}
			else{
				errorValueMap.put("emailError",errorsBundle.getString("emailAddress_already_exists"));
				errorValueMap.put("emailType", "hintMsg");
				addUserValueMap.put("isValidForm", Boolean.FALSE);
			}
		}
		catch(UIDeceException e){
			e.printStackTrace();
		}
	}

	public boolean validateSubmittedValues(){
		boolean isValidForm = true;
		if(!validateDOB()){
			isValidForm=false;
			if(!isValidForm)
				addUserValueMap.put("isValidForm", isValidForm);
			return isValidForm;
		}
		if("STANDARD".equalsIgnoreCase(sessionUtils.getSession().getAttribute("privilege").toString())){
			if("CHILD".equals(addUserValueMap.get("ageDetails").toString())){
				errorValueMap.put("ageGroupError", errorsBundle.getString("StandardCannotAddChild"));
				isValidForm=false;
			}else if("YOUTH".equals(addUserValueMap.get("ageDetails").toString())){
				errorValueMap.put("ageGroupError", errorsBundle.getString("StandardCannotAddYouth"));
				isValidForm=false;
			}
		}
		UserDVO userDVORef=setValuesToUserDVO();
		//validate username (inside checkavailability usnername patterns also validated)	
		if(!checkAvailabilityAction()){
			isValidForm = false;
		}
		
		//Member's First Name validation
		if(!"CHILD".equals(addUserValueMap.get("ageDetails").toString())){
			if(null==userDVO.getDisplayName() || "".equals(userDVO.getDisplayName().toString())){
				errorValueMap.put("firstNameError", errorsBundle.getString("firstName_not_entered"));
				isValidForm=false;
			}else
				errorValueMap.remove("firstNameError");
		}
		//Member's Last Name validation
		if(!"CHILD".equals(addUserValueMap.get("ageDetails").toString())){
			if(null==userDVO.getSurName() || "".equals(userDVO.getSurName().toString())){
				errorValueMap.put("lastNameError", errorsBundle.getString("lastName_not_entered"));
				isValidForm=false;
			}else
				errorValueMap.remove("lastNameError");
		}
		//validate password
		if("".equals(userDVORef.getPassword().trim())){
			errorValueMap.put("passwordError", errorsBundle.getString("pass_not_entered"));
			isValidForm=false;
		}else if("".equals(addUserValueMap.get("confirmPassword").toString().trim())){
			errorValueMap.put("confirmPasswordError", errorsBundle.getString("confirm_password_not_entered"));
			isValidForm=false;
		}else if(userDVORef.getPassword().equals(addUserValueMap.get("confirmPassword"))){
			errorValueMap.put("passwordError", CommonValidations.passwordValidation(userDVORef, userDVORef.getPassword()));
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
			
		//validate terms of use checked or not	
		if(null != addUserValueMap.get("ageDetails") && (!"".equals(addUserValueMap.get("ageDetails")))){
			if("CHILD".equals(addUserValueMap.get("ageDetails").toString()) || ("YOUTH".equals(addUserValueMap.get("ageDetails").toString()) && "GB".equalsIgnoreCase(sessionUtils.getSession().getAttribute("country").toString()))){
				if (!userDVORef.getEulaPolicyAccepted()) {
					errorValueMap.put("termsOfUseError", errorsBundle.getString("terms_and_cond_userCreate_error"));
					isValidForm = false;
				}else{
				  errorValueMap.remove("termsOfUseError");
				}
			}
		}
		if(!isValidForm)
			addUserValueMap.put("isValidForm", isValidForm);
		return isValidForm;
	}

	/**
	 * Feb 24,2011.
	 * Navigate to step2 action.
	 * @return
	 */
	public String continueStep1Action(){
		if(null != sessionUtils && null != sessionUtils.getSession())
            if(null != sessionUtils.getSession().getAttribute("country")){
                  addUserValueMap.put("countryName", sessionUtils.getSession().getAttribute("country"));
            }

		if(validateSubmittedValues()){
			if("STANDARD".equalsIgnoreCase(sessionUtils.getSession().getAttribute("privilege").toString()) || !("ADULT".equals(addUserValueMap.get("ageDetails").toString()))){
				addUserValueMap.put("checkRadioFlag", false);
			}else{
				addUserValueMap.put("checkRadioFlag", true);
			}
			stepsRender="step2Page";
			return "addUserProfilePage";
		}
		return null;
	}
	
	/**
	 * Feb 21,2011.
	 * Validate Date of Birth.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateDOB(){
		//errorValueMap.remove("ageGroupError");
		String memberAgeGrp = DateUtil.getAgeGroup(addUserValueMap.get("dobMonthValue").toString(),addUserValueMap.get("dobDateValue").toString(), addUserValueMap.get("dobYearValue").toString());
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
		}
		else{
			errorValueMap.remove("ageGroupError");
			addUserValueMap.put("ageDetails",memberAgeGrp);
		}
		return isValidForm;
	}
	/**
	 * Feb 25,2011.
	 * Navigate to step3 action.
	 * @return
	 */
	public String continueStep2Action(){
		String securityAccessLevel=addUserValueMap.get("securityAccess").toString();
		if("".equals(securityAccessLevel) || null == securityAccessLevel){
			errorValueMap.put("securityAccessError",errorsBundle.getString("securityAccess_mandatory"));
			stepsRender="step2Page";
			return null;
		}else{
			errorValueMap.remove("securityAccessError");
			if("STANDARD".equalsIgnoreCase(sessionUtils.getSession().getAttribute("privilege").toString())){
				stepsRender="SAUstep3Page";
			}
			else{
				stepsRender="step3Page";
			}
			return "addUserProfilePage";
		}		
	}
	/**
	 * Feb 25,2011.
	 * Step3 action code.
	 * @return
	 */
	public String continueStep3Action(){
		try{
			//Retrieving step1 members details.
				userDVO=setValuesToUserDVO();
			//checking username is already exist or not
				if(checkAvailabilityAction()){
					
				//Retrieving Step2 Security Access levels.
					String securityAccessLevel=addUserValueMap.get("securityAccess").toString();	
					addedUserAgeClass = addUserValueMap.get("ageDetails").toString();
					if("Basic".equalsIgnoreCase(securityAccessLevel)){
						userDVO.setPrivilege(UserPrivilege.BASIC);
					}else if("Standard".equalsIgnoreCase(securityAccessLevel)){
						userDVO.setPrivilege(UserPrivilege.STANDARD);
					}else{
						userDVO.setPrivilege(UserPrivilege.FULL);
					   }			
				//Retrieving Parental controls.		
					ParentalControlsDVO parentalControlDVO=new ParentalControlsDVO();	
					if((UserPrivilege.STANDARD.equals(sessionUtils.getSession().getAttribute("privilege")))){
						if(null!=addUserValueMap.get("parentalControlsRenderFlag") && !"".equals(addUserValueMap.get("parentalControlsRenderFlag"))){
							parentalControlDVO.setParentalControlsEnabled(Boolean.valueOf(addUserValueMap.get("parentalControlsEnabled").toString()));
						}else{
							parentalControlDVO.setParentalControlsEnabled(Boolean.FALSE);
						}
					}else if(((UserPrivilege.FULL.equals(sessionUtils.getSession().getAttribute("privilege"))) && "ADULT".equalsIgnoreCase(addUserValueMap.get("ageDetails").toString()))){
						if(null==addUserValueMap.get("parentalControlsRenderFlag") || "".equals(addUserValueMap.get("parentalControlsRenderFlag")))
							parentalControlDVO.setParentalControlsEnabled(Boolean.TRUE);
						else if(addUserValueMap.get("parentalControlsRenderFlag").toString().equalsIgnoreCase("false")){
							parentalControlDVO.setParentalControlsEnabled(Boolean.FALSE);
						}else
							parentalControlDVO.setParentalControlsEnabled(Boolean.TRUE);
					}else{
						if(null!=addUserValueMap.get("parentalControlsRenderFlag") && !"".equals(addUserValueMap.get("parentalControlsRenderFlag"))){
							String flag=addUserValueMap.get("parentalControlsRenderFlag").toString();
							if("false".equals(flag))
								parentalControlDVO.setParentalControlsEnabled(Boolean.FALSE);
							else
								parentalControlDVO.setParentalControlsEnabled(Boolean.TRUE);
						}else{
							parentalControlDVO.setParentalControlsEnabled(Boolean.TRUE);
							}			
					}
					if(parentalControlDVO.isParentalControlsEnabled()){
						parentalControlDVO.setMovieRating(getMovieValuefromSlider(addUserValueMap.get("MPAAUSslider").toString()));
						parentalControlDVO.setTvRating(getTVValuefromSlider(addUserValueMap.get("TVPGUSslider").toString()));
						parentalControlDVO.setUkRating(getUkParentalCOntrolValueFromSlider(addUserValueMap.get("BBFCGBslider").toString()));
						parentalControlDVO.setCaFilmRating(getCAMovieValuefromSlider(addUserValueMap.get("CHVRSCAslider").toString()));
						parentalControlDVO.setCaTvRating(getCATvValuefromSlider(addUserValueMap.get("CBSCCAslider").toString()));
						/*addUserValueMap.put("MPAAUSslider", getMovieValuefromSlider(parentalControlDVO.getMovieRating()));
						addUserValueMap.put("TVPGUSslider", getTvValuefromSlider(parentalControlDVO.getTvRating()));
						addUserValueMap.put("BBFCGBslider", getUkParentalCOntrolValueFromSlider(parentalControlDVO.getUkRating()));
						addUserValueMap.put("CHVRSCAslider", getCAMovieParentalControlValueFromSlider(parentalControlDVO.getCaFilmRating()));
						addUserValueMap.put("CBSCCAslider", getCATvParentalControlValueFromSlider(parentalControlDVO.getCaTvRating()));*/
						parentalControlDVO.setBlockExplicitMusicVideos(Boolean.parseBoolean(addUserValueMap.get("blockExplicitMusicVideos").toString()));
						parentalControlDVO.setBlockUnratedContent(Boolean.parseBoolean(addUserValueMap.get("blockUnratedContent").toString()));
						parentalControlDVO.setBlockAdultContent(Boolean.parseBoolean(addUserValueMap.get("blockAdultContent").toString()));
					  }else{
					//Set to null for parental control if it is disabled. 		
						parentalControlDVO = null;
					  }
					  userDVO.setParentalControls(parentalControlDVO);
					  UserAuthDVO auth=sessionUtils.getUserAuth();
					  auth.setPreviousWebPage(auth.getCurrentWebPage());
					  auth.setCurrentWebPage("ADD_USER_PARENTAL_CONTROLS_PAGE");
					  UserDVO creteduser= ServiceLocator.getUiUserService().createUser(auth, userDVO);
					  //addUserValueMap.get("ageDetails").toString())))
					 userDisplayName = creteduser.getDisplayName();
					  addUserValueMap=null;
					  if(("CHILD".equals(creteduser.getUserAgeClass().toString())) || (creteduser.getCountry().equals(UICountry.GB) && "YOUTH".equals(creteduser.getUserAgeClass().toString())))  {
						  sessionUtils.getSession().setAttribute("childNodeId",creteduser.getNodeUserId());
						  //successRender="coppaSuccessStep";
						  successRender="addUserSuccessStep";
					  }else{
						  setAddUserSuccessMembersList();
						  successRender="addUserSuccessStep";  
					  }
					  return "addUserSuccessPage";
				}// end of checkAvailability if condition
			}catch (UIDeceException ex) {
				ExceptionUtils.processUIDECEException("Save Profile","ClassName:AddUserBean:continueStep3Action",ex,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			 }
			if(errorValueMap.containsKey("usernameError") || errorValueMap.containsKey("passwordError")){
				stepsRender="step1Page";
				addUserValueMap.put("isValidForm", Boolean.FALSE);
				addUserValueMap.put("termsOfUse",Boolean.valueOf(addUserValueMap.get("termsOfUse").toString()));
			}else
				stepsRender="step3Page";
			return "addUserProfilePage";
		}

	/**
	 * Mar 17 ,2011.
	 * Resend email code. 
	 * @return boolean.
	 * @exception UIDeceException.
	 */
	public boolean resendCOPPAEmail(){
		boolean resentEmail=false;
		UserAuthDVO auth=sessionUtils.getUserAuth();
		auth.setPreviousWebPage(auth.getCurrentWebPage());
		auth.setCurrentWebPage("ADD_CHILD_SUCCESS_PAGE");
		try{
			resentEmail=ServiceLocator.getUiUtilService().resendCoppaEmail(auth,sessionUtils.getSession().getAttribute("childNodeId").toString());
		}
		catch(UIDeceException ex)
		{
			ExceptionUtils.processUIDECEException("AddUser_RESENDEMAIL","ClassName:AddUserBean_MethodName:resendEmail",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return resentEmail;
	}
	
	/**
	 * Feb 28,2011.
	 * This method is used for getting the MOVIE rating value based on the integer value.
	 * @param movieSliderVal.
	 * @return 
	 */
	private ParentalControlMovieRating getMovieValuefromSlider(String movieSliderVal){
		/*if(movieSliderVal==120)	
			return ParentalControlMovieRating.G;
		else if(movieSliderVal==240)
			return ParentalControlMovieRating.PG;
		else if(movieSliderVal==360)
			return ParentalControlMovieRating.PG13;
		else if(movieSliderVal==480)
			return ParentalControlMovieRating.R;
		else if(movieSliderVal==600)
			return ParentalControlMovieRating.NC17;*/
		if(movieSliderVal.equalsIgnoreCase(String.valueOf((600/5)*5)))
			return ParentalControlMovieRating.NC17;
		else if(movieSliderVal.equalsIgnoreCase(String.valueOf((600/5)*4)))
			return ParentalControlMovieRating.R;
		else if(movieSliderVal.equalsIgnoreCase(String.valueOf((600/5)*3)))
			return ParentalControlMovieRating.PG13;
		else if(movieSliderVal.equalsIgnoreCase(String.valueOf((600/5)*2)))
			return ParentalControlMovieRating.PG;
		else if(movieSliderVal.equalsIgnoreCase(String.valueOf((600/5)*1)))
			return ParentalControlMovieRating.G;
		return null;
	}
	
	/**
	 * Feb 28,2011.
	 * This method is used for getting the TV rating value based on the integer value.
	 * @param tvSliderVal.
	 * @return
	 */
	private ParentalControlTVRating getTVValuefromSlider(String tvSliderVal){
		float tvSliderVal1=Float.parseFloat(tvSliderVal);
		float roundvalue = Math.round(tvSliderVal1);
		int tvSliderVal2=(int)(roundvalue);
		if(tvSliderVal2==86)
			return ParentalControlTVRating.TVY;
		else if(tvSliderVal2==171)
			return ParentalControlTVRating.TVY7;
		else if(tvSliderVal2==257)
			return ParentalControlTVRating.TVY7FV;
		else if(tvSliderVal2==343)
			return ParentalControlTVRating.TVMA;
		else if(tvSliderVal2==429)
			return ParentalControlTVRating.TVPG;
		else if(tvSliderVal2==514)
			return ParentalControlTVRating.TV14;
		else if(tvSliderVal2==600)
			return ParentalControlTVRating.TVG;
		return null;
	}
	
	
/*	private CATvParentalControlRating getCATvValuefromSlider(int camovievalue){
		
		if(camovievalue==100)
			return CATvParentalControlRating.CA_G;
		else if(camovievalue==200)
			return CATvParentalControlRating.CA_PG;
		else if(camovievalue==300)
			return CATvParentalControlRating.CA_14A;
		else if(camovievalue==400)
			return CATvParentalControlRating.CA_18A;
		else if(camovievalue==500)
			return CATvParentalControlRating.CA_R;
		else if(camovievalue==600)
			return CATvParentalControlRating.CA_E;
		return null;
	}*/
	
private CAFilmParentalControlRating getCAMovieValuefromSlider(String camovievalue){
		
		if(camovievalue.equalsIgnoreCase(String.valueOf(100)))
			return CAFilmParentalControlRating.CA_G;
		else if(camovievalue.equalsIgnoreCase(String.valueOf(200)))
			return CAFilmParentalControlRating.CA_PG;
		else if(camovievalue.equalsIgnoreCase(String.valueOf(300)))
			return CAFilmParentalControlRating.CA_14A;
		else if(camovievalue.equalsIgnoreCase(String.valueOf(400)))
			return CAFilmParentalControlRating.CA_18A;
		else if(camovievalue.equalsIgnoreCase(String.valueOf(500)))
			return CAFilmParentalControlRating.CA_R;
		else if(camovievalue.equalsIgnoreCase(String.valueOf(600)))
			return CAFilmParentalControlRating.CA_E;
		return null;
	}
	
	private CATvParentalControlRating getCATvValuefromSlider(String catvvalue){
		float catvvalue1=Float.parseFloat(catvvalue);
		float roundvalue = Math.round(catvvalue1);
		int catvvalue2=(int)(roundvalue);
		
		if(catvvalue2==86)
			return CATvParentalControlRating.CA_C;
		else if(catvvalue2==171)
			return CATvParentalControlRating.CA_C8;
		else if(catvvalue2==257)
			return CATvParentalControlRating.CA_G;
		else if(catvvalue2==343)
			return CATvParentalControlRating.CA_PG;
		else if(catvvalue2==429)
			return CATvParentalControlRating.CA_14;
		else if(catvvalue2==514)
			return CATvParentalControlRating.CA_18;
		else if(catvvalue2==600)
			return CATvParentalControlRating.CA_E;
		return null;
	}
	
	/*private CAFilmParentalControlRating getCAMovieValuefromSlider(int catvvalue){
		
		if(catvvalue==86)
			return CAFilmParentalControlRating.CA_C;
		else if(catvvalue==171)
			return CAFilmParentalControlRating.CA_C8;
		else if(catvvalue==257)
			return CAFilmParentalControlRating.CA_G;
		else if(catvvalue==343)
			return CAFilmParentalControlRating.CA_PG;
		else if(catvvalue==429)
			return CAFilmParentalControlRating.CA_14;
		else if(catvvalue==514)
			return CAFilmParentalControlRating.CA_18;
		else if(catvvalue==600)
			return CAFilmParentalControlRating.CA_E;
		return null;
	}*/
	
	
	private UKParentalControlRating getUkParentalCOntrolValueFromSlider(String ukSliderVal){
		if(ukSliderVal.equalsIgnoreCase(String.valueOf(100)))
			return UKParentalControlRating.UK_U;
		else if(ukSliderVal.equalsIgnoreCase(String.valueOf(200)))
			return UKParentalControlRating.UK_PG;
		else if(ukSliderVal.equalsIgnoreCase(String.valueOf(300)))
			return UKParentalControlRating.UK_12;
		/*else if(ukSliderVal==332)
			return UKParentalControlRating.UK_12A;*/
		else if(ukSliderVal.equalsIgnoreCase(String.valueOf(400)))
			return UKParentalControlRating.UK_15;
		else if(ukSliderVal.equalsIgnoreCase(String.valueOf(500)))
			return UKParentalControlRating.UK_18;
		else if(ukSliderVal.equalsIgnoreCase(String.valueOf(600)))
			return UKParentalControlRating.UK_R18;
		return null;
	}
	
	/**
	 * This method is used for getting the MOVIE rating value based on the integer value.
	 * @param movieSliderVal.
	 * @return 
	 */
	public int getMovieValuefromSlider(ParentalControlMovieRating valueOfLink) {
		int movieSlider = 0;
		if (valueOfLink.equals(ParentalControlMovieRating.G))
			movieSlider = 120;
		else if (valueOfLink.equals(ParentalControlMovieRating.PG))
			movieSlider = 240;
		else if (valueOfLink.equals(ParentalControlMovieRating.PG13))
			movieSlider = 360;
		else if (valueOfLink.equals(ParentalControlMovieRating.R))
			movieSlider = 480;
		else if (valueOfLink.equals(ParentalControlMovieRating.NC17))
			movieSlider = 600;
		return movieSlider;
	}
	
	public int getUkParentalCOntrolValueFromSlider(UKParentalControlRating valueOfLink){
		int ukValues = 0;
		if(valueOfLink.equals(UKParentalControlRating.UK_U))
			ukValues = 100;
		else if(valueOfLink.equals(UKParentalControlRating.UK_PG))
			ukValues = 200;
		else if(valueOfLink.equals(UKParentalControlRating.UK_12))
			ukValues = 300;
		/*else if(valueOfLink.equals(UKParentalControlRating.UK_12A))
			ukValues = 332;*/
		else if(valueOfLink.equals(UKParentalControlRating.UK_15))
			ukValues = 400;
		else if(valueOfLink.equals(UKParentalControlRating.UK_18))
			ukValues = 500;
		else if(valueOfLink.equals(UKParentalControlRating.UK_R18))
			ukValues = 600;
		return ukValues;
	}
	
	
	public int getCAMovieParentalControlValueFromSlider(CAFilmParentalControlRating valueOfLink) {
		int camovievalue = 0;
		if (valueOfLink.equals(CAFilmParentalControlRating.CA_G))
			camovievalue = 100;
		else if (valueOfLink.equals(CAFilmParentalControlRating.CA_PG))
			camovievalue = 200;
		else if (valueOfLink.equals(CAFilmParentalControlRating.CA_14A))
			camovievalue = 300;
		else if (valueOfLink.equals(CAFilmParentalControlRating.CA_18A))
			camovievalue = 400;
		else if (valueOfLink.equals(CAFilmParentalControlRating.CA_R))
			camovievalue = 500;
		else if (valueOfLink.equals(CAFilmParentalControlRating.CA_E))
			camovievalue = 600;
		return camovievalue;
	}
	public int getCATvParentalControlValueFromSlider(CATvParentalControlRating valueOfLink) {
		int catvvalue = 0;
		if (valueOfLink.equals(CATvParentalControlRating.CA_C))
			catvvalue = 86;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_C8))
			catvvalue = 171;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_G))
			catvvalue = 257;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_PG))
			catvvalue = 343;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_14))
			catvvalue = 429;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_18))
			catvvalue = 514;
		else if (valueOfLink.equals(CATvParentalControlRating.CA_E))
			catvvalue = 600;
		return catvvalue;
	}
	
	 /** This method is used for getting the TV rating value based on the integer value.
	 * @param tvSliderVal.
	 * @return
	 */
	public int getTvValuefromSlider(ParentalControlTVRating valueOfLink) {
		int tvSlider = 0;
		if (valueOfLink.equals(ParentalControlTVRating.TVY))
			tvSlider = 86;
		else if (valueOfLink.equals(ParentalControlTVRating.TVY7))
			tvSlider = 171;
		else if (valueOfLink.equals(ParentalControlTVRating.TVY7FV))
			tvSlider = 257;
		else if (valueOfLink.equals(ParentalControlTVRating.TVMA))
			tvSlider = 343;
		else if (valueOfLink.equals(ParentalControlTVRating.TVPG))
			tvSlider = 429;
		else if (valueOfLink.equals(ParentalControlTVRating.TV14))
			tvSlider = 514;
		else if (valueOfLink.equals(ParentalControlTVRating.TVG))
			tvSlider = 600;
		return tvSlider;
	}
	
	 /** This method is used for getting the ca movie rating value based on the integer value.
		 * @param camovievalue.
		 * @return
		 */
	/*	public int getCATvParentalControlValueFromSlider(CATvParentalControlRating valueOfLink) {
			int camovievalue = 0;
			if (valueOfLink.equals(CATvParentalControlRating.CA_G))
				camovievalue = 100;
			else if (valueOfLink.equals(CATvParentalControlRating.CA_PG))
				camovievalue = 200;
			else if (valueOfLink.equals(CATvParentalControlRating.CA_14A))
				camovievalue = 300;
			else if (valueOfLink.equals(CATvParentalControlRating.CA_18A))
				camovievalue = 400;
			else if (valueOfLink.equals(CATvParentalControlRating.CA_R))
				camovievalue = 500;
			else if (valueOfLink.equals(CATvParentalControlRating.CA_E))
				camovievalue = 600;
			return camovievalue;
		}
		public int getCAMovieParentalControlValueFromSlider(CAFilmParentalControlRating valueOfLink) {
			int catvvalue = 0;
			if (valueOfLink.equals(CAFilmParentalControlRating.CA_C))
				catvvalue = 86;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_C8))
				catvvalue = 171;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_G))
				catvvalue = 257;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_PG))
				catvvalue = 343;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_14))
				catvvalue = 429;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_18))
				catvvalue = 514;
			else if (valueOfLink.equals(CAFilmParentalControlRating.CA_E))
				catvvalue = 600;
			return catvvalue;
		}
		*/
	/**
	 * Feb 25,2011.
	 * This method is used for Listener to the Movies values.
	 * @param event
	 */
	public void parentalControlsMoviesActionListner(ActionEvent event) {
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for (UIComponent uiComponent : listUIComponents) {
			if (uiComponent instanceof HtmlOutputText) {
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
				stepsRender="step3Page";
				if (valueOfLink.equals("N/A"))
					movieSliderValue=0;
				else if (valueOfLink.equals("G"))
					movieSliderValue=115;
				else if (valueOfLink.equals("PG"))
					movieSliderValue=230;
				else if (valueOfLink.equals("PG-13"))
					movieSliderValue=345;
				else if (valueOfLink.equals("R"))
					movieSliderValue=460;
				else if (valueOfLink.equals("NC-17"))
					movieSliderValue=575;
			}
		}
	}
   /**
    * Feb25,2011.
    * This method is used for Listener to the TV Rating values.
    * @param event
    */
	@SuppressWarnings("unchecked")
	public void parentalControlsTvActionListner(ActionEvent event) {
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for (UIComponent uiComponent : listUIComponents) {
			if (uiComponent instanceof HtmlOutputText) {
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
				stepsRender="step3Page";
				if(valueOfLink.equals("N/A"))
					tvSliderValue=0;
				else if (valueOfLink.equals("TV-Y"))
					tvSliderValue=96;
				else if (valueOfLink.equals("TV-Y7"))
					tvSliderValue=192;
				else if (valueOfLink.equals("TV-Y7-FV"))
					tvSliderValue=288;
				else if (valueOfLink.equals("TV-PG"))
					tvSliderValue=384;
				else if (valueOfLink.equals("TV-14"))
					tvSliderValue=480;
				else if (valueOfLink.equals("TV-MA"))
					tvSliderValue=576;
			}
		}
	}
	
	public void ukMovieParentalControlsActionListner(ActionEvent event){
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for(UIComponent uiComponent : listUIComponents){
			if(uiComponent instanceof HtmlOutputText)
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
			stepsRender="step3Page";
				if(valueOfLink.equals("N/A"))
					ukRatingSliderValue=0;
				else if(valueOfLink.equals("U"))
					ukRatingSliderValue=96;
				else if(valueOfLink.equals("PG"))
					ukRatingSliderValue=192;
				else if(valueOfLink.equals("12"))
					ukRatingSliderValue=288;
				/*else if(valueOfLink.equals("12A"))
					ukRatingSliderValue=332;*/
				else if(valueOfLink.equals("15"))
					ukRatingSliderValue=384;
				else if(valueOfLink.equals("18"))
					ukRatingSliderValue=480;
				else if(valueOfLink.equals("R18"))
					ukRatingSliderValue=576;
		}
	}
	/**
	 * Feb 28,2011.
	 * @return
	 */
	public UserDVO setValuesToUserDVO(){
		userDVO.setCountry(UICountry.valueOf(addUserValueMap.get("countryName").toString()));
		userDVO.setBirthDate(DateUtil.getDate(addUserValueMap.get("dobMonthValue").toString(),addUserValueMap.get("dobDateValue").toString(),addUserValueMap.get("dobYearValue").toString()));
		userDVO.setUserName(addUserValueMap.get("memberUserName").toString());
		
		if(("CHILD".equals(addUserValueMap.get("ageDetails").toString()))){
			userDVO.setDisplayName(userDVO.getUserName().toString());
			userDVO.setSurName(userDVO.getUserName().toString());
		}
		else{
			userDVO.setDisplayName(addUserValueMap.get("memberFirstName").toString().trim());
			userDVO.setSurName(addUserValueMap.get("memberLastName").toString().trim());
		}
		addUserValueMap.put("memberDisplayNameTemp",userDVO.getDisplayName().toString());
		if(null != addUserValueMap.get("ageDetails") && !"".equals(addUserValueMap.get("ageDetails"))){
			//Setting Eula policy for Adult,child and youth.
			if("ADULT".equals(addUserValueMap.get("ageDetails").toString())){
				userDVO.setEulaPolicyAccepted(Boolean.FALSE);
			}else{
				userDVO.setEulaPolicyAccepted(Boolean.valueOf(addUserValueMap.get("termsOfUse").toString()));
			}
		}
		userDVO.setEmail(addUserValueMap.get("memberEmailAddress").toString().trim());
		userDVO.setPassword(addUserValueMap.get("password").toString());
		
		return userDVO;
	}	
	/**
	 *Mar 02,2011. 
	 */
	@SuppressWarnings("unchecked")
	public void setParentalControlsOfStandardUser(String str){
		if("FULL".equalsIgnoreCase(sessionUtils.getSession().getAttribute("privilege").toString())){
			if(!("ADULT".equals(addUserValueMap.get("ageDetails").toString())) && !"GB".equals(addUserValueMap.get("countryName").toString())){
				addUserValueMap.put("blockExplicitMusicVideos",Boolean.parseBoolean("true"));
				addUserValueMap.put("blockAdultContent",Boolean.parseBoolean("true"));
			}
			if(!("ADULT".equals(addUserValueMap.get("ageDetails").toString())) && "GB".equals(addUserValueMap.get("countryName").toString())){
				addUserValueMap.put("blockExplicitMusicVideos",Boolean.parseBoolean("true"));
				addUserValueMap.put("blockAdultContent",Boolean.parseBoolean("true"));
				addUserValueMap.put("blockUnratedContent",Boolean.parseBoolean("true"));
			}
			
			addUserValueMap.put("enableParentalControls", Boolean.FALSE);
			addUserValueMap.put("disableParentalControls", Boolean.TRUE);
			
			if(!("ADULT".equals(addUserValueMap.get("ageDetails").toString()))){
				addUserValueMap.put("readOnlyAdultContent", Boolean.TRUE);
			}
			
		}
		if("STANDARD".equalsIgnoreCase(sessionUtils.getSession().getAttribute("privilege").toString())){
			if("ADULT".equalsIgnoreCase(sessionUtils.getSession().getAttribute("userAge").toString())){
				addUserValueMap.put("parentalControlsDisabled","disabled");
			}else{
				try{
					UserAuthDVO auth = sessionUtils.getUserAuth();
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("ADD_USER_SECURITY_PERMISSIONS_PAGE");
					UserParentalControlsDVO userParentalControlsDVO=ServiceLocator.getUiUserService().getUserParentalControls(auth, auth.getNodeUserId());
					if (userParentalControlsDVO.getParentalControls() != null){
						addUserValueMap.put("parentalControlsDisabled","enabled");
						Boolean explicitMusicVideos=userParentalControlsDVO.getParentalControls().isBlockExplicitMusicVideos();
						Boolean unratedContent=userParentalControlsDVO.getParentalControls().isBlockUnratedContent();
						Boolean adultContent=userParentalControlsDVO.getParentalControls().isBlockAdultContent();
						Boolean parentalControlsEnabled=userParentalControlsDVO.getParentalControls().isParentalControlsEnabled();
						ParentalControlMovieRating movieValue=userParentalControlsDVO.getParentalControls().getMovieRating();
						UKParentalControlRating ukvalue=userParentalControlsDVO.getParentalControls().getUkRating();
						ParentalControlTVRating tvValue=userParentalControlsDVO.getParentalControls().getTvRating();
						CAFilmParentalControlRating moviecavalue=userParentalControlsDVO.getParentalControls().getCaFilmRating();
						CATvParentalControlRating tvcavalue=userParentalControlsDVO.getParentalControls().getCaTvRating();
						if (explicitMusicVideos!= null)
							addUserValueMap.put("blockExplicitMusicVideos",explicitMusicVideos.booleanValue());
						if (adultContent!= null)
							addUserValueMap.put("blockAdultContent",adultContent.booleanValue());
						if (unratedContent!= null)
							addUserValueMap.put("blockUnratedContent",unratedContent.booleanValue());
						if (parentalControlsEnabled != null)
							addUserValueMap.put("parentalControlsEnabled", parentalControlsEnabled);
						if (movieValue != null)
							addUserValueMap.put("MPAAUSslider",getMovieValuefromSlider(movieValue));
						if (tvValue != null)
							addUserValueMap.put("TVPGUSslider", getTvValuefromSlider(tvValue));
						if (ukvalue != null)
							addUserValueMap.put("BBFCGBslider",getUkParentalCOntrolValueFromSlider(ukvalue));
						if (moviecavalue != null)
							addUserValueMap.put("CHVRSCAslider",getCAMovieParentalControlValueFromSlider(moviecavalue));
						if (tvcavalue != null)
							addUserValueMap.put("CBSCCAslider",getCATvParentalControlValueFromSlider(tvcavalue));
					}else{
						addUserValueMap.put("parentalControlsDisabled","disabled");
					}			
				}catch (UIDeceException ex) {
					ExceptionUtils.processUIDECEException("GET PARENTAL CONTROLS OF STD ACCESS USER","ClassName:AddUserBean:getParentalControlOfSTDUser",ex,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				}
			}
		}else
			addUserValueMap.put("parentalControlsDisabled","enabled");
	}
	/**
	 * March 11,2011.
	 * Method is to display user members. 	
	 */
	private void setAddUserSuccessMembersList(){
		try{List<DashboardUserDVO> usersData;			
			UserAuthDVO  auth = sessionUtils.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("ADD_ADULT_OR_YOUTH_SUCCESS_PAGE");
			usersData=ServiceLocator.getUiAccountService().getAccountUsers(auth,sessionUtils.getAccountId());	
			this.activeMembers = usersData.size();
			for (DashboardUserDVO dashboardUserDVO:usersData){
				String str=dashboardUserDVO.getNodeUserId();				
				dashboardUserDVO.setNodeUserId(str);
				usersMemberList.add(dashboardUserDVO);
			}				
			if (6 > this.activeMembers) {
				DashboardUserDVO dashBoardUserDVO = null;
			  for(int i=0;i<(6-this.activeMembers);i++) {
				  dashBoardUserDVO=new DashboardUserDVO();
				  if(i==0){
					  dashBoardUserDVO.setDisplayName("AddUser");
				  }else{
					  dashBoardUserDVO.setDisplayName("None");  
				  }
				  usersMemberList.add(dashBoardUserDVO);
				}
	    	 }
	        setActiveMembers(6-this.activeMembers);    
		}catch (UIDeceException exe) {
			ExceptionUtils.processUIDECEException("USER_SUCCESS","ClassName:AddUserBean_MethodName:addUserSuccessMembersList",exe, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
	}
	public String goToStep1(){
		addUserValueMap.put("termsOfUse",Boolean.valueOf(addUserValueMap.get("termsOfUse").toString()));
		stepsRender="step1Page";
		return "addUserProfilePage";		
	}
	public String goToStep2(){
		stepsRender="step2Page";
		return "addUserProfilePage";		
	}

	private LinkedHashMap<String,List<RatingsDVO>> ratingMap;
		
	public LinkedHashMap<String, List<RatingsDVO>> getRatingMap() {
		if(null==ratingMap){
			ratingMap=new LinkedHashMap<String, List<RatingsDVO>>();
			FacesContext context=FacesContext.getCurrentInstance();
			HashMap<String,List<RatingsDVO>> rateMap=(HashMap)context.getExternalContext().getApplicationMap().get("parentalControlRatings");
			String country=null;
			for(Iterator<String> iterator=rateMap.keySet().iterator();iterator.hasNext();){
				String countryRating=iterator.next();
				List<RatingsDVO> ratingList=rateMap.get(countryRating);
				RatingsDVO rDvo=ratingList.get(0);
				if(rDvo.getCountry().equalsIgnoreCase(sessionUtils.getSession().getAttribute("country").toString())){
					country=rDvo.getCountryDescription();
					ratingMap.put(country, ratingList);
				}
			}
			for(Iterator<String> iterator=rateMap.keySet().iterator();iterator.hasNext();){
				String countryRating=iterator.next();
				List<RatingsDVO> ratingList=rateMap.get(countryRating);
				RatingsDVO rDvo=ratingList.get(0);
				if(!(rDvo.getCountry().equalsIgnoreCase(sessionUtils.getSession().getAttribute("country").toString()))){
					country=rDvo.getCountryDescription();
					ratingMap.put(country, ratingList);
				}
			}
		}
		return ratingMap;
	}
	public void setRatingMap(LinkedHashMap<String, List<RatingsDVO>> ratingMap) {
		this.ratingMap = ratingMap;
	}
	public int getCamovievalue() {
		return camovievalue;
	}
	public void setCamovievalue(int camovievalue) {
		this.camovievalue = camovievalue;
	}
	public int getCatvvalue() {
		return catvvalue;
	}
	public void setCatvvalue(int catvvalue) {
		this.catvvalue = catvvalue;
	}

	public TreeMap<String, String> getSupportingCountryMap() {
		return supportingCountryMap;
	}

	public void setSupportingCountryMap(TreeMap<String, String> supportingCountryMap) {
		this.supportingCountryMap = supportingCountryMap;
	}
}