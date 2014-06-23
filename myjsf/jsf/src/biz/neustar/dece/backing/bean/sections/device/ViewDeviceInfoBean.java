package biz.neustar.dece.backing.bean.sections.device;

import java.io.Serializable;
import java.util.HashMap;

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
import biz.neustar.dece.ui.dvo.PlayerDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.enumeration.UIDeviceType;
import biz.neustar.dece.ui.service.UIDeviceService;

@ManagedBean(name="viewDeviceInfoBean")
@RequestScoped
public class ViewDeviceInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger VIEWDEVICEINFOBEANLOGGER= Logger.getLogger(ViewDeviceInfoBean.class);;
			
	private String deviceId;
	
	private String displayName;
	
	private String deviceType;
	
	private String renderPage="";
	
	private transient SessionUtils sessionUtils = new SessionUtils();	
	
	private transient UIDeviceService deviceService=ServiceLocator.getUiDeviceService();
	
	private DeviceDVO deviceDVO;
	
	private PlayerDVO playerDVO;
	
	private String playerId;
	
	private boolean updSuccess=false;

	private HashMap<String, String> errorValueMap= new HashMap<String, String>();
	
	
	public boolean isUpdSuccess() {
		return updSuccess;
	}

	public void setUpdSuccess(boolean updSuccess) {
		this.updSuccess = updSuccess;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return deviceId;
	}
	
	public DeviceDVO getDeviceDVO() {
		return deviceDVO;
	}

	public void setDeviceDVO(DeviceDVO deviceDVO) {
		this.deviceDVO=deviceDVO;	
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getPlayerInfo()
	{
		if(sessionUtils!=null && sessionUtils.getSession()!=null){
			UserAuthDVO auth = sessionUtils.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("PLAYER_INFO_PAGE");
				try {
					playerDVO=deviceService.getPlayerDetails(auth , playerId);
					if(playerDVO!=null){
						displayName=playerDVO.getPlayerName();
						renderPage="PLAYER";
						updSuccess=false;
						VIEWDEVICEINFOBEANLOGGER.info("Successfully retrieved player details.");
					}
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:getPlayerInfo", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				}
			}
		}
		return "deviceDetailsPage";
	}
	public String getDeviceInfo()
	{
		if(sessionUtils!=null && sessionUtils.getSession()!=null){
			UserAuthDVO auth = sessionUtils.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("DEVICE_INFO_PAGE");
				try {				
					if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceType")!=null){
						deviceType= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceType").toString();
					}
					if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceId")!=null){
						deviceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceId").toString();
					}
					deviceDVO = deviceService.getDeviceDetails(auth , deviceId, UIDeviceType.valueOf(deviceType));
					if(deviceDVO!=null){
						displayName=deviceDVO.getDisplayName();
						deviceType=deviceDVO.getDeviceType().toString();
						updSuccess=false;
						VIEWDEVICEINFOBEANLOGGER.info("Successfully retrieved device details.");			    
					    if(deviceDVO.getDeviceType().equals(UIDeviceType.LEGACY)){
					    	renderPage="LEGACY";
					    }
					    else if(deviceDVO.getDeviceType().equals(UIDeviceType.DECE)){
					    	renderPage="DECE";
					    }
					}
				}catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:getDeviceInfo", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				}
			}
		}
		return "deviceDetailsPage";
	}
	public void removeDevice(){
		if(sessionUtils!=null && sessionUtils.getSession()!=null){
			UserAuthDVO auth = sessionUtils.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("REMOVE_DEVICE_PAGE");
				if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceId")!=null){
					deviceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deviceId").toString();
				}
				boolean removeStatus;
				try {
					removeStatus = deviceService.removeDevice(auth, deviceId);
					if(removeStatus){
						errorValueMap.remove("pageError");
						sessionUtils.getSession().setAttribute("removeDeviceStatus", displayName+" successfully removed.");
					}
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:removeDevice", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				}
			}
		}
	}

	public HashMap<String, String> getErrorValueMap() {
		return errorValueMap;
	}

	public void setErrorValueMap(HashMap<String, String> errorValueMap) {
		this.errorValueMap = errorValueMap;
	}

	public void updateDeviceDisplayName()
	{
		if(sessionUtils!=null && sessionUtils.getSession()!=null){
			UserAuthDVO auth = sessionUtils.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("UPDATE_DEVICE_DISPLAY_NAME_PAGE");
				try {
					if(displayName.length()>1){
						final DeviceDVO updtDevice= deviceService.updateDevice(auth, deviceId, displayName, UIDeviceType.valueOf(deviceType));
						VIEWDEVICEINFOBEANLOGGER.info("Successfully Device display name is updated.");
						displayName = updtDevice.getDisplayName(); 
						updSuccess=true;
					}
					else{
						updSuccess=false;
						displayName=deviceService.getDeviceDetails(auth, deviceId, UIDeviceType.valueOf(deviceType)).getDisplayName();
					}
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_DEVICES_INFO", "ClassName:viewDeviceInfoBean_MethodName:updateDeviceDisplayName", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				}
			}
		}
	}
	
	public void updatePlayerDisplayName()
	{
		if(sessionUtils!=null && sessionUtils.getSession()!=null){
			UserAuthDVO auth = sessionUtils.getUserAuth();
			if(null!=auth){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("UPDATE_PLAYER_DISPLAY_NAME_PAGE");
				try {
						if(displayName.length()>1){
						PlayerDVO updtDevice=deviceService.updatePlayer(auth, playerId, displayName);
						VIEWDEVICEINFOBEANLOGGER.info("Successfully Player display name is updated.");
						displayName = updtDevice.getPlayerName(); 
						updSuccess=true;
					}
					else{
						updSuccess=false;
						displayName=deviceService.getPlayerDetails(auth, playerId).getPlayerName();
					}
				} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("VIEW_PLAYERS_INFO", "ClassName:viewDeviceInfoBean_MethodName:updatePlayerDisplayName", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
				}
			}
		}
	}
	
	public String getRenderPage() {
		return renderPage;
	}

	public void setRenderPage(String renderPage) {
		this.renderPage = renderPage;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setPlayerDVO(PlayerDVO playerDVO) {
		this.playerDVO = playerDVO;
	}

	public PlayerDVO getPlayerDVO() {
		return playerDVO;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerId() {
		return playerId;
	}

	

}