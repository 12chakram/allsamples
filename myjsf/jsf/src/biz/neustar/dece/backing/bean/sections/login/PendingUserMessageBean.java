/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * PendingUserMessageBean.java
 */
package biz.neustar.dece.backing.bean.sections.login;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.CLGUserDVO;
import biz.neustar.dece.ui.enumeration.UICountry;
import biz.neustar.dece.ui.service.UIUserService;

/**
 * The <code>PendingUserMessageBean.java</code> class encapsulates objects defined for DECE. 
 * @author Compugain.
 * @version 
 */
@ManagedBean(name="pendingUserMessageBean")
@RequestScoped
public class PendingUserMessageBean {
	private String pageTitle;
	private String pageSubTitle;
	
	private String displayName;
	private String status;
	private String userAgeClass;
	SessionUtils sessionUtils = new SessionUtils();
	private HashMap<String, String> errorValueMap;
	private ResourceBundle errorBundle=sessionUtils.getErrorTxtsBundle();
	private String clgUser;
	private UICountry country;
	private CLGUserDVO clgUserDVO;
	
	 public PendingUserMessageBean() {
		 String dispFor=null;
		 if(null!=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dispFor")){
			 dispFor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dispFor");
		 }else{
			 if(null!=sessionUtils.getSession() && null!=sessionUtils.getSession().getAttribute("dispFor"))
				 dispFor= sessionUtils.getSession().getAttribute("dispFor").toString();
		 }
		 if(dispFor != null){
				//dispFor= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dispFor");
				ResourceBundle lbls;
				lbls=new SessionUtils().getLabelsTxtsBundle();
				if(dispFor.equalsIgnoreCase("TOU_NOT_ACCEPTED_BY_CLG"))
					dispFor="TOU";
				else if(dispFor.equalsIgnoreCase("COPPA_NOT_ACCEPTED_BY_CLG"))
					dispFor="COPPA";
				else if(dispFor.equalsIgnoreCase("ACCOUNT_USER_STATUS_BLOCKED_CLG"))
					dispFor="CLGBLOCKED";

				getDisplayName();
				if(dispFor.equals("COPPA")){
					if(country.toString().equalsIgnoreCase("GB")){
						pageTitle=lbls.getString(dispFor+"PendingPageTitle_uk");
						pageSubTitle=lbls.getString(dispFor+"PendingPageSubTitle_uk");
					}
					else{
						pageTitle=lbls.getString(dispFor+"PendingPageTitle");
						pageSubTitle=lbls.getString(dispFor+"PendingPageSubTitle");
					}
				}
				else if(dispFor.equals("TOU")){
					pageTitle=lbls.getString(dispFor+"PendingPageTitle");
					pageSubTitle=lbls.getString(dispFor+"PendingPageSubTitle");
				}
			}
	}
	
	public String getDisplayName() {
		if(null!=sessionUtils.getSession() && null!=sessionUtils.getSession().getAttribute("clgUser"))
		{
			clgUser= sessionUtils.getSession().getAttribute("clgUser").toString();
			try{
				UIUserService uiUserService=ServiceLocator.getUiUserService();
				clgUserDVO=uiUserService.getCLGUser(clgUser);
				displayName=clgUserDVO.getDisplayName();
				country=clgUserDVO.getCountry();
				status=clgUserDVO.getStatus().toString();
				userAgeClass=clgUserDVO.getUserAgeClass().toString();
				if(null!=clgUserDVO.getStatus())
					setStatus(clgUserDVO.getStatus().toString());
				if("GB".equalsIgnoreCase(clgUserDVO.getCountry().toString())){
					//Locale locale = new Locale("en", "GB");
					Locale locale=new Locale("en", "US");
					sessionUtils.getSession().setAttribute("userLocale", locale);
					sessionUtils.getSession().setAttribute("country",clgUserDVO.getCountry().toString());
				}else{
					Locale locale=new Locale("en", "US");
					sessionUtils.getSession().setAttribute("userLocale", locale);
					sessionUtils.getSession().setAttribute("country",clgUserDVO.getCountry().toString());
				}
			}catch (UIDeceException ex) {			
				ExceptionUtils.processUIDECEException("CLG_USERNAME_GET", "ClassName:PendingUserMessageBean_MethodName:clgUser", ex, APIErrorMapUtil.uiErrorCodeFieldMap,errorValueMap, errorBundle);
			}
		}
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}	
	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}
	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}
	public String getClgUser() {
		return clgUser;
	}
	public void setClgUser(String clgUser) {
		this.clgUser = clgUser;
		
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageSubTitle() {
		return pageSubTitle;
	}
	public void setPageSubTitle(String pageSubTitle) {
		this.pageSubTitle = pageSubTitle;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setClgUserDVO(CLGUserDVO clgUserDVO) {
		this.clgUserDVO = clgUserDVO;
	}
	public CLGUserDVO getClgUserDVO() {
		return clgUserDVO;
	}

	public String getUserAgeClass() {
		return userAgeClass;
	}

	public void setUserAgeClass(String userAgeClass) {
		this.userAgeClass = userAgeClass;
	}

}