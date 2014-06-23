/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * UserProfileBean.java
 */
package biz.neustar.dece.backing.bean.sections.myhousehold;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import biz.neustar.dece.backing.bean.sections.common.ConsentsInfoDVO;
import biz.neustar.dece.backing.bean.sections.common.HeaderBean;
import biz.neustar.dece.backing.bean.sections.common.UIConsentsDVO;
import biz.neustar.dece.custom.validation.AddEditFamilyCommonClass;
import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.exception.DECESSPException;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.DateUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.CLGUserDVO;
import biz.neustar.dece.ui.dvo.ConsentsStatemenstDVO;
import biz.neustar.dece.ui.dvo.ParentalControlsDVO;
import biz.neustar.dece.ui.dvo.RatingsDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserAvatarDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserLinkedAccountsDVO;
import biz.neustar.dece.ui.dvo.UserLinkedConsentDVO;
import biz.neustar.dece.ui.dvo.UserParentalControlsDVO;
import biz.neustar.dece.ui.enumeration.CAFilmParentalControlRating;
import biz.neustar.dece.ui.enumeration.CATvParentalControlRating;
import biz.neustar.dece.ui.enumeration.ParentalControlMovieRating;
import biz.neustar.dece.ui.enumeration.ParentalControlTVRating;
import biz.neustar.dece.ui.enumeration.UICountry;
import biz.neustar.dece.ui.enumeration.UKParentalControlRating;
import biz.neustar.dece.ui.enumeration.UserPrivilege;
import biz.neustar.dece.ui.service.UIPolicyService;
import biz.neustar.dece.ui.service.UIUserService;

/**
 * The <code>UserProfileBean.java</code> class encapsulates objects defined for Member Edit Scenario.
 * @author Compugain.
 * @version $Revision: 1.27 $ $Date: 2012/06/28 12:51:30 $
 */

