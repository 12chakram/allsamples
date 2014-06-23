/*
 * Copyright (c) 2011 Compugain, Inc. All Rights Reserved. 
 * MyHouseholdBean.java
 */
package biz.neustar.dece.backing.bean.sections.myhousehold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.DashboardUserDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;

/**
 * The <code>MyHouseholdBean.java</code> class encapsulates objects defined for DECE.
 * @author Compugain.
 * @version $Revision: 1.4 $ $Date: 2012/03/13 04:44:34 $
 */
@ManagedBean(name="myHouseholdBean")
@RequestScoped
public class MyHouseholdBean {
	private static final Logger myHouseholdBeanLogger = Logger.getLogger(MyHouseholdBean.class);
	SessionUtils sessionUtils = new SessionUtils();
	private ResourceBundle errorsBundle=sessionUtils.getErrorTxtsBundle();
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private String nodeUserId="";
	private Integer activeMembers;
	private Integer totalMembers;
	private Integer basicCount=0;
	private Integer standardCount=0;
	private Integer fullCount=0;
	private List<DashboardUserDVO> usersMemberList;
	private List<DashboardUserDVO> basicMemberList;
	private List<DashboardUserDVO> standardMemberList;
	private List<DashboardUserDVO> fullMemberList;
	private String operationStatus="";
	private String selectedNodeId="";
	
	public MyHouseholdBean(){	
		myHouseholdDisplayAction();
	}

	public String getSelectedNodeId() {
		return selectedNodeId;
	}

	public void setSelectedNodeId(String selectedNodeId) {
		this.selectedNodeId = selectedNodeId;
	}

	public String getNodeUserId() {
		return nodeUserId;
	}

	public void setNodeUserId(String nodeUserId) {
		this.nodeUserId = nodeUserId;
	}

	public String getOperationStatus() {		
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public Integer getBasicCount() {
		return basicCount;
	}

	public void setBasicCount(Integer basicCount) {
		this.basicCount = basicCount;
	}

	public Integer getStandardCount() {
		return standardCount;
	}

	public void setStandardCount(Integer standardCount) {
		this.standardCount = standardCount;
	}

	public Integer getFullCount() {
		return fullCount;
	}

	public void setFullCount(Integer fullCount) {
		this.fullCount = fullCount;
	}

	public List<DashboardUserDVO> getBasicMemberList() {
		return basicMemberList;
	}

	public void setBasicMemberList(List<DashboardUserDVO> basicMemberList) {
		this.basicMemberList = basicMemberList;
	}

	public List<DashboardUserDVO> getStandardMemberList() {
		return standardMemberList;
	}

	public void setStandardMemberList(List<DashboardUserDVO> standardMemberList) {
		this.standardMemberList = standardMemberList;
	}

	public List<DashboardUserDVO> getFullMemberList() {
		return fullMemberList;
	}

	public void setFullMemberList(List<DashboardUserDVO> fullMemberList) {
		this.fullMemberList = fullMemberList;
	}

	public Integer getTotalMembers() {
		return totalMembers;
	}

	public void setTotalMembers(Integer totalMembers) {
		this.totalMembers = totalMembers;
	}

	public List<DashboardUserDVO> getUsersMemberList() {
		return usersMemberList;
	}

	public void setUsersMemberList(List<DashboardUserDVO> usersMemberList) {
		this.usersMemberList = usersMemberList;
	}

	public Integer getActiveMembers() {
		return activeMembers;
	}

	public void setActiveMembers(Integer activeMembers) {
		this.activeMembers = activeMembers;
	}

	public static Logger getMyhouseholdbeanlogger() {
		return myHouseholdBeanLogger;
	}

	public String myHouseholdDisplayAction(){
		try{
			List<DashboardUserDVO> usersData=new ArrayList<DashboardUserDVO>();			
			UserAuthDVO  auth = sessionUtils.getUserAuth();
			auth.setPreviousWebPage(auth.getCurrentWebPage());
			auth.setCurrentWebPage("MYHOUSEHOLD_DASHBOARD");
			usersMemberList=new ArrayList<DashboardUserDVO>();
			basicMemberList=new ArrayList<DashboardUserDVO>();
			standardMemberList=new ArrayList<DashboardUserDVO>();
			fullMemberList=new ArrayList<DashboardUserDVO>();			
			usersData=ServiceLocator.getUiAccountService().getAccountUsers(auth,sessionUtils.getAccountId());		
			for (DashboardUserDVO dashboardUserDVO:usersData){
				String str=dashboardUserDVO.getNodeUserId();				
				dashboardUserDVO.setNodeUserId(str);
				if(dashboardUserDVO.getPrivilege().toString().equalsIgnoreCase("BASIC")){				
					basicMemberList.add(dashboardUserDVO);					
				}
				if(dashboardUserDVO.getPrivilege().toString().equalsIgnoreCase("STANDARD")){	
						standardMemberList.add(dashboardUserDVO);					
				}
				if(dashboardUserDVO.getPrivilege().toString().equalsIgnoreCase("FULL")){								
						fullMemberList.add(dashboardUserDVO);
				   }
				usersMemberList.add(dashboardUserDVO);				
			}
			this.activeMembers=usersMemberList.size();
			 setTotalMembers(usersMemberList.size());
			if (6 > this.activeMembers) {
				DashboardUserDVO dashBoardUserDVO = null;
			  for(int i=0;i<(6-this.activeMembers);i++) {
				  dashBoardUserDVO=new DashboardUserDVO();				 
				  dashBoardUserDVO.setDisplayName("None");				 
				  usersMemberList.add(dashBoardUserDVO);
				}
	    	 }
	        setActiveMembers(6-this.activeMembers);
	        basicCount=basicMemberList.size();
	        standardCount=standardMemberList.size();
	        fullCount=fullMemberList.size();
	        if(null != sessionUtils.getSession().getAttribute("RemoveMemberStatus")){
				operationStatus=sessionUtils.getSession().getAttribute("RemoveMemberStatus").toString();
				sessionUtils.getSession().removeAttribute("RemoveMemberStatus");
			}
		}catch(UIDeceException exe){
			ExceptionUtils.processUIDECEException("MYHOUSEHOLD_DASHBOARD","ClassName:MyHouseholdBean_MethodName:myHouseholdDisplayAction",exe, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, errorsBundle);
		}
		return null;
	}
}
