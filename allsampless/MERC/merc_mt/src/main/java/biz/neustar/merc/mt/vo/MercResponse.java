/**
 * 
 */
package biz.neustar.merc.mt.vo;


/**
 * @author pkuninti
 *
 */
public class MercResponse {

	private int statusCode;
	private String responseXML;
	private String locationHeader;
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the responseXML
	 */
	public String getResponseXML() {
		return responseXML;
	}
	/**
	 * @param responseXML the responseXML to set
	 */
	public void setResponseXML(String responseXML) {
		this.responseXML = responseXML;
	}
	public String getLocationHeader() {
		return locationHeader;
	}
	public void setLocationHeader(String locationHeader) {
		this.locationHeader = locationHeader;
	}
	@Override
	public String toString() {
		return "MercResponse [statusCode=" + statusCode + ", responseXML="
				+ responseXML + ", locationHeader=" + locationHeader + "]";
	}

}