@ManagedBean(name="userProfileBean")
@RequestScoped
public class UserProfileBean{
	
	
	private static final Logger userProfileBeanLogger = Logger.getLogger(UserProfileBean.class);
	private transient SessionUtils sessionUtils = new SessionUtils();
	private UserDVO userDVO = new UserDVO();
	private transient ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private transient ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
	private HashMap<String,String> errorValueMap;
	private HashMap<String, String> errorMap = new HashMap<String, String>();
	private HashMap userProfileValueMap=new HashMap();
	private LinkedHashMap<String, Long> secretQuestionMap = new LinkedHashMap<String, Long>();
	private Map<String, String> dobMonthDetailsList;
	private Map<String, String> dobDateDetailsList;
	private Map<String, String> dobYearDetailsList;
	private ArrayList<String> parentalControlMovieList;
	private ArrayList<String> parentalControlTVList;
	private ArrayList<String> ukMovieRatingList;
	UserAuthDVO auth=sessionUtils.getUserAuth();
	private transient UIUserService uiUserService=ServiceLocator.getUiUserService();
	private String nodeUserId="";
	private List<UserLinkedConsentDVO> userLinkedConsentDVOsList=new ArrayList<UserLinkedConsentDVO>();
	private static double aspectRatio = 200 / 130;
	private byte[] b = null;
	private String defaultAvtarUrl;
	private int movieSliderValue, tvSliderValue,ukMovieSliderValue,camovievalue,catvvalue;
	private String navFor;
	private boolean renderEditPage=true;
	private List<UIConsentsDVO> userLinkedServices;
	private String empStr="";
	private String linkNodeId="";
	private LinkedHashMap<String,List<RatingsDVO>> ratingMap;
	private TreeMap<String,String> supportingCountryMap;
	private HashMap parentalRatingsValueMap=new HashMap();
	
  
	public UserProfileBean(){
	  userProfileBeanLogger.info("UserProfileBean Constructor");
	  errorValueMap = new HashMap<String, String>();
	  supportingCountryMap = new TreeMap<String, String>();
	  gettingCountriesFromConfig();
	  HttpServletRequest httpRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	  String fullURI = httpRequest.getRequestURI();
	  if(sessionUtils.getSession().getAttribute("RSAMLParamsDVO")!=null || sessionUtils.getSession().getAttribute("fromReset")!=null){
		  if(fullURI.contains("views/userProfilePage.jsf"))
				navigateToUserProfile();
		  if(fullURI.contains("views/userParentalControlsPage.jsf"))
			  navigateToUserParentalControls();
		  if(fullURI.contains("views/userLinkedAccountsPage.jsf"))
			  navigateToUserLinkedAccounts();
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
			
	public String getLinkNodeId() {
		return linkNodeId;
	}

	public void setLinkNodeId(String linkNodeId) {
		this.linkNodeId = linkNodeId;
	}

	public String getEmpStr() {
		return empStr;
	}

	public void setEmpStr(String empStr) {
		this.empStr = empStr;
	}

	public UserDVO getUserDVO() {
		return userDVO;
	}

	public void setUserDVO(UserDVO userDVO) {
		this.userDVO = userDVO;
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
			return parentalControlMovieList;
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
			return parentalControlTVList;
		}
		return parentalControlTVList;
	}
	public void setParentalControlTVList(ArrayList<String> parentalControlTVList) {
		this.parentalControlTVList = parentalControlTVList;
	}
	
	public ArrayList<String> getUkMovieRatingList() {
		if(ukMovieRatingList == null){
			ukMovieRatingList = new ArrayList<String>();
			ukMovieRatingList.add(labelsBundle.getString("notAvailable"));
			ukMovieRatingList.add(labelsBundle.getString("uku"));
			ukMovieRatingList.add(labelsBundle.getString("ukpg"));
			ukMovieRatingList.add(labelsBundle.getString("uk12"));
			//ukMovieRatingList.add(labelsBundle.getString("uk12a"));
			ukMovieRatingList.add(labelsBundle.getString("uk15"));
			ukMovieRatingList.add(labelsBundle.getString("uk18"));
			ukMovieRatingList.add(labelsBundle.getString("ukr18"));
			return ukMovieRatingList;
		}
		return ukMovieRatingList;
	}

	public void setUkMovieRatingList(ArrayList<String> ukMovieRatingList) {
		this.ukMovieRatingList = ukMovieRatingList;
	}

	public LinkedHashMap<String, Long> getSecretQuestionMap() {
		try {
			secretQuestionMap = CommonValidations.getSecretQuestionMap("Edit and View Member");
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("USER_PROFILE","ClassName:UserProfileBean_MethodName:getSecretQuestionMap",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}

		return secretQuestionMap;
	}

	public void setSecretQuestionMap(LinkedHashMap<String, Long> secretQuestionMap) {
		this.secretQuestionMap = secretQuestionMap;
	}
	
	public HashMap<String, String> getErrorMap() {
		return errorMap;
	}
	
	public void setErrorMap(HashMap<String, String> errorMap) {
		this.errorMap = errorMap;
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
			dobDateDetailsList = DateUtil.getDobDateMap();
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

	public HashMap getUserProfileValueMap() {
		return userProfileValueMap;
	}

	public void setUserProfileValueMap(HashMap userProfileValueMap) {
		this.userProfileValueMap = userProfileValueMap;
	}
	public String getDefaultAvtarUrl() {
		return defaultAvtarUrl;
	}

	public void setDefaultAvtarUrl(String defaultAvtarUrl) {
		this.defaultAvtarUrl = defaultAvtarUrl;
	}
	public int getMovieSliderValue() {
		return movieSliderValue;
	}

	public void setMovieSliderValue(int movieSliderValue) {
		this.movieSliderValue = movieSliderValue;
	}

	public int getUkMovieSliderValue() {
		return ukMovieSliderValue;
	}

	public void setUkMovieSliderValue(int ukMovieSliderValue) {
		this.ukMovieSliderValue = ukMovieSliderValue;
	}

	public int getTvSliderValue() {
		return tvSliderValue;
	}

	public void setTvSliderValue(int tvSliderValue) {
		this.tvSliderValue = tvSliderValue;
	}
	
	@SuppressWarnings("unchecked")
	public String navigateToUserProfile(){
		try{
		String loggedInUserAge=sessionUtils.getSession().getAttribute("userAge").toString();
		String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
		userDVO = retrieveUserDVODetails();
		//sessionUtils.getSession().setAttribute("currentUserid", userDVO.getNodeUserId());
		Boolean editByClgFlag=userDVO.getEditableByCLG();
		userProfileBeanLogger.info("UserDVO");
		userProfileBeanLogger.info(userDVO.toString());
		userProfileValueMap.put("username", userDVO.getUserName());
		userProfileValueMap.put("memberFirstName",userDVO.getDisplayName());
		userProfileValueMap.put("memberLastName",userDVO.getSurName());
		userProfileValueMap.put("memberFirstName",userDVO.getDisplayName().length() < 50 ? userDVO.getDisplayName():(userDVO.getDisplayName().substring(0,50)));
		userProfileValueMap.put("emailAddress", userDVO.getEmail());
		userProfileValueMap.put("memberFirstNameTemp", userDVO.getDisplayName());
		/*userProfileValueMap.put("secretQues1", userDVO.getSecretQuestionOneId());
		userProfileValueMap.put("secretAns1", userDVO.getSecretQuestionOneAnswer());
		userProfileValueMap.put("secretQues2", userDVO.getSecretQuestionTwoId());
		userProfileValueMap.put("secretAns2", userDVO.getSecretQuestionTwoAnswer());*/
		userProfileValueMap.put("age",userDVO.getUserAgeClass());
		userProfileValueMap.put("accessPrivilege",userDVO.getPrivilege().toString());
		userProfileValueMap.put("countryName", userDVO.getCountry().toString());
		CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
		if("CHILD".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
			if(null!=clgUserDVO && (clgUserDVO.getNodeUserId().equals(auth.getNodeUserId()) || (nodeUserId.equals(auth.getNodeUserId())))){
				DateFormat dateFormatSt = new SimpleDateFormat("MMM d, yyyy");
				userProfileValueMap.put("DateOfBirth", dateFormatSt.format(userDVO.getBirthDate()));
				DateFormat dateFormatEdit = new SimpleDateFormat("MM-dd-yyyy");
				String dateFormatStEdit = dateFormatEdit.format(userDVO.getBirthDate());
				String[] dobParts = dateFormatStEdit.split("-");
				userProfileValueMap.put("dobMonthValue", dobParts[0]);
				userProfileValueMap.put("dobDateValue", dobParts[1]);
				userProfileValueMap.put("dobYearValue", dobParts[2]);
			}
		}else{
			DateFormat dateFormatSt = new SimpleDateFormat("MMM d, yyyy");
			userProfileValueMap.put("DateOfBirth", dateFormatSt.format(userDVO.getBirthDate()));
			DateFormat dateFormatEdit = new SimpleDateFormat("MM-dd-yyyy");
			String dateFormatStEdit = dateFormatEdit.format(userDVO.getBirthDate());
			String[] dobParts = dateFormatStEdit.split("-");
			userProfileValueMap.put("dobMonthValue", dobParts[0]);
			userProfileValueMap.put("dobDateValue", dobParts[1]);
			userProfileValueMap.put("dobYearValue", dobParts[2]);
		}
		sessionUtils.getSession().setAttribute("userNameChngChk", userDVO.getUserName().toString());					
		setUserDVO(userDVO);
		if("BASIC".equalsIgnoreCase(loggedInUserPrivilige)){
			if(nodeUserId.equals(auth.getNodeUserId())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				renderEditPage=true;
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("avatarEditable", true);
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
			}
			return "userProfilePage";
		}else if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
			if(("ADULT".equalsIgnoreCase(loggedInUserAge) || "YOUTH".equalsIgnoreCase(loggedInUserAge)) && "ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString()) && !"FULL".equalsIgnoreCase(userDVO.getPrivilege().toString())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", true);
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("avatarEditable", true);
			}else if(nodeUserId.equals(auth.getNodeUserId())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				renderEditPage=true;
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				if("GB".equalsIgnoreCase(userDVO.getCountry().toString()))
					userProfileValueMap.put("accessRemoveMember", false);
				else
					userProfileValueMap.put("accessRemoveMember", true);
				userProfileValueMap.put("avatarEditable", true);
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
			}
			return "userProfilePage";
		}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
			if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(auth.getNodeUserId().equals(nodeUserId))
					auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				else
					auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", true);
				//userProfileValueMap.put("dateOfBirthEditable",true);
				userProfileValueMap.put("accessLevelEditable",true);
				userProfileValueMap.put("avatarEditable", true);
				return "userProfilePage";
			}else if(clgUserDVO!=null){
				if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
					if(null!=editByClgFlag && !(Boolean.TRUE.equals(editByClgFlag))){
						renderEditPage=false;
						if("GB".equalsIgnoreCase(userDVO.getCountry().toString())){
							userProfileValueMap.put("accessRemoveMember", false);
						}else{
							userProfileValueMap.put("accessRemoveMember", true);
						}
					}else{
						renderEditPage=true;
						userProfileValueMap.put("accessRemoveMember", true);
					}
					//userProfileValueMap.put("dateOfBirthEditable",true);
					//userProfileValueMap.put("termsOfUse", true);	
					userProfileValueMap.put("accessLevelEditable",true);
					userProfileValueMap.put("avatarEditable", true);
					return "userProfilePage";
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
					renderEditPage=false;
					if("GB".equalsIgnoreCase(userDVO.getCountry().toString()))
						userProfileValueMap.put("accessRemoveMember", false);
					else
						userProfileValueMap.put("accessRemoveMember", true);
					//userProfileValueMap.put("dateOfBirthEditable",false);
					userProfileValueMap.put("accessLevelEditable",false);
					return "userProfilePage";
				}
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
				return "userProfilePage";
			}
		}
		}catch (Exception e) {
			ExceptionUtils.processException("User Profile Page","ClassName:UserProfileBean:navigateToUserProfile", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
			try {
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../views/mediaPage.jsf");
			} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
			}
		}
		return null;
	}
	
	
	/**
	 * This method is used for saving the modified user details.
	 */
	@SuppressWarnings("unchecked")
	public String saveProfileChanges(){
		try{
		userDVO=retrieveUserDVODetails();
		String tempDisplayName = "";
		tempDisplayName=userDVO.getDisplayName();
		userProfileValueMap.put("memberFirstNameTemp", userDVO.getDisplayName());
		if(null == userProfileValueMap.get("countryName") || "".equalsIgnoreCase(userProfileValueMap.get("countryName").toString())){
			userDVO.setCountry(UICountry.valueOf(userDVO.getCountry().toString()));
			userProfileValueMap.put("countryName",userDVO.getCountry());
		}
		else
			userDVO.setCountry(UICountry.valueOf(userProfileValueMap.get("countryName").toString()));
		userDVO.setUserName(userProfileValueMap.get("username").toString());		
		userDVO.setEmail(userProfileValueMap.get("emailAddress").toString().trim());
		if("CHILD".equals(userDVO.getUserAgeClass().toString())){
			userDVO.setDisplayName(userProfileValueMap.get("username").toString().trim());
			userDVO.setSurName(userProfileValueMap.get("username").toString().trim());
		}else{
			userDVO.setDisplayName(userProfileValueMap.get("memberFirstName").toString().trim());
			userDVO.setSurName(userProfileValueMap.get("memberLastName").toString().trim());
		}
		
		if(null!=userProfileValueMap.get("password") && !"".equalsIgnoreCase(userProfileValueMap.get("password").toString()))
			userDVO.setPassword(userProfileValueMap.get("password").toString());
		else
			userDVO.setPassword("");
		DateFormat dateFormatSt1 = new SimpleDateFormat("MMM d, yyyy");
		userProfileValueMap.put("DateOfBirth", dateFormatSt1.format(userDVO.getBirthDate()));
		
		if(validateSubmittedValues()){
			try {
				/*if(userProfileValueMap.get("dateOfBirthEditable").toString().equalsIgnoreCase("true")){
						userDVO.setBirthDate(DateUtil.getDate(userProfileValueMap.get("dobMonthValue").toString(),userProfileValueMap.get("dobDateValue").toString(),userProfileValueMap.get("dobYearValue").toString()));
				}*/
				DateFormat dateFormatSt = new SimpleDateFormat("MMM d, yyyy");
				userProfileValueMap.put("DateOfBirth", dateFormatSt.format(userDVO.getBirthDate()));
				DateFormat dateFormatEdit = new SimpleDateFormat("MM-dd-yyyy");
				String dateFormatStEdit = dateFormatEdit.format(userDVO.getBirthDate());
				String[] dobParts = dateFormatStEdit.split("-");
				userProfileValueMap.put("dobMonthValue", dobParts[0]);
				userProfileValueMap.put("dobDateValue", dobParts[1]);
				userProfileValueMap.put("dobYearValue", dobParts[2]);
				
				if(null!=userProfileValueMap.get("accessPrivilege").toString())
					userDVO.setPrivilege(UserPrivilege.valueOf(userProfileValueMap.get("accessPrivilege").toString()));
				if(null!=userProfileValueMap.get("ageDetails"))
				if("ADULT".equalsIgnoreCase(userProfileValueMap.get("ageDetails").toString())){
					userDVO.setLegalGuardian(null);
				}
					
				userProfileBeanLogger.info("editUserDVO User Bean Data : "+userDVO.toString());
				UserDVO updateuser = uiUserService.updateUserProfile(auth, userDVO);
				setUserDVO(updateuser);
				sessionUtils.getSession().setAttribute("userNameChngChk", userDVO.getUserName().toString());
				if(auth.getNodeUserId().equals(nodeUserId)){
					sessionUtils.getSession().setAttribute("User", userDVO.getUserName());
					sessionUtils.getSession().setAttribute("privilege", updateuser.getPrivilege());
					sessionUtils.getSession().setAttribute("userAge", updateuser.getUserAgeClass());
				}
				userProfileValueMap.put("memberFirstNameTemp", updateuser.getDisplayName());
				userProfileValueMap.put("successMsg", errorsBundle.getString("successMsg"));
				userProfileBeanLogger.info("editUserDVO User Bean Data : "+updateuser.toString());	
			}catch (UIDeceException ex) {
				ExceptionUtils.processUIDECEException("Save Profile Changes","ClassName:UserProfileBean:saveProfileChanges", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				if(errorValueMap.get("pageError")!=null && !errorValueMap.get("pageError").toString().equals(""))
					userProfileValueMap.put("isValidForm", Boolean.FALSE);
				userDVO.setDisplayName(tempDisplayName);
				if(null!=userDVO && "GB".equalsIgnoreCase(userDVO.getCountry().toString()))
				{
					Iterator<UIError> itrObj=ex.getErrors().iterator();
					UIError uiErrObj = itrObj.next();
					String errorCodeObj = uiErrObj.getErrorCode();
					if(errorCodeObj.equalsIgnoreCase("USER_PRIVILEGE_CHANGE_NOT_ALLOWED_FOR_CLG_USERS"))
						errorValueMap.put("pageError", errorsBundle.getString("USER_PRIVILEGE_CHANGE_NOT_ALLOWED_FOR_CLG_USERS_UK"));
				}
				
			}
		}
		}catch (Exception e) {
			ExceptionUtils.processException("Save Profile Changes","ClassName:UserProfileBean:saveProfileChanges", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
			try {
					FacesContext context=FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("../views/mediaPage.jsf");
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
				}
		}
		CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
		String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
		String loggedInUserAge=sessionUtils.getSession().getAttribute("userAge").toString();
		//sessionUtils.getSession().setAttribute("userNameChngChk", userDVO.getUserName().toString());
		userProfileValueMap.put("age", userDVO.getUserAgeClass().toString());
		if("BASIC".equalsIgnoreCase(loggedInUserPrivilige)){
			if(nodeUserId.equals(auth.getNodeUserId())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				renderEditPage=true;
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("avatarEditable", true);
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
			}
			
		}else if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
			if(("ADULT".equalsIgnoreCase(loggedInUserAge) || "YOUTH".equalsIgnoreCase(loggedInUserAge)) && "ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString()) && !"FULL".equalsIgnoreCase(userDVO.getPrivilege().toString())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", true);
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("avatarEditable", true);
			}else if(nodeUserId.equals(auth.getNodeUserId())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				renderEditPage=true;
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				if("GB".equalsIgnoreCase(userDVO.getCountry().toString()))
					userProfileValueMap.put("accessRemoveMember", false);
				else
					userProfileValueMap.put("accessRemoveMember", true);
				userProfileValueMap.put("avatarEditable", true);
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
			}
			
		}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
			if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(auth.getNodeUserId().equals(nodeUserId))
					auth.setCurrentWebPage("EDIT_MYPROFILE_PAGE");
				else
					auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", "true");
				//userProfileValueMap.put("dateOfBirthEditable",true);
				userProfileValueMap.put("accessLevelEditable",true);
				userProfileValueMap.put("avatarEditable", true);
				
			}else if(clgUserDVO!=null){
				if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("EDIT_ANOTHER_PROFILE_PAGE");
					renderEditPage=true;
					userProfileValueMap.put("accessRemoveMember", "true");
					//userProfileValueMap.put("dateOfBirthEditable",true);
					//userProfileValueMap.put("termsOfUse", true);	
					userProfileValueMap.put("accessLevelEditable",true);
					userProfileValueMap.put("avatarEditable", true);
					
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
					renderEditPage=false;
					if("GB".equalsIgnoreCase(userDVO.getCountry().toString()))
						userProfileValueMap.put("accessRemoveMember", false);
					else
						userProfileValueMap.put("accessRemoveMember", true);
					//userProfileValueMap.put("dateOfBirthEditable",false);
					userProfileValueMap.put("accessLevelEditable",false);
					
				}
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_PROFILE_PAGE");
				renderEditPage=false;
				
			}
		}
		return "userProfilePage";
	}
	
	/**
	 * Mar 24,2011.
	 * Validate submit values.
	 * @return
	 */
	public boolean validateSubmittedValues(){
		boolean isValidForm = true;
		//validate password
		if("".equals(userDVO.getPassword().trim()) && "".equals(userProfileValueMap.get("confirmPassword").toString().trim())){
			isValidForm=true;
		}else if("".equals(userDVO.getPassword().trim()) && !"".equals(userProfileValueMap.get("confirmPassword").toString().trim())){
			errorValueMap.put("confirmPasswordError", errorsBundle.getString("Password_confirm_password_not_same"));
			isValidForm=false;
		}else if(!"".equals(userDVO.getPassword().trim())){
			if("".equals(userProfileValueMap.get("confirmPassword").toString().trim())){
				errorValueMap.put("confirmPasswordError", errorsBundle.getString("confirm_password_req"));
				isValidForm=false;
			}else if(userDVO.getPassword().equals(userProfileValueMap.get("confirmPassword"))){
				String errorMsg=CommonValidations.passwordValidation(userDVO, userDVO.getPassword());
				if(null!=errorMsg || errorMsg!=""){
					if(errorMsg.equalsIgnoreCase(errorsBundle.getString("insufficientPassword"))){
						if(nodeUserId.equals(auth.getNodeUserId()))
							errorValueMap.put("passwordError", errorsBundle.getString("insufficientPassword_self"));
						else
							errorValueMap.put("passwordError", errorsBundle.getString("insufficientPassword"));
					}
					else if(errorMsg.equalsIgnoreCase(errorsBundle.getString("password_not_matches"))){
						if(nodeUserId.equals(auth.getNodeUserId())){
							errorValueMap.put("passwordError", errorsBundle.getString("password_not_matches_self"));
						}
						else{
							errorValueMap.put("passwordError", errorsBundle.getString("password_not_matches"));
						}
					}
					else{
						errorValueMap.put("passwordError",errorMsg);
					}
				}
				if (!"".equals(errorValueMap.get("passwordError")))
					isValidForm=false;
				else
					errorValueMap.remove("passwordError");
			}else{
				errorValueMap.put("confirmPasswordError", errorsBundle.getString("Password_confirm_password_not_same"));
				isValidForm=false;
			}
		}
		/*if(userProfileValueMap.get("dateOfBirthEditable").toString().equalsIgnoreCase("true")){
			if(validateDOB()){
				if(!(userProfileValueMap.get("ageDetails").toString().equals(userDVO.getUserAgeClass().toString()))){
					if("YOUTH".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						if("CHILD".equalsIgnoreCase(userProfileValueMap.get("ageDetails").toString())){
							errorValueMap.put("ageGroupError",errorsBundle.getString("YOUTH_CANNOT_CHANGE"));
							isValidForm=false;
						}
					}else if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						errorValueMap.put("ageGroupError",errorsBundle.getString("ADULT_CANNOT_CHANGE"));
						isValidForm=false;
					}
				}
			}else
				isValidForm=false;
		}*/
		/*if(null!=userProfileValueMap.get("termsOfUse") && null!=userProfileValueMap.get("ageDetails")){
			if("ADULT".equalsIgnoreCase(userProfileValueMap.get("ageDetails").toString()) && userProfileValueMap.get("termsOfUse").toString().equalsIgnoreCase("true")){
				errorValueMap.put("termsOfUseError", errorsBundle.getString("editPageNoNeedClgAttestationError"));
				isValidForm=false;
			}else if((!"ADULT".equalsIgnoreCase(userProfileValueMap.get("ageDetails").toString())) && (userProfileValueMap.get("termsOfUse").toString().equalsIgnoreCase("false"))){
				errorValueMap.put("termsOfUseError", errorsBundle.getString("editPageNeedClgAttestationError"));
				isValidForm=false;
			}
		}*/
		//validate username (inside checkavailability usnername patterns also validated)
		checkUserNameAvailabile();
		
		if(!(userProfileValueMap.get("userCheckFlag").toString().equalsIgnoreCase("true"))){
			isValidForm = false;
		}else{
			errorValueMap.remove("userNameAvailable");
		}
		
		//Member's First Name validation
		if(null==userDVO.getDisplayName() || "".equals(userDVO.getDisplayName().toString())){
			errorValueMap.put("firstNameError", errorsBundle.getString("firstName_not_entered"));
			isValidForm=false;
		}else
			errorValueMap.remove("firstNameError");
		//Member's Last Name validation
		if(null==userDVO.getSurName() || "".equals(userDVO.getSurName().toString())){
			errorValueMap.put("lastNameError", errorsBundle.getString("lastName_not_entered"));
			isValidForm=false;
		}else
			errorValueMap.remove("lastNameError");
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

			if(!isValidForm)
				userProfileValueMap.put("isValidForm", isValidForm);
		return isValidForm;
	}
	
	/**
	 * Mar 24,2011.
	 * Validate Date of Birth.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateDOB(){
		//errorValueMap.remove("ageGroupError");
		String memberAgeGrp = DateUtil.getAgeGroup(userProfileValueMap.get("dobMonthValue").toString(),userProfileValueMap.get("dobDateValue").toString(), userProfileValueMap.get("dobYearValue").toString());
		boolean isValidForm=true;		
		if("DOBInValid".equalsIgnoreCase(memberAgeGrp)){
			isValidForm = false;
			if(nodeUserId.equals(auth.getNodeUserId()))
				errorValueMap.put("ageGroupError", errorsBundle.getString("dob_mandatory"));
			else
				errorValueMap.put("ageGroupError", errorsBundle.getString("dob_member_mandatory"));
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
			userProfileValueMap.put("ageDetails",memberAgeGrp);
		}
		return isValidForm;
	}
	
	
	/**
	 * This method is used for checking the username is available or not
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void checkUserNameAvailabile() {
		errorMap.remove("usernameError");
		AddEditFamilyCommonClass addEditFamilyCommonClass = new AddEditFamilyCommonClass();
		boolean userCheckFlag=addEditFamilyCommonClass.checkAvailabilityAction(userProfileValueMap.get("username").toString(),errorMap,"edit",nodeUserId);
		if(userCheckFlag){
			userProfileValueMap.put("userCheckFlag","true" );
			renderEditPage=true;
		}else{
			userProfileValueMap.put("userCheckFlag","false" );
			renderEditPage=true;
			errorValueMap.put("usernameError", errorMap.get("usernameError"));
		}
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

			errorValueMap.put("emailError", CommonValidations.emailValidation(userProfileValueMap.get("emailAddress").toString().trim()));
			if(!"".equals(errorValueMap.get("emailError"))){
				if(errorValueMap.get("emailError").equalsIgnoreCase(errorsBundle.getString("emailReq")))
					errorValueMap.put("emailError", errorsBundle.getString("memberEmailReq"));
					errorValueMap.put("emailType", "errorMsg");
					userProfileValueMap.put("isValidForm", Boolean.FALSE);
			}
			else if(!ServiceLocator.getUiUserService().checkUserEmailIDAvailablity(userProfileValueMap.get("emailAddress").toString().trim())){
				errorValueMap.remove("emailError");
				errorValueMap.remove("emailType");
				userProfileValueMap.remove("isValidForm");
			}
			else{
				errorValueMap.put("emailError",errorsBundle.getString("emailAddress_already_exists"));
				errorValueMap.put("emailType", "hintMsg");
				userProfileValueMap.put("isValidForm", Boolean.FALSE);
			}
		}
		catch(UIDeceException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String navigateToUserParentalControls(){
		try{
			userDVO=retrieveUserDVODetails();
			UserParentalControlsDVO userParentalControlsDVO=uiUserService.getUserParentalControls(auth, nodeUserId);
			CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
			//String loggedInUserAge=sessionUtils.getSession().getAttribute("userAge").toString();
			String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
			setUserDVO(userDVO);
			if (null!=userParentalControlsDVO && userParentalControlsDVO.getParentalControls() != null && userParentalControlsDVO.getParentalControls().isParentalControlsEnabled()!=false) {
				userProfileValueMap.put("parentalControlsDisabled","enabled");
			}else{
				userProfileValueMap.put("parentalControlsDisabled","disabled");
			}
			Boolean explicitMusicVideos=userParentalControlsDVO.getParentalControls().isBlockExplicitMusicVideos();
			Boolean adultContent=userParentalControlsDVO.getParentalControls().isBlockAdultContent();
			Boolean unratedContent=userParentalControlsDVO.getParentalControls().isBlockUnratedContent();
			Boolean parentalControlsEnabled=userParentalControlsDVO.getParentalControls().isParentalControlsEnabled();
			/*if(userParentalControlsDVO.getParentalControls().getMovieRating() != null)
				movieSliderValue=getMovieValuefromSlider(userParentalControlsDVO.getParentalControls().getMovieRating());
			if(userParentalControlsDVO.getParentalControls().getTvRating() != null)
				tvSliderValue=getTvValuefromSlider(userParentalControlsDVO.getParentalControls().getTvRating());
			if(userParentalControlsDVO.getParentalControls().getUkRating() != null)
				ukMovieSliderValue=getUkParentalCOntrolValueFromSlider(userParentalControlsDVO.getParentalControls().getUkRating());
			if(userParentalControlsDVO.getParentalControls().getCaFilmRating() != null)
				camovievalue=getCAMovieParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaFilmRating());
			if(userParentalControlsDVO.getParentalControls().getCaTvRating() != null)
				catvvalue=getCATvParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaTvRating());*/
			
			if(userParentalControlsDVO.getParentalControls().getMovieRating() != null)
				parentalRatingsValueMap.put("MPAAUSslider", getMovieValuefromSlider(userParentalControlsDVO.getParentalControls().getMovieRating()));
			if(userParentalControlsDVO.getParentalControls().getTvRating() != null)
				parentalRatingsValueMap.put("TVPGUSslider", getTvValuefromSlider(userParentalControlsDVO.getParentalControls().getTvRating()));
			if(userParentalControlsDVO.getParentalControls().getUkRating() != null)
				parentalRatingsValueMap.put("BBFCGBslider", getUkParentalCOntrolValueFromSlider(userParentalControlsDVO.getParentalControls().getUkRating()));
			if(userParentalControlsDVO.getParentalControls().getCaFilmRating() != null)
				parentalRatingsValueMap.put("CHVRSCAslider", getCAMovieParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaFilmRating()));
			if(userParentalControlsDVO.getParentalControls().getCaTvRating() != null)
				parentalRatingsValueMap.put("CBSCCAslider",getCATvParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaTvRating()));
			
			if (explicitMusicVideos!= null)
				userProfileValueMap.put("blockExplicitMusicVideos",explicitMusicVideos.booleanValue());
			if (adultContent!= null)
				userProfileValueMap.put("blockAdultContent",adultContent.booleanValue());
			if (unratedContent!= null)
				userProfileValueMap.put("blockUnratedContent",unratedContent.booleanValue());
			if (parentalControlsEnabled != null)
				userProfileValueMap.put("parentalControlsEnabled", parentalControlsEnabled);
			userProfileValueMap.put("ageClass",userDVO.getUserAgeClass().toString());
			if("BASIC".equalsIgnoreCase(loggedInUserPrivilige) || "STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(auth.getNodeUserId().equals(nodeUserId))
					auth.setCurrentWebPage("VIEW_MY_PARENTAL_CONTROLS_PAGE");
				else
					auth.setCurrentWebPage("VIEW_ANOTHER_PARENTAL_CONTROLS_PAGE");
				userProfileValueMap.put("parentalControlsEditable",false);
				userProfileValueMap.put("parentalControlsView",true);
				return "userParentalControlsPage";
			}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
				if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					if(auth.getNodeUserId().equals(nodeUserId))
						auth.setCurrentWebPage("EDIT_MY_PARENTAL_CONTROLS_PAGE");
					else
						auth.setCurrentWebPage("EDIT_ANOTHER_PARENTAL_CONTROLS_PAGE");
					userProfileValueMap.put("parentalControlsEditable",true);
					return "userParentalControlsPage";
				}else if(null!=clgUserDVO){
					if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
						auth.setPreviousWebPage(auth.getCurrentWebPage());
						auth.setCurrentWebPage("EDIT_ANOTHER_PARENTAL_CONTROLS_PAGE");
						userProfileValueMap.put("parentalControlsEditable", true);
						if("disabled".equalsIgnoreCase(userProfileValueMap.get("parentalControlsDisabled").toString())){
							userProfileValueMap.put("blockExplicitMusicVideos",true);
							userProfileValueMap.put("blockAdultContent",true);
							if(null!=userDVO && null!=userDVO.getCountry())
								if("GB".equals(userDVO.getCountry().toString())){
									userProfileValueMap.put("blockUnratedContent",true);
								}
						}
					}else{
						auth.setPreviousWebPage(auth.getCurrentWebPage());
						auth.setCurrentWebPage("VIEW_ANOTHER_PARENTAL_CONTROLS_PAGE");
						userProfileValueMap.put("parentalControlsEditable",false);
						userProfileValueMap.put("parentalControlsView",true);
					}
					return "userParentalControlsPage";
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_PARENTAL_CONTROLS_PAGE");
					userProfileValueMap.put("parentalControlsEditable", false);
					userProfileValueMap.put("parentalControlsView",true);
					return "userParentalControlsPage";
				}
			}
		}catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("User parental controls", "ClassName:UserProfileBean:navigateToUserParentalControls", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}catch (Exception e) {
			ExceptionUtils.processException("User parental controls", "ClassName:UserProfileBean:navigateToUserParentalControls", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
			try {
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../views/mediaPage.jsf");
			} catch (IOException ioe) {
				if(userProfileBeanLogger.isDebugEnabled())   {           
					userProfileBeanLogger.debug(ioe.getMessage());
				}
			}
		}
		return null;
	}
	
	/**
	 * This method is used for saving parental controls of user.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String saveParentalControls()
	{
		userProfileValueMap.put("pcFlag", true);
		try{
			UserParentalControlsDVO userParentalControlsDVO=uiUserService.getUserParentalControls(auth, nodeUserId);
			ParentalControlsDVO parentalControlDVO=userParentalControlsDVO.getParentalControls();
			if(parentalControlDVO==null){
				parentalControlDVO=new ParentalControlsDVO();
			}
			if(null!=userProfileValueMap.get("parentalControlsEnabled"))
				parentalControlDVO.setParentalControlsEnabled(Boolean.valueOf(userProfileValueMap.get("parentalControlsEnabled").toString()));
			else{
				if(null!=userProfileValueMap.get("parentalControlsDisabled")){
					if(userProfileValueMap.get("parentalControlsDisabled").equals("enabled"))
						parentalControlDVO.setParentalControlsEnabled(Boolean.TRUE);
					else
						parentalControlDVO.setParentalControlsEnabled(Boolean.FALSE);
				}else
					parentalControlDVO.setParentalControlsEnabled(Boolean.FALSE);
			}
			userDVO.setNodeUserId(nodeUserId);
			userDVO=retrieveUserDVODetails();
			setUserDVO(userDVO);
			userProfileValueMap.put("ageClass",userDVO.getUserAgeClass().toString());
			if(parentalControlDVO.isParentalControlsEnabled()){
				userProfileValueMap.put("parentalControlsDisabled","enabled");
				parentalControlDVO.setMovieRating(getMovieValuefromSlider(parentalRatingsValueMap.get("MPAAUSslider").toString()));
				parentalControlDVO.setTvRating(getTVValuefromSlider(parentalRatingsValueMap.get("TVPGUSslider").toString()));
				parentalControlDVO.setUkRating(getUkParentalCOntrolValueFromSlider(parentalRatingsValueMap.get("BBFCGBslider").toString()));
				parentalControlDVO.setCaFilmRating(getCAMovieValuefromSlider(parentalRatingsValueMap.get("CHVRSCAslider").toString()));
				parentalControlDVO.setCaTvRating(getCATvValuefromSlider(parentalRatingsValueMap.get("CBSCCAslider").toString()));
				parentalControlDVO.setBlockExplicitMusicVideos(Boolean.parseBoolean(userProfileValueMap.get("blockExplicitMusicVideos").toString()));
				parentalControlDVO.setBlockUnratedContent(Boolean.parseBoolean(userProfileValueMap.get("blockUnratedContent").toString()));
				parentalControlDVO.setBlockAdultContent(Boolean.parseBoolean(userProfileValueMap.get("blockAdultContent").toString()));
			}else{
				userProfileValueMap.put("parentalControlsDisabled","disabled");
			}

			userProfileValueMap.put("parentalControlsEditable",true);
			UserAuthDVO auth=sessionUtils.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			if(auth.getNodeUserId().equals(nodeUserId))
				auth.setCurrentWebPage("EDIT_MY_PARENTAL_CONTROLS_PAGE");
			else
				auth.setCurrentWebPage("EDIT_ANOTHER_PARENTAL_CONTROLS_PAGE");
			userParentalControlsDVO.setParentalControls(parentalControlDVO);
			userParentalControlsDVO.setNodeUserId(nodeUserId);
			UserParentalControlsDVO updateParentalControlsDVO=uiUserService.updateUserParentalControls(auth, userParentalControlsDVO);
			userProfileValueMap.put("successMsg", errorsBundle.getString("successMsg"));
			if(null!=userParentalControlsDVO && null!=userParentalControlsDVO.getParentalControls()){
				userProfileBeanLogger.info("updateParentalControlsDVO User parental controls Data : "+updateParentalControlsDVO);
				Boolean explicitMusicVideos=updateParentalControlsDVO.getParentalControls().isBlockExplicitMusicVideos();
				Boolean adultContent=updateParentalControlsDVO.getParentalControls().isBlockAdultContent();
				Boolean unratedContent=updateParentalControlsDVO.getParentalControls().isBlockUnratedContent();
				Boolean parentalControlsEnabled=updateParentalControlsDVO.getParentalControls().isParentalControlsEnabled();
				if(userParentalControlsDVO.getParentalControls().getMovieRating() != null)
					parentalRatingsValueMap.put("MPAAUSslider", getMovieValuefromSlider(userParentalControlsDVO.getParentalControls().getMovieRating()));
				if(userParentalControlsDVO.getParentalControls().getTvRating() != null)
					parentalRatingsValueMap.put("TVPGUSslider", getTvValuefromSlider(userParentalControlsDVO.getParentalControls().getTvRating()));
				if(userParentalControlsDVO.getParentalControls().getUkRating() != null)
					parentalRatingsValueMap.put("BBFCGBslider", getUkParentalCOntrolValueFromSlider(userParentalControlsDVO.getParentalControls().getUkRating()));
				if(userParentalControlsDVO.getParentalControls().getCaFilmRating() != null)
					parentalRatingsValueMap.put("CHVRSCAslider", getCAMovieParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaFilmRating()));
				if(userParentalControlsDVO.getParentalControls().getCaTvRating() != null)
					parentalRatingsValueMap.put("CBSCCAslider",getCATvParentalControlValueFromSlider(userParentalControlsDVO.getParentalControls().getCaTvRating()));
				if (explicitMusicVideos!= null)
					userProfileValueMap.put("blockExplicitMusicVideos",explicitMusicVideos.booleanValue());
				if (adultContent!= null)
					userProfileValueMap.put("blockAdultContent",adultContent.booleanValue());
				if (unratedContent!= null)
					userProfileValueMap.put("blockUnratedContent",unratedContent.booleanValue());
				if (parentalControlsEnabled != null)
					userProfileValueMap.put("parentalControlsEnabled", parentalControlsEnabled);	
			}
			return "userParentalControlsPage";
		}catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("Save ParentalControls","ClassName:UserProfileBean:saveParentalControls",ex,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
			userProfileValueMap.put("pcFlag", false);
		}catch (Exception e) {
			ExceptionUtils.processException("Save ParentalControls","ClassName:UserProfileBean:saveParentalControls",e,errorValueMap, sessionUtils.getErrorTxtsBundle());
			userProfileValueMap.put("pcFlag", false);
			try {
					FacesContext context=FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("../views/mediaPage.jsf");
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
			}
				
		}
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

	/**
	 * This method is used for getting the TV rating value based on the integer value.
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
	}*/
	
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
	
	
	/**
	 * This method is used for getting the MOVIE rating value based on the integer value.
	 * @param movieSlider.
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
	 * This method is used for getting the TV rating value based on the integer value.
	 * @param tvSlider.
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
	
	
/*
	private CATvParentalControlRating getCATvValuefromSlider(int camovievalue){
		
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
	}
	
	private CAFilmParentalControlRating getCAMovieValuefromSlider(int catvvalue){
		
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
	}
*/	
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
	
	public void parentalControlsTvActionListner(ActionEvent event) {
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for (UIComponent uiComponent : listUIComponents) {
			if (uiComponent instanceof HtmlOutputText) {
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
				userProfileValueMap.put("parentalControlsEditable",true);
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
	
	public void parentalControlsMoviesActionListner(ActionEvent event) {
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for (UIComponent uiComponent : listUIComponents) {
			if (uiComponent instanceof HtmlOutputText) {
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
				userProfileValueMap.put("parentalControlsEditable",true);
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
	
	@SuppressWarnings("unchecked")
	public void ukMovieParentalControlsActionListner(ActionEvent event){
		List<UIComponent> listUIComponents = event.getComponent().getChildren();
		String valueOfLink = "";
		for(UIComponent uiComponent : listUIComponents){
			if(uiComponent instanceof HtmlOutputText)
				valueOfLink = ((HtmlOutputText) uiComponent).getValue().toString();
			userProfileValueMap.put("parentalControlsEditable",true);	
			if(valueOfLink.equals("N/A"))
				ukMovieSliderValue=0;
			else if(valueOfLink.equals("U"))
				ukMovieSliderValue=96;
			else if(valueOfLink.equals("PG"))
				ukMovieSliderValue=192;
			else if(valueOfLink.equals("12"))
				ukMovieSliderValue=288;
			/*else if(valueOfLink.equals("12A"))
				ukMovieSliderValue=332;*/
			else if(valueOfLink.equals("15"))
				ukMovieSliderValue=384;
			else if(valueOfLink.equals("18"))
				ukMovieSliderValue=480;
			else if(valueOfLink.equals("R18"))
				ukMovieSliderValue=576;
		}
	}
	
	
	private UserDVO retrieveUserDVODetails(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = sessionUtils.getSession();
		//nodeUserId="urn:dece:userid:org:dece:9F493CB53630681CE040007F010062B5";
		if(context.getExternalContext().getRequestParameterMap().get("frmMyHousehold:selectedUserId")!=null){
			nodeUserId = context.getExternalContext().getRequestParameterMap().get("frmMyHousehold:selectedUserId").toString();
		}
		else if(context.getExternalContext().getRequestParameterMap().get("selectedUserId")!=null){
			nodeUserId = context.getExternalContext().getRequestParameterMap().get("selectedUserId").toString();
		}
		else {
			if(null!=session.getAttribute("currUserViewId") && (null==nodeUserId || "".equals(nodeUserId)))
				nodeUserId = (String)session.getAttribute("currUserViewId");
		}
		try {
			userDVO = uiUserService.getUserProfile(auth, nodeUserId);
			session.setAttribute("currUserViewId", userDVO.getNodeUserId());
			if(session.getAttribute("fromReset")!=null)
			{
				session.removeAttribute("fromReset");
				if(null!=session.getAttribute("tokenRST"))
				session.removeAttribute("tokenRST");
			}
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("User Profile","ClassName:UserProfileBean_MethodName:retrieveUserDVODetails",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}
		return userDVO;
	}
	@SuppressWarnings("unchecked")
	public String navigateToUserLinkedAccounts(){
		try{
			userDVO = retrieveUserDVODetails();
			UserLinkedAccountsDVO userLinkedAccountsDVO=uiUserService.getUserLinkedAccounts(auth, nodeUserId);
			CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
			String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
			if(userLinkedAccountsDVO!=null && userLinkedAccountsDVO.getLinkedAccounts()!=null){
				userLinkedConsentDVOsList=userLinkedAccountsDVO.getLinkedAccounts();
				//List<UserLinkedConsentDVO> userLinkedConsentDVOsList=dummyRetrievelOfUserLinkedAccounts();  
				userProfileValueMap.put("linkedAccounts", "true");
				userDVO.setUserLinkedAccountsConsentDVOList(userLinkedConsentDVOsList);
				setLinkedAccounts(userLinkedConsentDVOsList,userDVO.getCountry());
				
			}else{
				userProfileValueMap.put("linkedAccounts", "false");
			}
			
			setUserDVO(userDVO);
			userProfileValueMap.put("age", userDVO.getUserAgeClass().toString());
			if("BASIC".equalsIgnoreCase(loggedInUserPrivilige)){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
					}else {
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
					}
				}else{
					userProfileValueMap.put("linkedAccountsEditable",false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
				}
				return "userLinkedAccountsPage";
			}else if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
					}else {
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
					}
				}else{
					userProfileValueMap.put("linkedAccountsEditable",false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
				}
				return "userLinkedAccountsPage";
			}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
				}else if(null!=clgUserDVO){
					if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
						auth.setPreviousWebPage(auth.getCurrentWebPage());
						userProfileValueMap.put("RemoveLinkedAccounts", false);
						auth.setCurrentWebPage("EDIT_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
						userProfileValueMap.put("linkedAccountsEditable", true);
						if("YOUTH".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
							userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
							userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
							userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
						}else if("CHILD".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
							userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
							userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
							userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
						}
					}else{
						auth.setPreviousWebPage(auth.getCurrentWebPage());
						auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
						userProfileValueMap.put("linkedAccountsEditable", false);
						userProfileValueMap.put("RemoveLinkedAccounts", false);
					}
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("linkedAccountsEditable", false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
				}
				return "userLinkedAccountsPage";
			}
			
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("UserLinkedAccounts","ClassName:UserProfileBean_MethodName:navigateToUserLinkedAccounts",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}catch (Exception e) {
			ExceptionUtils.processException("UserLinkedAccounts","ClassName:UserProfileBean_MethodName:navigateToUserLinkedAccounts",e, errorValueMap, sessionUtils.getErrorTxtsBundle());
			try {
					FacesContext context=FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("../views/mediaPage.jsf");
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
				}
		}
		return null;
	}
	
	private void setLinkedAccounts(List<UserLinkedConsentDVO> userLinkedConsentDVOsList,UICountry country){
		UIPolicyService uiPolicyService=ServiceLocator.getUIPolicyService();
		userLinkedServices=new ArrayList<UIConsentsDVO>();
		if(null!=userLinkedConsentDVOsList)
		for(UserLinkedConsentDVO eachLinkedServiceOfUser : userLinkedConsentDVOsList)
		{
			UIConsentsDVO serviceNode = new UIConsentsDVO();
			serviceNode.setNodeId(eachLinkedServiceOfUser.getNodeId());
			serviceNode.setNodeName(eachLinkedServiceOfUser.getNodeName());
			serviceNode.setNodeImageURL(eachLinkedServiceOfUser.getNodeImageURL());
			
			List<ConsentsInfoDVO> cntsDVO= new ArrayList<ConsentsInfoDVO>();
			ConsentsStatemenstDVO consentsStatemenstDVO=uiPolicyService.getConsentsStatementsByNodeIdAndCountry(eachLinkedServiceOfUser.getNodeId(), country);
			
			ConsentsInfoDVO nodeConsent = new ConsentsInfoDVO();
			nodeConsent.setConsentName(labelsBundle.getString("linkUvAccount"));
			nodeConsent.setConsentHdnName("LINKMYACCOUNT");
			nodeConsent.setConsentShortDesc(labelsBundle.getString("linkUvShortDesc1")+" "+eachLinkedServiceOfUser.getNodeName()+" "+labelsBundle.getString("linkUvShortDesc2"));
			nodeConsent.setConsentDesc(consentsStatemenstDVO.getLinkMyAccountsStatement());
			nodeConsent.setConsentAccepted(eachLinkedServiceOfUser.isLinkMyAccounts());
			cntsDVO.add(nodeConsent);
			
			nodeConsent = new ConsentsInfoDVO();
			nodeConsent.setConsentName(labelsBundle.getString("manageUvMember"));
			nodeConsent.setConsentHdnName("MANGEMYPROFILE");
			nodeConsent.setConsentShortDesc(labelsBundle.getString("manageUvMemberShortDesc")+" "+eachLinkedServiceOfUser.getNodeName());
			nodeConsent.setConsentDesc(consentsStatemenstDVO.getManageMyProfileStatement());
			nodeConsent.setConsentAccepted(eachLinkedServiceOfUser.isManageMyProfile());
			cntsDVO.add(nodeConsent);
			
			nodeConsent = new ConsentsInfoDVO();
			nodeConsent.setConsentName(labelsBundle.getString("recommendMedia"));
			nodeConsent.setConsentHdnName("RECOMMNEDMEDIAPDTSANDSERVICES");
			nodeConsent.setConsentShortDesc(labelsBundle.getString("recommendMediaProductsAndServicesConsent1")+" "+eachLinkedServiceOfUser.getNodeName()+" "+labelsBundle.getString("recommendMediaShortDesc"));
			nodeConsent.setConsentDesc(consentsStatemenstDVO.getRecommendMediaProductsAndServicesStatement());
			nodeConsent.setConsentAccepted(eachLinkedServiceOfUser.isRecommendMediaProductsAndServices());
			cntsDVO.add(nodeConsent);
			
			serviceNode.setConsentsObj(cntsDVO);
			userLinkedServices.add(serviceNode);
		}
	}
	public List<UserLinkedConsentDVO> dummyRetrievelOfUserLinkedAccounts(){
		List<UserLinkedConsentDVO> userLinkedConsentDVOsList=new ArrayList<UserLinkedConsentDVO>();
		for(int i=1;i<=2;i++){		
		if(i==1){
			UserLinkedConsentDVO user=new UserLinkedConsentDVO();
			user.setManageMyProfile(true);
			user.setNodeId("1");
			user.setNodeImageURL("http://172.24.5.66/images/BestBuyLogoIcon.png");
			user.setNodeName("BestBuy");
			user.setTermsOfUse(true);
			user.setRecommendMediaProductsAndServices(true);
			user.setLinkMyAccounts(true);
			userLinkedConsentDVOsList.add(user);
		 }else{
			UserLinkedConsentDVO user=new UserLinkedConsentDVO();
			user.setManageMyProfile(false);
			user.setNodeId("2");		
			user.setNodeImageURL("http://172.24.5.66/images/FyeLogoIcon.png");		
			user.setNodeName("FyeLogo");		
			user.setTermsOfUse(false);		
			user.setRecommendMediaProductsAndServices(false);		
			user.setLinkMyAccounts(false);	
			userLinkedConsentDVOsList.add(user);
		  }		
		}
		return userLinkedConsentDVOsList;
	}
	@SuppressWarnings("unchecked")
	public String saveLinkedAccounts(){
		userProfileValueMap.put("flag", true);
		try{
			String[] selectedValues=null;
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			//List<UserLinkedConsentDVO> userLinkedConsentDVOsList = dummyRetrievelOfUserLinkedAccounts();
			UserLinkedAccountsDVO userLinkedAccountsDVO=uiUserService.getUserLinkedAccounts(auth, nodeUserId);
			userLinkedConsentDVOsList =userLinkedAccountsDVO.getLinkedAccounts();
			List<UserLinkedConsentDVO> userLinkedConsentDetails=new ArrayList<UserLinkedConsentDVO>();
			for(Iterator<UserLinkedConsentDVO> iterator=userLinkedConsentDVOsList.iterator();iterator.hasNext();){
				UserLinkedConsentDVO userConsentsDVO = iterator.next();			
				selectedValues = request.getParameterValues(userConsentsDVO.getNodeId());
				if(selectedValues !=null){
					boolean manageMyProfileFlag=false;
					boolean linkMyAccountsFlag=false;
					boolean recommendFlag=false;				
					for(int index=0;index<selectedValues.length;index++){
						if("MANGEMYPROFILE".equalsIgnoreCase(selectedValues[index])){						
							manageMyProfileFlag=true;
						}
					    if("LINKMYACCOUNT".equalsIgnoreCase(selectedValues[index])){				    	
					    	linkMyAccountsFlag=true;
						}
						if("RECOMMNEDMEDIAPDTSANDSERVICES".equalsIgnoreCase(selectedValues[index])){						
							recommendFlag=true;
						}	
				    }				
					if(manageMyProfileFlag == true){
						userConsentsDVO.setManageMyProfile(true);
					}else{
						userConsentsDVO.setManageMyProfile(false);
					}
					if(linkMyAccountsFlag == true){
						userConsentsDVO.setLinkMyAccounts(true);
					}else{
						userConsentsDVO.setLinkMyAccounts(false);	
					}
					if(recommendFlag == true){
						userConsentsDVO.setRecommendMediaProductsAndServices(true);
					}else{
						userConsentsDVO.setRecommendMediaProductsAndServices(false);
					}
					
				}else{
					userConsentsDVO.setManageMyProfile(false);
					userConsentsDVO.setLinkMyAccounts(false);
					userConsentsDVO.setRecommendMediaProductsAndServices(false);
				}
				userLinkedConsentDetails.add(userConsentsDVO);
			   }
				userDVO.setNodeUserId(nodeUserId);
				userDVO=retrieveUserDVODetails();
			   userLinkedAccountsDVO.setLinkedAccounts(userLinkedConsentDetails);
			   UserLinkedAccountsDVO updatedLinkedAccounts=uiUserService.updateUserLinkedAccounts(auth, userLinkedAccountsDVO);
			   userLinkedConsentDVOsList=updatedLinkedAccounts.getLinkedAccounts();
			   userProfileBeanLogger.info("Successfull updatetion of user linked accounts."+updatedLinkedAccounts);
			   userProfileValueMap.put("successMsg", errorsBundle.getString("successMsg"));
			   setUserDVO(userDVO);
			   userProfileValueMap.put("age", userDVO.getUserAgeClass().toString());
		   }catch (UIDeceException e) {
			   ExceptionUtils.processUIDECEException("Update User Linked Accounts","ClassName:UserProfileBean_MethodName:saveLinkedAccounts",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
			   userProfileValueMap.put("flag", false);
	       }catch (Exception e) {
	    	   ExceptionUtils.processException("Update User Linked Accounts","ClassName:UserProfileBean_MethodName:saveLinkedAccounts", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
	    	   userProfileValueMap.put("flag", false);
					try {
						FacesContext context=FacesContext.getCurrentInstance();
						context.getExternalContext().redirect("../views/mediaPage.jsf");
					} catch (IOException ioe) {
						if(userProfileBeanLogger.isDebugEnabled())   {           
							userProfileBeanLogger.debug(ioe.getMessage());
						}
					}
			}
	       userDVO.setUserLinkedAccountsConsentDVOList(userLinkedConsentDVOsList);
		   setLinkedAccounts(userLinkedConsentDVOsList,userDVO.getCountry());
	       auth.setPreviousWebPage(auth.getCurrentWebPage());
		   if(nodeUserId.equals(auth.getNodeUserId()))
				auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
			else
				auth.setCurrentWebPage("EDIT_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
		   if(null!=userLinkedConsentDVOsList)
			   userProfileValueMap.put("linkedAccounts", "true");
		   else
			   userProfileValueMap.put("linkedAccounts", "false");
	   	CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
		String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
	   if("BASIC".equalsIgnoreCase(loggedInUserPrivilige)){
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			if(nodeUserId.equals(auth.getNodeUserId())){
				userProfileValueMap.put("linkedAccountsEditable",true);
				userProfileValueMap.put("RemoveLinkedAccounts", true);
				auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
				if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
				}else {
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
				}
			}else{
				userProfileValueMap.put("linkedAccountsEditable",false);
				userProfileValueMap.put("RemoveLinkedAccounts", false);
				auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
			}
			return "userLinkedAccountsPage";
		}else if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			if(nodeUserId.equals(auth.getNodeUserId())){
				userProfileValueMap.put("linkedAccountsEditable",true);
				userProfileValueMap.put("RemoveLinkedAccounts", true);
				auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
				if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
				}else {
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
				}
			}else{
				userProfileValueMap.put("linkedAccountsEditable",false);
				userProfileValueMap.put("RemoveLinkedAccounts", false);
				auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
			}
			return "userLinkedAccountsPage";
		}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
			if(nodeUserId.equals(auth.getNodeUserId())){
				userProfileValueMap.put("linkedAccountsEditable",true);
				userProfileValueMap.put("RemoveLinkedAccounts", true);
				auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
				userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
				userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
				userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
			}else if(null!=clgUserDVO){
				if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("EDIT_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("linkedAccountsEditable", true);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
					if("YOUTH".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
					}else if("CHILD".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
					}
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("linkedAccountsEditable", false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
				}
			}else{
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
				userProfileValueMap.put("linkedAccountsEditable", false);
				userProfileValueMap.put("RemoveLinkedAccounts", false);
			}
			return "userLinkedAccountsPage";
		}
	   return null;
	 }
	@SuppressWarnings("unchecked")
	public String removeLinkedAccounts(){
		try{
			String nodeid=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmUserLinkedAccounts:nodeId");
			UserLinkedAccountsDVO userLinkedAccountsDVO=uiUserService.getUserLinkedAccounts(auth, nodeUserId);
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			if(nodeUserId.equals(auth.getNodeUserId()))
				auth.setCurrentWebPage("REMOVE_LINKED_SERVICES");
			else
				auth.setCurrentWebPage("REMOVE_ANOTHER_MEMBER_LINKED_SERVICES");
			List<UserLinkedConsentDVO> userConsentsList=userLinkedAccountsDVO.getLinkedAccounts();
			if(!userConsentsList.isEmpty()){
				for(Iterator<UserLinkedConsentDVO> iterator=userConsentsList.iterator();iterator.hasNext();){
					UserLinkedConsentDVO useLinkedConsentDVO=iterator.next();
					if(useLinkedConsentDVO.getNodeId().equals(nodeid)){						
						useLinkedConsentDVO.setLinkMyAccounts(false);
						iterator.remove();
						userConsentsList.add(useLinkedConsentDVO);
						break;
					}
				}
			}
			userLinkedAccountsDVO.setLinkedAccounts(userConsentsList);
			UserLinkedAccountsDVO updatedLinkedAccountsDVO=uiUserService.updateUserLinkedAccounts(auth, userLinkedAccountsDVO);
			userDVO.setNodeUserId(nodeUserId);
			userDVO=retrieveUserDVODetails();
			if(null!=updatedLinkedAccountsDVO && null!=updatedLinkedAccountsDVO.getLinkedAccounts() && updatedLinkedAccountsDVO.getLinkedAccounts().size()!=0){
				userProfileValueMap.put("linkedAccounts", true);
				userLinkedConsentDVOsList=updatedLinkedAccountsDVO.getLinkedAccounts();
				setLinkedAccounts(userLinkedConsentDVOsList,userDVO.getCountry());
				userDVO.setUserLinkedAccountsConsentDVOList(userLinkedConsentDVOsList);
			}else
				userProfileValueMap.put("linkedAccounts", false);
			CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
			String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
			userProfileValueMap.put("age", userDVO.getUserAgeClass().toString());
			if("BASIC".equalsIgnoreCase(loggedInUserPrivilige)){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
					}else {
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
					}
				}else{
					userProfileValueMap.put("linkedAccountsEditable",false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
				}
				return "userLinkedAccountsPage";
			}else if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
					}else {
						userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
						userProfileValueMap.put("EDIT_MANGEMYPROFILE", false);
						userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
					}
				}else{
					userProfileValueMap.put("linkedAccountsEditable",false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
				}
				return "userLinkedAccountsPage";
			}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
				if(nodeUserId.equals(auth.getNodeUserId())){
					userProfileValueMap.put("linkedAccountsEditable",true);
					userProfileValueMap.put("RemoveLinkedAccounts", true);
					auth.setCurrentWebPage("EDIT_MY_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("EDIT_LINKMYACCOUNT", true);
					userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
					userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
				}else if(null!=clgUserDVO){
					if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
						auth.setPreviousWebPage(auth.getCurrentWebPage());
						userProfileValueMap.put("RemoveLinkedAccounts", false);
						auth.setCurrentWebPage("EDIT_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
						userProfileValueMap.put("linkedAccountsEditable", true);
						if("YOUTH".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
							userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
							userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
							userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", true);
						}else if("CHILD".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
							userProfileValueMap.put("EDIT_LINKMYACCOUNT", false);
							userProfileValueMap.put("EDIT_MANGEMYPROFILE", true);
							userProfileValueMap.put("EDIT_RECOMMNEDMEDIAPDTSANDSERVICES", false);
						}
					}
					
				}else{
					auth.setPreviousWebPage(auth.getCurrentWebPage());
					auth.setCurrentWebPage("VIEW_ANOTHER_MEMBER_LINKED_SERVICES_PAGE");
					userProfileValueMap.put("linkedAccountsEditable", false);
					userProfileValueMap.put("RemoveLinkedAccounts", false);
				}
				return "userLinkedAccountsPage";
			}
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("RemoveUserLinkedAccounts","ClassName:UserProfileBean_MethodName:removeLinkedAccounts",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}catch (Exception e) {
			ExceptionUtils.processException("RemoveUserLinkedAccounts","ClassName:UserProfileBean_MethodName:removeLinkedAccounts", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
				try {
					FacesContext context=FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("../views/mediaPage.jsf");
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
				}
		}
		return null;
	}
	
	
	
	/**
	 * This method is used for remove the member profile from household. 
	 * @throws DECESSPException
	 * @exception UIDeceException,Exception
	 */
	@SuppressWarnings("unchecked")
	public String removeMemberProfile(){
		try{
		userDVO=retrieveUserDVODetails();
		CLGUserDVO clgUserDVO=userDVO.getLegalGuardian();
		String loggedInUserPrivilige=sessionUtils.getSession().getAttribute("privilege").toString();
		String loggedInUserAge=sessionUtils.getSession().getAttribute("userAge").toString();
		try {
			//Added to track the Previous and next page in UserAuthDVO
			UserAuthDVO  auth = sessionUtils.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
		
			if(auth.getNodeUserId().equalsIgnoreCase(nodeUserId))
				auth.setCurrentWebPage("REMOVE_MYPROFILE");
			else
				auth.setCurrentWebPage("REMOVE_MEMBER_PROFILE");

			String dispName=userDVO.getDisplayName();
			uiUserService.deleteUser(auth,nodeUserId);
			if(auth.getNodeUserId().equalsIgnoreCase(nodeUserId)){
				sessionUtils.getSession().setAttribute("selfMemberRemoved", true);
				return new HeaderBean().signOut();
			}else{
				sessionUtils.getSession().setAttribute("RemoveMemberStatus", dispName+" has been removed from account");
				return "myHouseholdPage";
			}
		}catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("Remove Member", "removeMemberProfile", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
			if(null!=userDVO && "GB".equalsIgnoreCase(userDVO.getCountry().toString()))
			{
				Iterator<UIError> itrObj=ex.getErrors().iterator();
				UIError uiErrObj = itrObj.next();
				String errCodeObj = uiErrObj.getErrorCode();
				if(errCodeObj.equalsIgnoreCase("LEGAL_GUARDIAN_USER_CANNOT_BE_DELETED"))
					errorValueMap.put("pageError", errorsBundle.getString("LEGAL_GUARDIAN_USER_CANNOT_BE_DELETED_UK"));
			}
			
		}
		userProfileValueMap.put("username", userDVO.getUserName());
		userProfileValueMap.put("memberFirstName",userDVO.getDisplayName());
		userProfileValueMap.put("memberFirstNameTemp", userDVO.getDisplayName());
		userProfileValueMap.put("memberLastName",userDVO.getSurName());
		userProfileValueMap.put("emailAddress", userDVO.getEmail());
		userProfileValueMap.put("age",userDVO.getUserAgeClass());
		userProfileValueMap.put("accessPrivilege",userDVO.getPrivilege().toString());
		if("CHILD".equalsIgnoreCase(userProfileValueMap.get("age").toString())){
			if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId()) || (nodeUserId.equals(auth.getNodeUserId()))){
				DateFormat dateFormatSt = new SimpleDateFormat("MMM d, yyyy");
				userProfileValueMap.put("DateOfBirth", dateFormatSt.format(userDVO.getBirthDate()));
				DateFormat dateFormatEdit = new SimpleDateFormat("MM-dd-yyyy");
				String dateFormatStEdit = dateFormatEdit.format(userDVO.getBirthDate());
				String[] dobParts = dateFormatStEdit.split("-");
				userProfileValueMap.put("dobMonthValue", dobParts[0]);
				userProfileValueMap.put("dobDateValue", dobParts[1]);
				userProfileValueMap.put("dobYearValue", dobParts[2]);
			}
		}else{
			DateFormat dateFormatSt = new SimpleDateFormat("MMM d, yyyy");
			userProfileValueMap.put("DateOfBirth", dateFormatSt.format(userDVO.getBirthDate()));
			DateFormat dateFormatEdit = new SimpleDateFormat("MM-dd-yyyy");
			String dateFormatStEdit = dateFormatEdit.format(userDVO.getBirthDate());
			String[] dobParts = dateFormatStEdit.split("-");
			userProfileValueMap.put("dobMonthValue", dobParts[0]);
			userProfileValueMap.put("dobDateValue", dobParts[1]);
			userProfileValueMap.put("dobYearValue", dobParts[2]);
		}
		if("STANDARD".equalsIgnoreCase(loggedInUserPrivilige)){
			if(("ADULT".equalsIgnoreCase(loggedInUserAge) || "YOUTH".equalsIgnoreCase(loggedInUserAge)) && "ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString()) && !"FULL".equalsIgnoreCase(userDVO.getPrivilege().toString())){
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", true);
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("avatarEditable", true);
			}else if(nodeUserId.equals(auth.getNodeUserId())){
				renderEditPage=true;
				//userProfileValueMap.put("dateOfBirthEditable",false);
				userProfileValueMap.put("accessLevelEditable",false);
				userProfileValueMap.put("accessRemoveMember", true);
				userProfileValueMap.put("avatarEditable", true);
			}else{
				renderEditPage=false;
			}
		}else if("FULL".equalsIgnoreCase(loggedInUserPrivilige)){
			if("ADULT".equalsIgnoreCase(userDVO.getUserAgeClass().toString())){
				renderEditPage=true;
				userProfileValueMap.put("accessRemoveMember", "true");
				//userProfileValueMap.put("dateOfBirthEditable",true);
				userProfileValueMap.put("accessLevelEditable",true);
				userProfileValueMap.put("avatarEditable", true);
			}else if(clgUserDVO!=null){
				if(clgUserDVO.getNodeUserId().equals(auth.getNodeUserId())){
					renderEditPage=true;
					userProfileValueMap.put("accessRemoveMember", "true");
					//userProfileValueMap.put("dateOfBirthEditable",true);
					//userProfileValueMap.put("termsOfUse", true);	
					userProfileValueMap.put("accessLevelEditable",true);
					userProfileValueMap.put("avatarEditable", true);
				}else{
					renderEditPage=false;
					userProfileValueMap.put("accessRemoveMember", "true");
					//userProfileValueMap.put("dateOfBirthEditable",false);
					userProfileValueMap.put("accessLevelEditable",false);
				}
			}
		}
		}catch (Exception e) {
			ExceptionUtils.processException("Remove Member", "removeMemberProfile", e, errorValueMap, sessionUtils.getErrorTxtsBundle());
			if(auth.getNodeUserId().equals(nodeUserId)){
				try {
					FacesContext context=FacesContext.getCurrentInstance();
					if(null!=context.getExternalContext().getApplicationMap().get("portalglobalUrl"))
					{
						//PortalGlobalUrls portalGlobal = (PortalGlobalUrls) context.getExternalContext().getApplicationMap().get("portalglobalUrl");
						context.getExternalContext().redirect(getHomePageURL());
					}
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
				}
			}else{
				try {
					FacesContext context=FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("../views/mediaPage.jsf");
				} catch (IOException ioe) {
					if(userProfileBeanLogger.isDebugEnabled())   {           
						userProfileBeanLogger.debug(ioe.getMessage());
					}
				}
				
			}
				
		}
		return "userProfilePage";
	}
	
	
	
	/**
	 * March 29,2011.
	 * This method is used for Listener to upload picture.
	 * @param event
	 * @throws Exception
	 */
	public void fileUploadListner(FileUploadEvent event) throws Exception {
		UploadedFile item = event.getUploadedFile();
		InputStream in=item.getInputStream();
        ImageInputStream iin = ImageIO.createImageInputStream(in);
        ImageReader reader = ImageIO.getImageReaders(iin).next();
        reader.setInput(iin, true, true);
        BufferedImage original = reader.read(0);
        BufferedImage scaled;
        scaled = awtScaleImage(original);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(scaled, "gif", baos);
//		ImageIO.write(scaled, "jpg", baos);
//		ImageIO.write(scaled, "png", baos);
//		ImageIO.write(scaled, "bmp", baos);
		
		 b = baos.toByteArray();
		if (b != null && b.length > 0) {
			sessionUtils.getSession().removeAttribute("bytesOfImage");
			sessionUtils.getSession().setAttribute("bytesOfImage", b);
			try{
			userDVO=retrieveUserDVODetails();
			}catch (Exception e) {
				ExceptionUtils.processException("File Upload Listener", "CalssName:UserProfileBean:fileUploadListner", e, errorValueMap, errorsBundle);	
					try {
						FacesContext context=FacesContext.getCurrentInstance();
						context.getExternalContext().redirect("../views/mediaPage.jsf");
					} catch (IOException ioe) {
						if(userProfileBeanLogger.isDebugEnabled())   {           
							userProfileBeanLogger.debug(ioe.getMessage());
						}
					}
			}
			renderEditPage=true;
		}
	}
	/**
	 * This method is used for scaling the image.
	 * @param image
	 * @return
	 */
	static BufferedImage awtScaleImage(BufferedImage image) {
		float w = (float)image.getWidth();
		float h = (float)image.getHeight();
		double imageAspectRatio = w / h;
		float scaleFactor = 1.0f;
		if (imageAspectRatio > aspectRatio) {
			scaleFactor = ((float) 200 / (float) w);
		} else {
			scaleFactor = ((float) 130 / (float) h);
		}
		if (scaleFactor > 1)
			return image;
		w =  (w * scaleFactor);
		h = (h * scaleFactor);
		ImageProducer p = new FilteredImageSource(image.getSource(),
				new AreaAveragingScaleFilter((int)w, (int)h));
		java.awt.Canvas c = new java.awt.Canvas();
		Image i = c.createImage(p);
		image = new BufferedImage((int)w, (int)h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(i, null, null);
		g.dispose();
		i.flush();
		return image;
	}
	

	
	@SuppressWarnings("unused")
	public void saveAvatarChange(){
		String avatarUrlValue;
		FacesContext context = FacesContext.getCurrentInstance();
		if(context.getExternalContext().getRequestParameterMap().get("frmEditMember:avatarUrlValue")!=null && !"".equals(context.getExternalContext().getRequestParameterMap().get("frmEditMember:avatarUrlValue")) && !"clear".equals(context.getExternalContext().getRequestParameterMap().get("frmEditMember:avatarUrlValue"))){
			try {
				avatarUrlValue = context.getExternalContext().getRequestParameterMap().get("frmEditMember:avatarUrlValue").toString();
				userDVO=retrieveUserDVODetails();
				userDVO.setDisplayImageUrl(avatarUrlValue);
				auth.setPreviousWebPage(auth.getPreviousWebPage());
				if(auth.getNodeUserId().equals(nodeUserId))
					auth.setCurrentWebPage("AVATAR_FOR_MY_PROFILE");
				else
					auth.setCurrentWebPage("AVATAR_FOR_MEMBER_PROFILE");
				userDVO = uiUserService.updateUserProfile(auth, userDVO);
			}catch (UIDeceException e) {
				ExceptionUtils.processUIDECEException("save avatar", "saveAvatarChange", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				userDVO.setDisplayImageUrl(null);
			}
			catch (Exception ex){
				ExceptionUtils.processException("updateAvatarResponse", "saveAvatarChange", ex, errorValueMap, sessionUtils.getErrorTxtsBundle());
			}
		}else if(null!=(byte[])sessionUtils.getSession().getAttribute("bytesOfImage") && !"clear".equals(context.getExternalContext().getRequestParameterMap().get("frmEditMember:avatarUrlValue"))){
		userDVO=retrieveUserDVODetails();
		UserAvatarDVO uAvatar=userDVO.getUserAvatarDVO();
		if(uAvatar==null){
			uAvatar=new UserAvatarDVO();
			uAvatar.setNodeUserOid(userDVO.getNodeUserId());
			uAvatar.setUpdatedDate(new Date());
		}
		uAvatar.setUserAvatarImg((byte[])sessionUtils.getSession().getAttribute("bytesOfImage"));
		String updateAvatarResponse="";
		try {
			auth.setPreviousWebPage(auth.getPreviousWebPage());
			if(auth.getNodeUserId().equals(nodeUserId))
				auth.setCurrentWebPage("AVATAR_FOR_MY_PROFILE");
			else
				auth.setCurrentWebPage("AVATAR_FOR_MEMBER_PROFILE");
			updateAvatarResponse = uiUserService.updateAvatar(auth, uAvatar);
			userDVO=retrieveUserDVODetails();
			renderEditPage=true;
			userProfileBeanLogger.info("Message post avatar Save: "+updateAvatarResponse);
			
		} catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("save avatar", "saveAvatarChange", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}
		catch (Exception ex){
			ExceptionUtils.processException("updateAvatarResponse", "saveAvatarChange", ex, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}
		sessionUtils.getSession().removeAttribute("bytesOfImage");
		}else{
			try{
			userDVO=retrieveUserDVODetails();
			userDVO.setDisplayImageUrl(null);
			auth.setPreviousWebPage(auth.getPreviousWebPage());
			if(auth.getNodeUserId().equals(nodeUserId))
				auth.setCurrentWebPage("AVATAR_FOR_MY_PROFILE");
			else
				auth.setCurrentWebPage("AVATAR_FOR_MEMBER_PROFILE");
			userDVO = uiUserService.updateUserProfile(auth, userDVO);
			}catch (UIDeceException e) {
				ExceptionUtils.processUIDECEException("save avatar", "saveAvatarChange", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
			}
			catch (Exception ex){
				ExceptionUtils.processException("updateAvatarResponse", "saveAvatarChange", ex, errorValueMap, sessionUtils.getErrorTxtsBundle());
			}
		}
	}
	
	public void cancelAvatarChange(){		
		sessionUtils.getSession().removeAttribute("bytesOfImage");
	}
	
	public String getNodeUserId() {
		return nodeUserId;
	}

	public void setNodeUserId(String nodeUserId) {
		this.nodeUserId = nodeUserId;
	}

	public String getNavFor() {
		return navFor;
	}

	public void setNavFor(String navFor) {
		this.navFor = navFor;
	}

	public void setRenderEditPage(boolean renderEditPage) {
		this.renderEditPage = renderEditPage;
	}

	public boolean isRenderEditPage() {
		return renderEditPage;
	}

	public void setUserLinkedServices(List<UIConsentsDVO> userLinkedServices) {
		this.userLinkedServices = userLinkedServices;
	}

	public List<UIConsentsDVO> getUserLinkedServices() {
		return userLinkedServices;
	}

	public void setUserLinkedConsentDVOsList(
			List<UserLinkedConsentDVO> userLinkedConsentDVOsList) {
		this.userLinkedConsentDVOsList = userLinkedConsentDVOsList;
	}

	public List<UserLinkedConsentDVO> getUserLinkedConsentDVOsList() {
		return userLinkedConsentDVOsList;
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

	public TreeMap<String, String> getSupportingCountryMap() {
		return supportingCountryMap;
	}

	public void setSupportingCountryMap(TreeMap<String, String> supportingCountryMap) {
		this.supportingCountryMap = supportingCountryMap;
	}

	public HashMap getParentalRatingsValueMap() {
		return parentalRatingsValueMap;
	}

	public void setParentalRatingsValueMap(HashMap parentalRatingsValueMap) {
		this.parentalRatingsValueMap = parentalRatingsValueMap;
	}
}