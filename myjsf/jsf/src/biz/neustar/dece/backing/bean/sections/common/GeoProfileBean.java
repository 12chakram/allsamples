package biz.neustar.dece.backing.bean.sections.common;

import java.util.HashMap;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import biz.neustar.dece.portal.utils.APIErrorMapUtil;
import biz.neustar.dece.portal.utils.ExceptionUtils;
import biz.neustar.dece.portal.utils.ServiceLocator;
import biz.neustar.dece.portal.utils.SessionUtils;
import biz.neustar.dece.ui.UIDeceException;
import biz.neustar.dece.ui.dvo.GeoInformationDVO;
import biz.neustar.dece.ui.service.UIGEOService;

@ManagedBean(name="geoProfileBean")
@RequestScoped
public class GeoProfileBean {

	SessionUtils sessionUtils=new SessionUtils();
	private FacesContext context = FacesContext.getCurrentInstance();
	private HashMap<String,String> errorValueMap=new HashMap<String, String>();
	private HashMap<String, String> geoProfileValueMap=new HashMap<String, String>();
	private String country="US";
	HttpSession session=(HttpSession)FacesContext.getCurrentInstance(). getExternalContext().getSession(true);

	public GeoProfileBean(){
		try{
			String hostAddress=((HttpServletRequest)context.getExternalContext().getRequest()).getRemoteHost();
			UIGEOService uiGEOService=ServiceLocator.getUIGEOService();
			GeoInformationDVO geoInformationDVO=uiGEOService.getGeoData(hostAddress);
			if(null!=geoInformationDVO && null!=geoInformationDVO.getCountryCode() && ""!=geoInformationDVO.getCountryCode()){
				country=geoInformationDVO.getCountryCode();
			}
		}catch (UIDeceException ex) {
			ExceptionUtils.processUIDECEException("Geo Profile", "GeoProfileBean", ex, APIErrorMapUtil.uiErrorCodeFieldMap, errorValueMap, sessionUtils.getErrorTxtsBundle());
		}
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}