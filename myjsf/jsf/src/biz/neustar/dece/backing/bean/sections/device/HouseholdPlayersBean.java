package biz.neustar.dece.backing.bean.sections.device;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.DeviceDVO;
import biz.neustar.dece.ui.dvo.DeviceSummaryDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.enumeration.UIDeviceType;
import biz.neustar.dece.ui.service.UIDeviceService;

@ManagedBean(name="householdPlayersBean")
@RequestScoped
public class HouseholdPlayersBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger HOUSEHOLDPLAYERSBEANLOGGER= Logger.getLogger(HouseholdPlayersBean.class);
	private transient UIDeviceService deviceService=ServiceLocator.getUiDeviceService();
	private List<DeviceSummaryDVO> deviceList;
	private List<DeviceSummaryDVO> unverifiedDeviceList;
	private String householdDispName;
	private int deviceCount=0;
	private int unverifiedDeviceCount=0;
	private int remainingDeviceCount=0;
	private transient SessionUtils sessionUtilsObj=new SessionUtils();
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private String deviceCode;
	private String deviceName;
	private String deviceId;
	private String deviceType;
	private String removeDeviceId;
	
	
	public void retrievePlayerList(){
		if(sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null){
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			if(auth!=null){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("DEVICES_LIST_PAGE");
				try {
					deviceList=deviceService.getDeviceList(auth);
					HOUSEHOLDPLAYERSBEANLOGGER.info("*******Successfully Device list is retrieved*******");
					if(null!=deviceList && deviceList.size()>0)
					{
						deviceCount=deviceList.size();
						remainingDeviceCount = 12 - deviceCount;
					}
				} 
				catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("HOUSEHOLD_DEVICES", "ClassName:householdDevicesBean_MethodName:getDeviceList", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
				sessionUtilsObj.getSession().setAttribute("devNamLen", "6");
			}
		}
	}
	
	public void getAddDeviceCode(){
		SessionUtils sessionObj = new SessionUtils();
		if(sessionObj!=null && sessionObj.getSession()!=null){
			UserAuthDVO auth = sessionObj.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("ADD_DEVICE_CODE_PAGE");
				try {
					deviceCode=deviceService.generateAuthJoinCode(auth);
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("HOUSEHOLD_DEVICES", "ClassName:householdDevicesBean_MethodName:getAddDeviceCode", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
			}
		}
	}
	
	public void updateDeviceDisplayName(){
		if(sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null){
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("UPDATE_DEVICE_DISPLAY_NAME_PAGE");
				try {
						final DeviceDVO updtDevice= deviceService.updateDevice(auth, deviceId, deviceName, UIDeviceType.valueOf(deviceType));
						HOUSEHOLDPLAYERSBEANLOGGER.info("Successfully Device display name is updated.");
						deviceName = updtDevice.getDisplayName(); 
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:updateDeviceDisplayName", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
			}
		}
	}
	
	public void removeDevice(){
		if(sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null){
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("REMOVE_DEVICE_PAGE");
				if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmDevicesDashboard:removeDeviceId")!=null){
					removeDeviceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frmDevicesDashboard:removeDeviceId").toString();
				}
				else if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("removeDeviceId")!=null){
					removeDeviceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("removeDeviceId").toString();
				}
				boolean removeStatus;
				try {
					removeStatus = deviceService.removeDevice(auth, removeDeviceId);
					if(removeStatus){
						if(deviceList!=null)
							deviceList.clear();
						if(unverifiedDeviceList!=null)
							unverifiedDeviceList.clear();
						deviceCount=0;
						errorValueMap.remove("pageError");
						//sessionUtilsObj.getSession().setAttribute("removeDeviceStatus", displayName+" successfully removed.");
					}
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:removeDevice", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
			}
		}
	}
	
	public void getUnverifiedDevices(){
		if(sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null){
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			if(auth!=null){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("DEVICES_LIST_PAGE");
				try {
					unverifiedDeviceList = deviceService.getUnverifiedLeaveDeviceList(auth);
					HOUSEHOLDPLAYERSBEANLOGGER.info("*******Successfully UnverifiedDevice list is retrieved*******");
					if(null!=unverifiedDeviceList && unverifiedDeviceList.size()>0)
					{
						unverifiedDeviceCount = unverifiedDeviceList.size();
					}
				} 
				catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("HOUSEHOLD_DEVICES", "ClassName:householdDevicesBean_MethodName:getDeviceList", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
				sessionUtilsObj.getSession().setAttribute("devNamLen", "6");
			}
		}
	}
	
	public void reloadThisPageAction(){
		try{
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../views/ourDevicesPage.jsf");
		}catch (IOException ioe) {
			if(HOUSEHOLDPLAYERSBEANLOGGER.isDebugEnabled())   {           
				HOUSEHOLDPLAYERSBEANLOGGER.debug(ioe.getMessage());
			}
		}
		
	}
	public void setHouseholdDispName(String householdDispName) {
		this.householdDispName = householdDispName;
	}

	public String getHouseholdDispName() {
		if(null==householdDispName && sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null)			
			householdDispName=sessionUtilsObj.getSession().getAttribute("householdName").toString();
		return householdDispName;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}

	public void setDeviceList(List<DeviceSummaryDVO> deviceList) {
		this.deviceList = deviceList;
	}

	public List<DeviceSummaryDVO> getDeviceList() {
		if(deviceList==null || deviceList.isEmpty())
			retrievePlayerList();
		return deviceList;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public int getDeviceCount() {
		if(deviceCount==0)
			retrievePlayerList();
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public int getRemainingDeviceCount() {
		return remainingDeviceCount;
	}

	public void setRemainingDeviceCount(int remainingDeviceCount) {
		this.remainingDeviceCount = remainingDeviceCount;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRemoveDeviceId() {
		return removeDeviceId;
	}

	public void setRemoveDeviceId(String removeDeviceId) {
		this.removeDeviceId = removeDeviceId;
	}

	public int getUnverifiedDeviceCount() {
		return unverifiedDeviceCount;
	}

	public void setUnverifiedDeviceCount(int unverifiedDeviceCount) {
		this.unverifiedDeviceCount = unverifiedDeviceCount;
	}

	public List<DeviceSummaryDVO> getUnverifiedDeviceList() {
		if(unverifiedDeviceList==null || unverifiedDeviceList.isEmpty())
			getUnverifiedDevices();
		return unverifiedDeviceList;
	}

	public void setUnverifiedDeviceList(List<DeviceSummaryDVO> unverifiedDeviceList) {
		this.unverifiedDeviceList = unverifiedDeviceList;
	}
}
