/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * AddEditFamilyCommonClass.java
 */
package biz.neustar.dece.custom.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.DashboardUserDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.enumeration.UIDECEStatus;
import biz.neustar.dece.ui.enumeration.UserPrivilege;
/**
 * The <code>AddEditFamilyCommonClass.java</code> class encapsulates objects defined
 * for DECE.
 * @author Compugain.
 * @version $Revision: 1.1 $ $Date: 2012/01/10 13:06:45 $
 */
public class AddEditFamilyCommonClass {
	private  ResourceBundle errorMsgsBundle= ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.errorTxts",FacesContext.getCurrentInstance().getViewRoot().getLocale());
	private  ResourceBundle bundleLabels= ResourceBundle.getBundle("biz.neustar.dece.decessp.i18n.labelTxts",FacesContext.getCurrentInstance().getViewRoot().getLocale());
	private final Logger addEditFamilyCommonClassLogger = Logger.getLogger(AddEditFamilyCommonClass.class);
	HashMap<String,String> errorMap=new HashMap<String, String>();
	private SessionUtils sessionUtils = new SessionUtils();	
	/**
	 * Jan 19,2011.
	 * Full access level check method.
	 * @param sessionUtils
	 * @param privilegeString
	 * @return
	 */
	public HashMap<String,String> fullAccessLevelCheck(SessionUtils sessionUtils,String privilegeString ){
		List<DashboardUserDVO> userMemberList=new ArrayList<DashboardUserDVO>();
		int fullAccessCount=0;
		try {
			userMemberList=ServiceLocator.getUiAccountService().getAccountUsers(sessionUtils.getUserAuth(), sessionUtils.getAccountId());
			if(userMemberList!=null && userMemberList.size()>0){
		      for (DashboardUserDVO  userMember: userMemberList){
			    if (6 > userMemberList.size()) {
				   if(UserPrivilege.FULL.equals(userMember.getDisplayName()) && UIDECEStatus.ACTIVE.equals(userMember.getStatus())){
					   fullAccessCount++;
				 }
			  }
		    }
		  }		
		} catch (UIDeceException ex) {			
			ExceptionUtils.processUIDECEException("Add/Edit_Family","ClassName:AddEditFamilyCommonClass_MethodName:fullAccessLevelCheck",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorMap, errorMsgsBundle);
		}
		if(fullAccessCount==1 && ("STANDARD".equalsIgnoreCase(privilegeString) || "BASIC".equalsIgnoreCase(privilegeString))){
			errorMap.put("pageError",errorMsgsBundle.getString("fullAccessLevelPermission"));
			return errorMap;
		}		 
		else{
		 return null;
		}
	}
    /**
     * Jan 19,2011.
     * validate method of submitted values.
     * @param userVal
     * @param confirmPassword
     * @param alternateEmail
     * @param pageName
     * @return
     */
	public  HashMap<String,String> validateSubmittedValues(UserDVO userVal,String confirmPassword,String alternateEmail,String pageName) {		
		boolean isValidForm = true;		
		String emaileerror,alternateEmaileerror;
		isValidForm = checkAvailabilityAction(userVal.getUserName(),errorMap,pageName,"");
		if(pageName.equalsIgnoreCase("edit")?(userVal.getPassword()!=null && !userVal.getPassword().equalsIgnoreCase("")):true){
			String passworderror = CommonValidations.passwordValidation(userVal, userVal.getPassword());
			if (!"".equals(passworderror)) {
				isValidForm=false;
				errorMap.put("creapasserror",passworderror);
			}else if(!(userVal.getPassword().equals(confirmPassword))){
				isValidForm=false;
				errorMap.put("cnfmpasseerror",errorMsgsBundle.getString("Password_confirm_password_not_same"));	
				addEditFamilyCommonClassLogger.info("isValidForm----------"+isValidForm+"---------"+userVal.getPassword()+"-------------"+confirmPassword);
			}
		}
     //Email Validation Start
		emaileerror=CommonValidations.emailValidation(userVal.getEmail());
		errorMap.put("emaileerror", CommonValidations.emailValidation(userVal.getEmail()));
		if(!"".equals(emaileerror))
			isValidForm=false;
		if(alternateEmail != null && !"".equalsIgnoreCase(alternateEmail)){
			alternateEmaileerror=CommonValidations.emailValidation(alternateEmail.toLowerCase());
			errorMap.put("alternateEmaileerror",alternateEmaileerror);
			if(!"".equals(alternateEmaileerror))
				isValidForm=false;
			if("".equalsIgnoreCase(alternateEmaileerror) && userVal.getEmail().equalsIgnoreCase(alternateEmail)){
				errorMap.put("alternateEmaileerror","E-mail and Alternate E-mail are same.");	
				isValidForm=false;
			}			
		}
     //Email Validation End
		if(null==userVal.getSecretQuestionOneId() || userVal.getSecretQuestionOneId()==0L){
			isValidForm=false;
			errorMap.put("secQuestion1Error", errorMsgsBundle.getString("SELECT_VALID_QUESTION"));
		}
		if(null==userVal.getSecretQuestionTwoId() || userVal.getSecretQuestionTwoId()== 0L){
			errorMap.put("secQuestion2Error",errorMsgsBundle.getString("SELECT_VALID_QUESTION"));
			isValidForm=false;
		} else if(null!=userVal.getSecretQuestionOneId() && userVal.getSecretQuestionOneId().compareTo(userVal.getSecretQuestionTwoId())==0)
		{
			errorMap.put("secQuestion2Error",bundleLabels.getString("sec2selection"));			
			isValidForm=false;
		}		
		String secAns1Error=(userVal.getSecretQuestionOneAnswer()!=null?("".equals(userVal.getSecretQuestionOneAnswer().trim())?errorMsgsBundle.getString("enter_answer"):""):errorMsgsBundle.getString("enter_answer"));
		errorMap.put("secAns1Error",secAns1Error);
		String secAns2Error=(userVal.getSecretQuestionTwoAnswer()!=null?("".equals(userVal.getSecretQuestionTwoAnswer().trim())?errorMsgsBundle.getString("enter_answer"):""):errorMsgsBundle.getString("enter_answer"));
		errorMap.put("secAns2Error",secAns2Error);
		if((!"".equals(secAns1Error)||!"".equals(secAns2Error)))
		 isValidForm=false;
		if(isValidForm)
			return null;	
		else
			return errorMap;
		
	}
	/**
	 * Jan 19,2011.
	 * User check availability method.
	 * @param username
	 * @param map
	 * @param pageName
	 * @return
	 */
	public boolean checkAvailabilityAction(String username,HashMap<String,String> map,String pageName,String nodeUserId) {
		try {
			if(sessionUtils.getSession().getAttribute("userNameChngChk")!=null && sessionUtils.getSession().getAttribute("userNameChngChk").toString().equals(username) && pageName.equalsIgnoreCase("edit")){
				map.put("userNameAvailable", errorMsgsBundle.getString("user_available"));
				return true;
			}
			String userNameValidation = CommonValidations.userNameValidation(username); 
			if(userNameValidation.equals(errorMsgsBundle.getString("insufficientUsername"))){
				if(!sessionUtils.getUserAuth().getNodeUserId().equals(nodeUserId))
					userNameValidation = errorMsgsBundle.getString("insufficientUsername_others");
			}
			if("".equals(userNameValidation)){
				if(!ServiceLocator.getUiUserService().checkUserIDAvailablity(username.trim())){
					map.put("userNameAvailable", errorMsgsBundle.getString("user_available"));
					return true;
				} else {
					map.put("usernameError",  errorMsgsBundle.getString("user_already_exists"));
					return false;
				}
			} else {
				map.put("usernameError", userNameValidation);
			}
			userNameValidation = null;
			
		} catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("Add/Edit_Family","ClassName:AddEditFamilyCommonClass_MethodName:checkAvailabilityAction",ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorMap, errorMsgsBundle);
		}
		return false;
	}
	
  }
