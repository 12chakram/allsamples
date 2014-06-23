/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * HouseholdSettings.java
 */

package biz.neustar.dece.backing.bean.sections.householdSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import biz.neustar.dece.backing.bean.sections.common.ConsentsInfoDVO;
import biz.neustar.dece.backing.bean.sections.common.HeaderBean;
import biz.neustar.dece.backing.bean.sections.common.UIConsentsDVO;
import biz.neustar.dece.custom.validation.CommonValidations;
import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.AccountConsentsDVO;
import biz.neustar.dece.ui.dvo.AccountDVO;
import biz.neustar.dece.ui.dvo.ConsentsStatemenstDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UICountry;
import biz.neustar.dece.ui.service.UIAccountService;
import biz.neustar.dece.ui.service.UIPolicyService;

/**
 * The <code>HouseholdSettings.java</code> class encapsulates objects defined for DECE.
 * @author Compugain. 
 * @version $Revision: 1.5 $ $Date: 2012/03/13 04:44:34 $
 */
@ManagedBean(name="householdSettingsBean")
@RequestScoped
public class HouseholdSettingsBean  {

	private static final Logger householdSettingsLogger = Logger.getLogger(HouseholdSettingsBean.class);
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private HashMap<String,String> householdSettingsMap=new HashMap<String, String>();
	transient SessionUtils sessionUtils=new SessionUtils();
	transient ResourceBundle errorBundle = sessionUtils.getErrorTxtsBundle();
	transient ResourceBundle labelsBundle=sessionUtils.getLabelsTxtsBundle();
	private String householdName;
	transient private List<AccountConsentsDVO> accountConsentsDVOsList;
	transient private List<ConsentsStatemenstDVO> consentsStatemenstList;
	transient private List<UIConsentsDVO> accountConsents;
	private String empStr="";
	private String linkNodeId="";
	
		
	public HouseholdSettingsBean(){
		if(accountConsentsDVOsList == null){			
			accountConsentsDVOsList=new ArrayList<AccountConsentsDVO>();
			consentsStatemenstList=new ArrayList<ConsentsStatemenstDVO>();
			householdAccountDetails();
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
	public List<ConsentsStatemenstDVO> getConsentsStatemenstList() {
		return consentsStatemenstList;
	}

	public void setConsentsStatemenstList(
			List<ConsentsStatemenstDVO> consentsStatemenstList) {
		this.consentsStatemenstList = consentsStatemenstList;
	}
	public List<AccountConsentsDVO> getAccountConsentsDVOsList() {
		return accountConsentsDVOsList;
	}

	public void setAccountConsentsDVOsList(List<AccountConsentsDVO> accountConsentsDVOsList) {
		this.accountConsentsDVOsList = accountConsentsDVOsList;
	}
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	
	public HashMap<String, String> getHouseholdSettingsMap() {
		return householdSettingsMap;
	}

	public void setHouseholdSettingsMap(HashMap<String, String> householdSettingsMap) {
		this.householdSettingsMap = householdSettingsMap;
	}

	public String getHouseholdName() {
		return householdName;
	}

	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}
	
	public String householdAccountDetails(){
		AccountDVO accountDvoObj=null;
		try {
			UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
			UserAuthDVO auth=sessionUtils.getUserAuth();
			//Added to track the Previous and next page in UserAuthDVO
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("AUTH_HOUSEHOLD_VIEW_HOUSEHOLD_SETTINGS");
			accountDvoObj=uiAccountService.getAccount(auth, auth.getNodeAccountId());
			householdName=accountDvoObj.getName();
			if(accountDvoObj.getAccountConsentsDVOList()!=null){
				accountConsentsDVOsList=accountDvoObj.getAccountConsentsDVOList();
				householdSettingsLogger.info("consentsStatemenstList.size:---"+consentsStatemenstList.size());
				setLinkedAccounts(accountConsentsDVOsList,accountDvoObj.getCountry());				
			}else
				householdSettingsMap.put("householdLinkedAccounts", "false");
			householdSettingsLogger.info("Successfully retrieved account details.");
			
		} catch (UIDeceException exe) {
			householdSettingsLogger.error("Failed retrieving the account details.");
			ExceptionUtils.processUIDECEException("HOUSEHOLD_SETTING","ClassName:HouseholdSetting_MethodName:householdAccountDetails",exe, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		  }
		return "householdAccountSettingsPage";
	}
	
	public void householdRename(){
		AccountDVO accountDvoObj=null;
		try {
			UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
			UserAuthDVO auth=sessionUtils.getUserAuth();
			//Added to track the Previous and next page in UserAuthDVO
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("AUTH_HOUSEHOLD_VIEW_HOUSEHOLD_SETTINGS");
			accountDvoObj=uiAccountService.getAccount(auth, auth.getNodeAccountId());
			if(accountDvoObj!=null ){
				if(renameValidation()){
					if(this.householdName!=null && (!"".equals(this.householdName))){
						accountDvoObj.setName(this.householdName);
					    AccountDVO accountDvoFromUpdateAccountObj=uiAccountService.updateAccount(auth, accountDvoObj);
					    UserSignInDVO userDVO=(UserSignInDVO)sessionUtils.getSession().getAttribute("SignIn");
					    userDVO.setAccountName(accountDvoFromUpdateAccountObj.getName());
					    sessionUtils.getSession().setAttribute("SignIn", userDVO);
					    sessionUtils.getSession().setAttribute("householdName", userDVO.getAccountName());
					    householdSettingsLogger.info("Successfully updated house hold name."+accountDvoFromUpdateAccountObj.getName());
					    householdSettingsMap.put("succMsg", errorBundle.getString("successMsg"));
					    setHouseholdName(accountDvoFromUpdateAccountObj.getName());
					}
				}
			}
		}catch (UIDeceException exe) {
			householdSettingsLogger.error("House hold updation is failed.");
			ExceptionUtils.processUIDECEException("HOUSEHOLD_SETTING","ClassName:HouseholdSetting_MethodName:householdRename",exe, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		 }
			
	}
	
	public String removeHouseholdAction(){
		try {
			UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
			UserAuthDVO userAuthDvoObj=sessionUtils.getUserAuth();
			userAuthDvoObj.setPreviousWebPage(userAuthDvoObj.getCurrentWebPage());
			userAuthDvoObj.setCurrentWebPage("AUTH_HOUSEHOLD_REMOVE_HOUSEHOLD");
			uiAccountService.deleteAccount(userAuthDvoObj, userAuthDvoObj.getNodeAccountId());
			//after deleting the household, log-out user. Log-out function will redirect to homepage			
			householdSettingsLogger.debug("Deleted account for nodeaccount id "+userAuthDvoObj.getNodeAccountId());
			sessionUtils.getSession().setAttribute("householdRemoved", true);
			return new HeaderBean().signOut();
		} catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("REQUEST_ACCOUNT_DELETED","ClassName:HouseholdSetting_MethodName:deleteAccount",e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		}
		return null;
	}
	
	
	public boolean renameValidation() {
		  boolean renameValidationFlag = true;
		  errorValueMap.put("houseHoldNameError",CommonValidations.houseHoldNameValidation(householdName.trim()));
		  if(!"".equals(errorValueMap.get("houseHoldNameError")))
			renameValidationFlag = false;	
		  else
			errorValueMap.remove("houseHoldNameError");	  
		 return renameValidationFlag;		
		}	
	
	public void saveLinkedAccounts(){
		AccountDVO accountDvoObj=null;
		String[] selectedValues=null;
		try{
			UIAccountService uiAccountService=ServiceLocator.getUiAccountService();
			UserAuthDVO auth=sessionUtils.getUserAuth();			
			accountDvoObj=uiAccountService.getAccount(auth, auth.getNodeAccountId());
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			//List<UserLinkedConsentDVO> userLinkedConsentDVOsList = dummyRetrievelOfUserLinkedAccounts();
			List<AccountConsentsDVO> householdLinkedConsentDVOsList =accountDvoObj.getAccountConsentsDVOList();
			List<AccountConsentsDVO> householdLinkedConsentDetails=new ArrayList<AccountConsentsDVO>();
			for(Iterator<AccountConsentsDVO> iterator=householdLinkedConsentDVOsList.iterator();iterator.hasNext();){
				AccountConsentsDVO householdConsentsDVO = iterator.next();			
				selectedValues = request.getParameterValues(householdConsentsDVO.getNodeId());
				if(selectedValues !=null){
					boolean enableManageUserConsent=false;
					boolean enableSharingMemberProfile=false;
					boolean manageAccountConsent=false;
					boolean shareMediaHistory=false;				
					for(int index=0;index<selectedValues.length;index++){
						if("enableManageUserConsent".equalsIgnoreCase(selectedValues[index])){						
							enableManageUserConsent=true;
						}
					    if("enableSharingMemberProfile".equalsIgnoreCase(selectedValues[index])){				    	
					    	enableSharingMemberProfile=true;
						}
						if("manageAccountConsent".equalsIgnoreCase(selectedValues[index])){						
							manageAccountConsent=true;
						}
						if("shareMediaHistory".equalsIgnoreCase(selectedValues[index])){						
							shareMediaHistory=true;
						}	
				    }				
					if(enableManageUserConsent == true){
						householdConsentsDVO.setEnableManageUserConsent(true);
					}else{
						householdConsentsDVO.setEnableManageUserConsent(false);
					}
					if(enableSharingMemberProfile == true){
						householdConsentsDVO.setEnableSharingMemberProfile(true);
					}else{
						householdConsentsDVO.setEnableSharingMemberProfile(false);	
					}
					if(manageAccountConsent == true){
						householdConsentsDVO.setManageAccountConsent(true);
					}else{
						householdConsentsDVO.setManageAccountConsent(false);
					}
					if(shareMediaHistory == true){
						householdConsentsDVO.setShareMediaHistory(true);
					}else{
						householdConsentsDVO.setShareMediaHistory(false);
					}
					
				}else{
					householdConsentsDVO.setEnableManageUserConsent(false);
					householdConsentsDVO.setEnableSharingMemberProfile(false);
					householdConsentsDVO.setManageAccountConsent(false);
					householdConsentsDVO.setShareMediaHistory(false);
				}
				householdLinkedConsentDetails.add(householdConsentsDVO);
			   }
				accountDvoObj.setAccountConsentsDVOList(householdLinkedConsentDetails);			
				//AccountDVO updatedAccountDVO=uiAccountService.updateAccount(auth, accountDvoObj);
				AccountDVO updatedAccountDVO=uiAccountService.updateAccountConsents(auth, accountDvoObj.getAccountConsentsDVOList());
				householdSettingsLogger.info("Successfull updatetion of account linked accounts."+updatedAccountDVO);
				if(updatedAccountDVO.getAccountConsentsDVOList().size()!=0){
					householdSettingsMap.put("successMsg", errorBundle.getString("successMsg"));
					householdSettingsMap.put("householdLinkedAccounts", "true");
				}else
					householdSettingsMap.put("householdLinkedAccounts", "false");
				setAccountConsentsDVOsList(updatedAccountDVO.getAccountConsentsDVOList());
				setLinkedAccounts(accountConsentsDVOsList,updatedAccountDVO.getCountry());
		   }catch (UIDeceException e) {
			   householdSettingsLogger.error("Failed while updating the account linked accounts.");
			   ExceptionUtils.processUIDECEException("Update Account Linked Accounts","ClassName:HouseholdSettingsBean_MethodName:saveLinkedAccounts",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
	       }
	 }
	public void removeUserFromLinkedAccounts(){
		String nodeUserId=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmHouseholdSettings1:nodeUserId").toString();
		String accountNodeId=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmHouseholdSettings1:nodeId").toString();
		UserAuthDVO auth=sessionUtils.getUserAuth();
		try {
			AccountDVO updatedAccountDVO=ServiceLocator.getUiAccountService().unlinkUserFromNode(auth,nodeUserId,accountNodeId);
			if(null!=updatedAccountDVO && null!=updatedAccountDVO.getAccountConsentsDVOList() && updatedAccountDVO.getAccountConsentsDVOList().size()!=0){
				householdSettingsMap.put("householdLinkedAccounts", "true");
				setAccountConsentsDVOsList(updatedAccountDVO.getAccountConsentsDVOList());
				setLinkedAccounts(accountConsentsDVOsList,updatedAccountDVO.getCountry());
			}else
				householdSettingsMap.put("householdLinkedAccounts", "false");
		} catch (UIDeceException e) {
			e.printStackTrace();
		}
	}
	
	private void setLinkedAccounts(List<AccountConsentsDVO> accountConsentsDVOsList, UICountry country){
		UIPolicyService uiPolicyService=ServiceLocator.getUIPolicyService();
		accountConsents = new ArrayList<UIConsentsDVO>();
		for(AccountConsentsDVO acctCnst:accountConsentsDVOsList)
		{
			UIConsentsDVO obj = new UIConsentsDVO();
			obj.setNodeId(acctCnst.getNodeId());
			obj.setNodeImageURL(acctCnst.getNodeImageURL());
			obj.setNodeName(acctCnst.getNodeName());
			obj.setLinkedUsers(acctCnst.getLinkedUsers());
								
			List<ConsentsInfoDVO> cntsDVO= new ArrayList<ConsentsInfoDVO>();
			ConsentsStatemenstDVO consentsStatemenstDVO=uiPolicyService.getConsentsStatementsByNodeIdAndCountry(acctCnst.getNodeId(), country);
			
			ConsentsInfoDVO obj1 = new ConsentsInfoDVO();
			obj1.setConsentName(labelsBundle.getString("manageUvAccount"));
			obj1.setConsentHdnName("manageAccountConsent");
			obj1.setConsentShortDesc(labelsBundle.getString("manageUvShortDesc")+" "+acctCnst.getNodeName()+".");
			obj1.setConsentDesc(consentsStatemenstDVO.getManageMyAccountStatement());
			obj1.setConsentAccepted(acctCnst.isManageAccountConsent());
			obj1.setConsentEditable(acctCnst.isManageAccountConsentEditable());
			cntsDVO.add(obj1);
			
			obj1 = new ConsentsInfoDVO();
			obj1.setConsentName(labelsBundle.getString("shareUvRightsLocker"));
			obj1.setConsentHdnName("shareMediaHistory");
			obj1.setConsentShortDesc(labelsBundle.getString("allow")+" "+acctCnst.getNodeName()+" "+labelsBundle.getString("shareMediaShortDesc"));
			obj1.setConsentDesc(consentsStatemenstDVO.getShareMediaHistoryStatement());
			obj1.setConsentAccepted(acctCnst.isShareMediaHistory());
			obj1.setConsentEditable(acctCnst.isShareMediaHistoryEditable());
			cntsDVO.add(obj1);
			
			obj1 = new ConsentsInfoDVO();
			obj1.setConsentName(labelsBundle.getString("shareMemberInfo"));
			obj1.setConsentHdnName("enableSharingMemberProfile");
			obj1.setConsentShortDesc(labelsBundle.getString("shareMemberShortDesc1")+" "+acctCnst.getNodeName()+" "+labelsBundle.getString("shareMemberShortDesc2"));
			obj1.setConsentDesc(consentsStatemenstDVO.getEnableSharingMemberProfileStatement());
			obj1.setConsentAccepted(acctCnst.isEnableSharingMemberProfile());
			obj1.setConsentEditable(acctCnst.isEnableSharingMemberProfileEditable());
			cntsDVO.add(obj1);
			
			obj1 = new ConsentsInfoDVO();
			obj1.setConsentName(labelsBundle.getString("enableMangeUserConsent"));
			obj1.setConsentHdnName("enableManageUserConsent");
			obj1.setConsentShortDesc(labelsBundle.getString("enableMangeUserConsentShortDesc1")+" "+acctCnst.getNodeName()+".");
			obj1.setConsentDesc(consentsStatemenstDVO.getEnableManageUserConsentStatement());
			obj1.setConsentAccepted(acctCnst.isEnableManageUserConsent());
			obj1.setConsentEditable(acctCnst.isEnableSharingMemberProfileEditable());
			cntsDVO.add(obj1);
			
			obj.setConsentsObj(cntsDVO);
			accountConsents.add(obj);
		}
	}
	
	public void removeLinkedAccounts(){
		try{
			String nodeid=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmHouseholdSettings1:nodeId");
			UserAuthDVO auth=sessionUtils.getUserAuth();
			AccountDVO accountDVO=ServiceLocator.getUiAccountService().getAccount(auth, auth.getNodeAccountId());
			List<AccountConsentsDVO> accountConsentsList=accountDVO.getAccountConsentsDVOList();
			if(!accountConsentsList.isEmpty()){
				for(Iterator<AccountConsentsDVO> iterator=accountConsentsList.iterator();iterator.hasNext();){
					AccountConsentsDVO accntLinkedConsentDVO=iterator.next();
					if(accntLinkedConsentDVO.getNodeId().equals(nodeid)){						
						iterator.remove();
						break;
					}
				}
			}
			accountDVO.setAccountConsentsDVOList(accountConsentsList);
			AccountDVO updatedAccountDVO=ServiceLocator.getUiAccountService().updateAccount(auth, accountDVO);
			if(updatedAccountDVO.getAccountConsentsDVOList().size()!=0)
				householdSettingsMap.put("householdLinkedAccounts", "true");
			else
				householdSettingsMap.put("householdLinkedAccounts", "false");
			setAccountConsentsDVOsList(updatedAccountDVO.getAccountConsentsDVOList());
			setLinkedAccounts(accountConsentsDVOsList,updatedAccountDVO.getCountry());
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("RemoveUserLinkedAccounts","ClassName:UserProfileBean_MethodName:removeLinkedAccounts",e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorBundle);
		}
	}
	public List<UIConsentsDVO> getAccountConsents() {
		return accountConsents;
	}
	public void setAccountConsents(List<UIConsentsDVO> accountConsents) {
		this.accountConsents = accountConsents;
	}
}