/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * TermsOfUseBean.java
 */
package biz.neustar.dece.backing.bean.sections.login;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.ChildYouthUserDVO;
import biz.neustar.dece.ui.dvo.RSAMLParamsDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.dvo.UserSignInDVO;
import biz.neustar.dece.ui.enumeration.UINextPageEnum;

/**
 * The <code>TermsOfUseBean.java</code> class encapsulates objects defined for DECE.
 * @author Compugain.
 * @version 
 */
@ManagedBean(name="termsOfUseBean")
@RequestScoped
public class TermsOfUseBean {
	private static final Logger termsOfUseBeanLogger = Logger.getLogger(TermsOfUseBean.class);
	SessionUtils sessionUtils = new SessionUtils();
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	UserSignInDVO userSignInDvoRef;

	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}

	public String termsAndConditionAcceptAction(){
		try {
			userSignInDvoRef= ServiceLocator.getUIPolicyService().acceptTermsAndConditions(sessionUtils.getUserAuth());
			FacesContext context=FacesContext.getCurrentInstance();
			RSAMLParamsDVO rsamlParamsDVO  = (RSAMLParamsDVO)sessionUtils.getSession().getAttribute("RSAMLParamsDVO");
			if(null!= rsamlParamsDVO && rsamlParamsDVO.getTargetURL().contains("/views/touPage.jsf"))
			{
				HttpSession usrSession = sessionUtils.getSession();
				if(null!=usrSession){
					usrSession.removeAttribute("touAvailableFlag");
					usrSession.invalidate();
				}
				context.getExternalContext().redirect(rsamlParamsDVO.getReturnToURL());
			}
			else{
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retpath=loginNavigationBean.loginAction();
				if(null!=retpath && retpath.equalsIgnoreCase("pendingAccountDashboardPage") && userSignInDvoRef.getNextPage().equals(UINextPageEnum.DASHBOARD_SECURITY_QUESTIONS)){
					context.getExternalContext().redirect("../views/pendingAccountDashboardPage.jsf");
				}else
					return retpath;
			}
		}catch (UIDeceException e) {
			
			ExceptionUtils.processUIDECEException("TERMSOFUSE_SELF", "ClassName:TermsOfUseBean_MethodName:termsAndConditionAcceptAction", e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}
	public void termsAndConditionDeclineAction(){
		try {
			RSAMLParamsDVO rsamlParamsDVO  = (RSAMLParamsDVO)sessionUtils.getSession().getAttribute("RSAMLParamsDVO");
			if(null!= rsamlParamsDVO && rsamlParamsDVO.getTargetURL().contains("/views/touPage.jsf"))
			{
				HttpSession usrSession = sessionUtils.getSession();
				if(null!=usrSession){
					usrSession.removeAttribute("touAvailableFlag");
					usrSession.invalidate();
				}
				FacesContext context=FacesContext.getCurrentInstance();
				context.getExternalContext().redirect(rsamlParamsDVO.getReturnToURL());
			}
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
	}
	public String touOnBehalfOfChildYouthAcceptAction(){
		try {
				UserAuthDVO auth=sessionUtils.getUserAuth();
				userSignInDvoRef= ServiceLocator.getUIPolicyService().acceptTermsAndConditions(auth,sessionUtils.getSession().getAttribute("userNodeId").toString());
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				List<ChildYouthUserDVO> childYouthList=userSignInDvoRef.getChildYouthUserList();
				if(null!=childYouthList){
					ChildYouthUserDVO firstUser=childYouthList.get(0);
					String userDisplayName=firstUser.getDisplayName();
					sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);
				}else{
					sessionUtils.getSession().removeAttribute("ADMIN_POPUP");
					sessionUtils.getSession().removeAttribute("childOrYouthUser");
					sessionUtils.getSession().removeAttribute("userNodeId");
					sessionUtils.getSession().removeAttribute("toShowPopupOf");
				}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retPath=loginNavigationBean.loginAction();
				return retPath;
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("TERMSOFUSE_OTHERS", "ClassName:TermsOfUseBean_MethodName:touOnBehalfOfChildYouthAcceptAction", e, APIErrorMapUtil.uiErrorCodeFieldMap,errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}
	public String coppaOnBehalfOfChildAcceptAction(){
		try {
				UserAuthDVO auth=sessionUtils.getUserAuth();
				userSignInDvoRef= ServiceLocator.getUIPolicyService().acceptCoppa(auth,sessionUtils.getSession().getAttribute("userNodeId").toString());
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				List<ChildYouthUserDVO> childYouthList=userSignInDvoRef.getChildYouthUserList();
				if(null!=childYouthList){
					ChildYouthUserDVO firstUser=childYouthList.get(0);
					String userDisplayName=firstUser.getDisplayName();
					sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);					
					sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				}else{
					sessionUtils.getSession().removeAttribute("ADMIN_POPUP");
					sessionUtils.getSession().removeAttribute("toShowPopupOf");
					sessionUtils.getSession().removeAttribute("childOrYouthUser");
					sessionUtils.getSession().removeAttribute("userNodeId");
				}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retpath=loginNavigationBean.loginAction();
				return retpath;
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("COPPA_OTHERS", "ClassName:TermsOfUseBean_MethodName:coppaOnBehalfOfChildAcceptAction", e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}
	
	public String touOnBehalfOfChildYouthRejectAction(){
		try {
			UserAuthDVO auth=sessionUtils.getUserAuth();
				userSignInDvoRef= ServiceLocator.getUIPolicyService().doNotAcceptTermsAndConditions(auth,sessionUtils.getSession().getAttribute("userNodeId").toString());
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				List<ChildYouthUserDVO> childYouthList=userSignInDvoRef.getChildYouthUserList();
				if(null!=childYouthList){
					ChildYouthUserDVO firstUserList=childYouthList.get(0);
					String userDisplayName=firstUserList.getDisplayName();
					sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);
					sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				}else{
					sessionUtils.getSession().removeAttribute("ADMIN_POPUP");
					sessionUtils.getSession().removeAttribute("toShowPopupOf");
					sessionUtils.getSession().removeAttribute("childOrYouthUser");
					sessionUtils.getSession().removeAttribute("userNodeId");
				}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retPath=loginNavigationBean.loginAction();
				return retPath;
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("TOU_OTHERS_REJECT", "ClassName:TermsOfUseBean_MethodName:touOnBehalfOfChildYouthRejectAction", e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}
	public String declineClgTransferAction(){
		try {
			UserAuthDVO auth=sessionUtils.getUserAuth();
				userSignInDvoRef= ServiceLocator.getUiUserService().declineCLGTransfer(auth,sessionUtils.getSession().getAttribute("userNodeId").toString());
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				List<ChildYouthUserDVO> childYouthList=userSignInDvoRef.getChildYouthUserList();
				if(null!=childYouthList){
					ChildYouthUserDVO firstUserList=childYouthList.get(0);
					String userDisplayName=firstUserList.getDisplayName();
					sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);
					sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				}else{
					sessionUtils.getSession().removeAttribute("ADMIN_POPUP");
					sessionUtils.getSession().removeAttribute("toShowPopupOf");
					sessionUtils.getSession().removeAttribute("childOrYouthUser");
					sessionUtils.getSession().removeAttribute("userNodeId");
				}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retPath=loginNavigationBean.loginAction();
				return retPath;
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("CLG_TRANSFER_DECLINE", "ClassName:TermsOfUseBean_MethodName:declineClgTransferAction", e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}
	public String coppaRejectAction(){
		try {
				UserAuthDVO auth=sessionUtils.getUserAuth();
				userSignInDvoRef= ServiceLocator.getUIPolicyService().doNotAcceptCoppa(auth,sessionUtils.getSession().getAttribute("userNodeId").toString());
				sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				List<ChildYouthUserDVO> childYouthList=userSignInDvoRef.getChildYouthUserList();
				if(null!=childYouthList){
					ChildYouthUserDVO firstUserList=childYouthList.get(0);
					String userDisplayName=firstUserList.getDisplayName();
					sessionUtils.getSession().setAttribute("childOrYouthUser", userDisplayName);
					sessionUtils.getSession().setAttribute("SignIn", userSignInDvoRef);
				}else{
					sessionUtils.getSession().removeAttribute("ADMIN_POPUP");
					sessionUtils.getSession().removeAttribute("toShowPopupOf");
					sessionUtils.getSession().removeAttribute("childOrYouthUser");
					sessionUtils.getSession().removeAttribute("userNodeId");
				}
				LoginNavigationBean loginNavigationBean=new LoginNavigationBean();
				String retPath=loginNavigationBean.loginAction();
				return retPath;
		}catch (UIDeceException e) {
			ExceptionUtils.processUIDECEException("COPPA_REJECT", "ClassName:TermsOfUseBean_MethodName:coppaRejectAction", e,APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		catch (Exception ex) {
			errorValueMap.put("pageError", errorsBundle.getString("UNEXPECTED_ERROR"));
		}
		return null;
	}	
}