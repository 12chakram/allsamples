package biz.neustar.dece.backing.bean.sections.device;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.DeviceSummaryDVO;
import biz.neustar.dece.ui.dvo.UserAuthDVO;
import biz.neustar.dece.ui.service.UIDeviceService;

@ManagedBean(name="householdDevicesBean")
@RequestScoped
public class HouseholdDevicesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger HOUSEHOLDDEVICESBEANLOGGER= Logger.getLogger(HouseholdDevicesBean.class);
	
	private transient UIDeviceService deviceService=ServiceLocator.getUiDeviceService();
	
	private List<DeviceSummaryDVO> deviceList;
	
	private int deviceSlotsAvailable;
	
	private String householdDispName;
	
	private int devicesCount=-1;
	
	private transient SessionUtils sessionUtilsObj=new SessionUtils();
	
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	
	private String deviceCode;
	
	private boolean viewAddForm=false;
	
	private String usrAddEnabled;
	
	private String removeDeviceInfo="";
	
		
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
			retrieveDeviceList();
		return deviceList;
	}

	public String getRemoveDeviceInfo() {
		return removeDeviceInfo;
	}


	public void setRemoveDeviceInfo(String removeDeviceInfo) {
		this.removeDeviceInfo = removeDeviceInfo;
	}

	
	public void retrieveDeviceList(){
		if(sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null){
			UserAuthDVO auth = sessionUtilsObj.getUserAuth();
			if(auth!=null){
				auth.setPreviousWebPage(auth.getCurrentWebPage());
				auth.setCurrentWebPage("DEVICES_LIST_PAGE");
				if(null != sessionUtilsObj.getSession().getAttribute("removeDeviceStatus")){
					removeDeviceInfo=sessionUtilsObj.getSession().getAttribute("removeDeviceStatus").toString();
					sessionUtilsObj.getSession().removeAttribute("removeDeviceStatus");
				}
					try {
						deviceList=deviceService.getDeviceList(auth);
						HOUSEHOLDDEVICESBEANLOGGER.info("*******Successfully Device list is retrieved*******");
						if(null!=deviceList && deviceList.size()>0)
						{
							devicesCount=deviceList.size();
							DeviceSummaryDVO dSummDVO;
							int lstSize =deviceList.size();
							deviceSlotsAvailable=12-lstSize;				
							if(lstSize < 12)
							{
								while(lstSize<12){
									dSummDVO = new DeviceSummaryDVO();				
									dSummDVO.setDeviceId("NOTAVAILABLE");
									deviceList.add(dSummDVO);
									lstSize++;
								}			
							}
							
						}
						else{
							deviceSlotsAvailable=12;
							devicesCount=0;
						}
						String userAge="";
						String usrPrivilege="";
						if(null!=sessionUtilsObj.getSession().getAttribute("userAge")){
							userAge = sessionUtilsObj.getSession().getAttribute("userAge").toString();
						}
						if(null!=sessionUtilsObj.getSession().getAttribute("privilege")){
							usrPrivilege = sessionUtilsObj.getSession().getAttribute("privilege").toString();
						}
						if(usrAddEnabled==null || ""==usrAddEnabled){
							if("BASIC".equalsIgnoreCase(usrPrivilege) || deviceSlotsAvailable==0 || "CHILD".equalsIgnoreCase(userAge))
								usrAddEnabled="no";
							else
								usrAddEnabled="yes";
						}
						sessionUtilsObj.getSession().setAttribute("viewFormName", "frmDevicesDashboard");			
					} catch (UIDeceException e) {
					ExceptionUtils.processUIDECEException("HOUSEHOLD_DEVICES", "ClassName:householdDevicesBean_MethodName:getDeviceList", e, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtilsObj.getErrorTxtsBundle());
				}
				
				/*Iterator<DeviceSummaryDVO> itr = deviceList.iterator();
				while(itr.hasNext()){
					DeviceSummaryDVO dd = itr.next();
					System.out.println(dd.getDeviceId()+dd.getDisplayName()+dd.getDeviceType());
				}*/
				sessionUtilsObj.getSession().setAttribute("devNamLen", "6");
			}
		}
	}
	
	public void getAddDeviceCode()
	{
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
	
	public void setDeviceSlotsAvailable(int deviceSlotsAvailable) {
		this.deviceSlotsAvailable = deviceSlotsAvailable;
	}

	public int getDeviceSlotsAvailable() {
		return deviceSlotsAvailable;
	}

	public void setHouseholdDispName(String householdDispName) {
		this.householdDispName = householdDispName;
	}

	public String getHouseholdDispName() {
		if(null==householdDispName && sessionUtilsObj!=null && sessionUtilsObj.getSession()!=null)			
			householdDispName=sessionUtilsObj.getSession().getAttribute("householdName").toString();
		return householdDispName;
	}

	public void setDevicesCount(int devicesCount) {
		this.devicesCount = devicesCount;
	}

	public int getDevicesCount() {
		if(-1==devicesCount)
			retrieveDeviceList();
		return devicesCount;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setViewAddForm(boolean viewAddForm) {
		this.viewAddForm = viewAddForm;
	}

	public boolean isViewAddForm() {
		return viewAddForm;
	}

	public void setUsrAddEnabled(String usrAddEnabled) {
		this.usrAddEnabled = usrAddEnabled;
	}

	public String getUsrAddEnabled() {
		if(usrAddEnabled==null)
			retrieveDeviceList();
		return usrAddEnabled;
	}
	

}
