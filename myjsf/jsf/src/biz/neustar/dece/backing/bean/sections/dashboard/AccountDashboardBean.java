/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * UserDashBoardBean.java
 */
package biz.neustar.dece.backing.bean.sections.dashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.login.LoginNavigationBean;
import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.UIError;
import biz.neustar.dece.ui.dvo.ChildYouthUserDVO;
import biz.neustar.dece.ui.dvo.DashboardUserDVO;
import biz.neustar.dece.ui.dvo.FavoriteMediaDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;
import biz.neustar.dece.ui.service.UIUserService;

/**
 * The <code>UserDashBoardBean.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.14 $ $Date: 2012/06/19 13:04:46 $
 */
@ManagedBean(name="accntDashboardBean")
@RequestScoped
public class AccountDashboardBean {
	
	private static final Logger accntDashBoardBeanLogger = Logger.getLogger(AccountDashboardBean.class);
	
	private String toShowPopupOf;
	
	private String password;
	
	private String confirmPassword;
	
	private String householdDispName;
	
	private List<DashboardUserDVO> householdMembers;
	
	private List<FavoriteMediaDVO> myFavorites;
	
	private HashMap<String,String> accountDashBoardValueMap=new HashMap<String,String>();
	
	private ResourceBundle errorsBundle = (new SessionUtils()).getErrorTxtsBundle();
	
	public HashMap<String,String> popUpErrorValueMap = new HashMap<String, String>();
	
	public HashMap<String,String> errorValueMap= new HashMap<String, String>();
	
	private Boolean popupRenderer=Boolean.FALSE;
	
	private SessionUtils sessionUtilsObj = new SessionUtils();
	
	private HttpSession session = sessionUtilsObj.getSession();
	
	private LinkedHashMap<String, Long> secretQuestionMap;
	private UserDVO userDVO = new UserDVO();
    public HashMap userDetailsMap=new HashMap();
	
		
	FacesContext fc = FacesContext.getCurrentInstance();
	
	private int householdCapacity;
	
	@PostConstruct
	public void init(){
		sessionUtilsObj.getSession().setAttribute("devNamLen", "4");
	}
	
	public String getToShowPopupOf() {
		return toShowPopupOf;
	}

	public void setToShowPopupOf(String toShowPopupOf) {
		this.toShowPopupOf = toShowPopupOf;
	}
	public LinkedHashMap<String, Long> getSecretQuestionMap() {
		
		return secretQuestionMap;
	}
	public HashMap getUserDetailsMap() {
		return userDetailsMap;
	}

	public void setUserDetailsMap(HashMap userDetailsMap) {
		this.userDetailsMap = userDetailsMap;
	}

	public void setSecretQuestionMap(LinkedHashMap<String, Long> secretQuestionMap) {
		this.secretQuestionMap = secretQuestionMap;
	}
	
	public ResourceBundle getErrorsBundle() {
		return errorsBundle;
	}

	public void setErrorsBundle(ResourceBundle errorsBundle) {
		this.errorsBundle = errorsBundle;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPopupRenderer() {
		return popupRenderer;
	}

	public void setPopupRenderer(Boolean popupRenderer) {
		this.popupRenderer = popupRenderer;
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

	public AccountDashboardBean() {
		SessionUtils su = new SessionUtils();
		if(null!=su){
			toShowPopupOf=(null== su.getSession().getAttribute("toShowPopupOf"))?"":su.getSession().getAttribute("toShowPopupOf").toString();
			getUserDetailsForQuestions();
		}
		try {
			secretQuestionMap=CommonValidations.getSecretQuestionMap("secretQuestionsPopUp");
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("HOUSEHOLD_REGISTRATION","ClassName:HouseholdRegistrationBean_MethodName:getSecqsMap",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
	
	}
	
	public String removePopupAttribute(){
		SessionUtils sessionObj = new SessionUtils();
		sessionObj.getSession().removeAttribute("ADMIN_POPUP");
		sessionObj.getSession().removeAttribute("toShowPopupOf");
		UserSignInDVO userSignInDVO=(UserSignInDVO)sessionObj.getSession().getAttribute("SignIn");
		userSignInDVO.setNextPage(UINextPageEnum.DASHBOARD);
		sessionObj.getSession().setAttribute("SignIn", userSignInDVO);
		LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
		String retPath=loginNavigationBean.loginAction();
		return retPath;
	}

	public String popupRefresh(){
		SessionUtils sessionUtils=new SessionUtils();
		UserSignInDVO userSignInDVO=(UserSignInDVO)sessionUtils.getSession().getAttribute("SignIn");
		String retPath=null;
		if(userSignInDVO.getNextPage().equals(UINextPageEnum.DASHBOARD_EMAIL_NOT_VERIFIED) && null==sessionUtils.getSession().getAttribute("ADMIN_POPUP")){
			sessionUtils.getSession().removeAttribute("toShowPopupOf");
			List<ChildYouthUserDVO> childYouthList=userSignInDVO.getChildYouthUserList();
			if(null!=childYouthList){
				if(childYouthList.size()>0)
					{
						sessionUtils.getSession().setAttribute("ADMIN_POPUP", "YES");
						sessionUtils.getSession().setAttribute("toShowPopupOf", "ADMIN_POPUP");
						accntDashBoardBeanLogger.info("--ADMIN_POPUP--");
					}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				retPath=loginNavigationBean.loginAction();
			}else{
				userSignInDVO.setNextPage(UINextPageEnum.DASHBOARD);
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				retPath=loginNavigationBean.loginAction();
			}
		}
		else if(userSignInDVO.getNextPage().equals(UINextPageEnum.DASHBOARD_EMAIL_NOT_VERIFIED) && null!=sessionUtils.getSession().getAttribute("ADMIN_POPUP")){
			userSignInDVO.setNextPage(UINextPageEnum.DASHBOARD_TOU_OR_COPPA_ON_BEHALF_OF_CHILD_OR_YOUTH);
			sessionUtils.getSession().setAttribute("SignIn", userSignInDVO);
			LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
			retPath=loginNavigationBean.loginAction();
		}else if(userSignInDVO.getNextPage().equals(UINextPageEnum.DASHBOARD_TOU_OR_COPPA_ON_BEHALF_OF_CHILD_OR_YOUTH) && null!=sessionUtils.getSession().getAttribute("ADMIN_POPUP")){
			sessionUtils.getSession().setAttribute("SignIn", userSignInDVO);
			LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
			retPath=loginNavigationBean.loginAction();
		}
		return retPath;
	}

	public void setHouseholdDispName(String householdDispName) {
		this.householdDispName = householdDispName;
	}

	public String getHouseholdDispName() {
		if(null==householdDispName || "".equals(householdDispName)){
			 UserSignInDVO userDVO= (UserSignInDVO) sessionUtilsObj.getSession().getAttribute("SignIn");
			 householdDispName=userDVO.getAccountName();
			 sessionUtilsObj.getSession().setAttribute("householdName", householdDispName);
			 sessionUtilsObj.getSession().setAttribute("viewFormName", "frmAccountDashboard");				
		}
		return householdDispName;
	}

	public void setHouseholdMembers(List<DashboardUserDVO> householdMembers) {
		this.householdMembers = householdMembers;
	}

	public List<DashboardUserDVO> getHouseholdMembers() {
		return householdMembers;
	}
	
	public void retriveHouseholdMemebers(){
		if(null==householdMembers || householdMembers.isEmpty()){
			try {
				householdMembers = ServiceLocator.getUiAccountService().getAccountUsers(sessionUtilsObj.getUserAuth(), sessionUtilsObj.getAccountId());
				for(DashboardUserDVO dd:householdMembers)
				{
					dd.setNodeUserId(dd.getNodeUserId());
				}
				int currentSize=householdMembers.size();
				if(currentSize<6){
					String userPriv = sessionUtilsObj.getSession().getAttribute("privilege").toString();
					String userAge = sessionUtilsObj.getSession().getAttribute("userAge").toString();
					int availableSpace = 6-currentSize;
					for(int itr=1;itr<=availableSpace;itr++){
						DashboardUserDVO fillUpObj = new DashboardUserDVO();
						if(itr==1 && !userPriv.equals("BASIC") && !userAge.equalsIgnoreCase("CHILD"))
							fillUpObj.setNodeUserId("add");
						else
							fillUpObj.setNodeUserId("placeholder");
						householdMembers.add(fillUpObj);
					}
				}
			} catch (UIDeceException e) {
				ExceptionUtils.processUIDECEException("AccountDashBoardBean","ClassName:AccountDashBoardBean_MethodName:RetriveHouseholdMemebers()",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
			}
		}
	}

	public List<FavoriteMediaDVO> getMyFavorites() {
		
		try {
			myFavorites=null;
			myFavorites=ServiceLocator.getUiMediaService().getFavoriteMedia(sessionUtilsObj.getUserAuth());
			myFavorites = (myFavorites.size()>0) ? myFavorites:null;
		} catch (UIDeceException e) {
			e.printStackTrace();
		}		
		return myFavorites;
		
	}

	public void removeTitleFromFavorites()
	{
		String mediaTitleId=null;
		Object paramVal=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mediaTitleId");
		mediaTitleId=(null==paramVal)?"":paramVal.toString();
		try {
			ServiceLocator.getUiMediaService().removeMyFavoriteMedia(sessionUtilsObj.getUserAuth(), mediaTitleId);
		} catch (UIDeceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to process the submit action in the reset password pop-up 
	 * @return
	 */
	public String popupSubmit(){
		popupRenderer=Boolean.FALSE;
		
		if(validateFieldsinPopup()){
			try {		
				if(null!=session)
				{
					FacesContext context=FacesContext.getCurrentInstance();
					 if(null!=session.getAttribute("User") && null!=session.getAttribute("token"))
						accntDashBoardBeanLogger.info("Post password vlaidation, userName: "+session.getAttribute("User").toString() +"     token: "+session.getAttribute("token").toString()+"   password Length: "+password.length());
					    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
						UserSignInDVO usd  = ServiceLocator.getUiLoginService().resetPassword(session.getAttribute("User").toString(), session.getAttribute("token").toString(),password,request.getRemoteHost());
						session.removeAttribute("token");
						session.setAttribute("tokenRST", "yes");
						session.setAttribute("targetURL", "RESETPWD");
						session.setAttribute("currUserViewId", usd.getNodeUserId());
						accntDashBoardBeanLogger.info("Post password change");
						if(session.getAttribute("userLocale") == null){
							sessionUtilsObj.localeSet(null);
						}
						LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
						loginNavigationBean.setDetailsIntoSession(usd,session);
						if(!"ADULT".equalsIgnoreCase(usd.getUserAgeClass().toString()) && usd.getNextPage().equals(UINextPageEnum.TOU_SELF)){
	                    	 session.setAttribute("clgUser", usd.getUserName().trim());
	                    	 session.setAttribute("dispFor","TOU_NOT_ACCEPTED_BY_CLG");
	                    	 try{
	           	              		context.getExternalContext().redirect("../public/pendingUserMessagePage.jsf");
	           	             }catch (IOException ioe) {
		           	              if(accntDashBoardBeanLogger.isDebugEnabled())   {           
		           	            	accntDashBoardBeanLogger.debug(ioe.getMessage());
		           	              }
	           	             }
	                     } else{
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
			} catch (UIDeceException ex) {
				accntDashBoardBeanLogger.info("Exception in password change");
				ExceptionUtils.processUIDECEException("CONFIRM_MY_DEATILS","ClassName:ConfirmMyDetailsBean_MethodName:popupSubmit()",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
				Iterator<UIError> itrObj=ex.getErrors().iterator();
				while(itrObj.hasNext())
				{
					UIError uiErrObj = itrObj.next();
					String errorCodeObj = uiErrObj.getErrorCode();
					if(errorCodeObj.equalsIgnoreCase("ACCOUNT_USER_PASSWORD_NOT_VALID")){
						popUpErrorValueMap.put("passwordError", errorsBundle.getString("password_contains_account_info"));
					}
				}
				popupRenderer=Boolean.TRUE;				
			} 
		}
		return null;
	}
	
	/**
	 * method to validate the reset password pop-up fields 
	 * @return
	 */
	private boolean validateFieldsinPopup(){
		boolean isValid = false;
		accntDashBoardBeanLogger.info("validateFieldsinPopup: "+password);
		if("".equals(password.trim()) || null==password){
			popUpErrorValueMap.put("passwordError", errorsBundle.getString("pass_not_entered"));
			isValid = false;
		}else if("".equals(confirmPassword.trim()) || null==confirmPassword){
			popUpErrorValueMap.put("confirmPasswordError", errorsBundle.getString("conf_your_pwd_not_entered"));
			isValid = false;
		}else if(password.trim().equals(confirmPassword.trim())){
			popUpErrorValueMap.put("passwordError", CommonValidations.passwordValidation(password));
			if(null!=popUpErrorValueMap.get("passwordError") && !"".equals(popUpErrorValueMap.get("passwordError").toString())){
				isValid = false;
			}
			else{
				popUpErrorValueMap.remove("passwordError");
				isValid = true;
			}
			/*UserAuthDVO auth=new SessionUtils().getUserAuth();
			try {
				UserDVO userDVO=ServiceLocator.getUiUserService().getUserProfile(auth, auth.getNodeUserId());
			
			String passworderror = CommonValidations.passwordValidation(userDVO, password);
			accntDashBoardBeanLogger.info("passworderror: "+passworderror);
			if (!"".equals(passworderror)) {
				isValid=false;
				popUpErrorValueMap.put("passwordError", passworderror);				
			}
			else isValid=true;
			} catch (UIDeceException e) {
				e.printStackTrace();
			}*/
		}
		else{
			popUpErrorValueMap.put("confirmPasswordError",errorsBundle.getString("Password_confirm_password_not_same") );
			isValid=false;
		}
		return isValid;
	}
	
    public boolean validateSecretQuestions() {
    	boolean isValidForm = true;	
	    if(0L==userDVO.getSecretQuestionOneId()){
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
			errorValueMap.remove("secAns2Error");
		return isValidForm;
    }

    public void remaindSecretQuestions()
    {
        UIUserService uiUserService=ServiceLocator.getUiUserService();  
          try {
        	  	if(null!=session.getAttribute("Auth"))
        	  	{
	        	    UserSignInDVO userSignInDVO=uiUserService.skipUserSecurityQuestions((UserAuthDVO)session.getAttribute("Auth"));
	        	  	session.removeAttribute("toShowPopupOf");
	  				session.removeAttribute("ADMIN_POPUP");
	  				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();	
	  				loginNavigationBean.setDetailsIntoSession(userSignInDVO,session);
	  				loginNavigationBean.loginAction();
        	  	}
          }catch (UIDeceException ex) {
        	  ExceptionUtils.processUIDECEException("HOMEPAGE_LOGIN","ClassName:LoginUserBean_MethodName:remaindSecretQuestions",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
    }

    public void saveSecretQuestions()
    {
        UIUserService uiUserService=ServiceLocator.getUiUserService();  
          try {
	          userDVO.setSecretQuestionOneId(Long.valueOf(userDetailsMap.get("secretQuestion1").toString()));
	          userDVO.setSecretQuestionOneAnswer(userDetailsMap.get("secretAnswer1").toString().trim());
	          userDVO.setSecretQuestionTwoId(Long.valueOf(userDetailsMap.get("secretQuestion2").toString()));
	          userDVO.setSecretQuestionTwoAnswer(userDetailsMap.get("secretAnswer2").toString().trim());
	          if(validateSecretQuestions()){
	        	  UserSignInDVO userSignInDVO=uiUserService.updateUserSecurityQuestions((UserAuthDVO)session.getAttribute("Auth"), userDVO.getSecretQuestionOneId(), userDVO.getSecretQuestionOneAnswer().toString(), userDVO.getSecretQuestionTwoId(), userDVO.getSecretQuestionTwoAnswer().toString());
	        	  LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
	        	  loginNavigationBean.setDetailsIntoSession(userSignInDVO,session);
	        	  errorValueMap.put("pageError", null);
	        	  
	        	  loginNavigationBean.loginAction();
	          }else{
	        	  errorValueMap.put("pageError", "true");
	        	  sessionUtilsObj.getSession().setAttribute("toShowPopupOf", "SECRET_QUESTIONS");
	          }
          }catch (UIDeceException ex) {
        	  ExceptionUtils.processUIDECEException("HOMEPAGE_LOGIN","ClassName:LoginUserBean_MethodName:saveSecretQuestions",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
    }
	
	@SuppressWarnings("unchecked")
    public void getUserDetailsForQuestions()
    {
		if(session!=null && session.getAttribute("SignIn")!=null)
		{
         UserSignInDVO signin = (UserSignInDVO)sessionUtilsObj.getSession().getAttribute("SignIn");
		 userDetailsMap.put("secretQuestion1", signin.getSecretQuestionOneId());
		 userDetailsMap.put("secretAnswer1", signin.getSecretQuestionOneAnswer());
		}
    }
	
	
	public void setMyFavorites(List<FavoriteMediaDVO> myFavs) {
		this.myFavorites = myFavs;
	}

	public void setHouseholdCapacity(int householdCapacity) {
		this.householdCapacity = householdCapacity;
	}

	public int getHouseholdCapacity() {
		if(householdCapacity==0){
			try {
				householdMembers = ServiceLocator.getUiAccountService().getAccountUsers(sessionUtilsObj.getUserAuth(), sessionUtilsObj.getAccountId());
			} catch (UIDeceException e) {
				e.printStackTrace();
			}
			householdCapacity=6-householdMembers.size();
			householdMembers=null;
		}
		return householdCapacity;
	}

	public void setAccountDashBoardValueMap(HashMap<String,String> accountDashBoardValueMap) {
		this.accountDashBoardValueMap = accountDashBoardValueMap;
	}

	public HashMap<String,String> getAccountDashBoardValueMap() {
				return accountDashBoardValueMap;
	}
}
