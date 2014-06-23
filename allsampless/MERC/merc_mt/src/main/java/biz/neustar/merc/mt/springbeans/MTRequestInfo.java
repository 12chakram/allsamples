/**
 * 
 */
package biz.neustar.merc.mt.springbeans;

import java.util.LinkedHashMap;


/**
 * @author pkuninti
 *
 */
public class MTRequestInfo {

	private boolean asynchronous = false;
	private LinkedHashMap<String, MBRequestInfo> mbRequestMap;
	private String responseXSLT;
	/**
	 * @return the asynchronous
	 */
	public boolean isAsynchronous() {
		return asynchronous;
	}
	/**
	 * @param asynchronous the asynchronous to set
	 */
	public void setAsynchronous(boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	/**
	 * @return the mbRequestMap
	 */
	public LinkedHashMap<String, MBRequestInfo> getMbRequestMap() {
		return mbRequestMap;
	}
	/**
	 * @param mbRequestMap the mbRequestMap to set
	 */
	public void setMbRequestMap(LinkedHashMap<String, MBRequestInfo> mbRequestMap) {
		this.mbRequestMap = mbRequestMap;
	}
	/**
	 * @return the responseXSLT
	 */
	public String getResponseXSLT() {
		return responseXSLT;
	}
	/**
	 * @param responseXSLT the responseXSLT to set
	 */
	public void setResponseXSLT(String responseXSLT) {
		this.responseXSLT = responseXSLT;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MTRequestInfo [asynchronous=" + asynchronous
				+ ", mbRequestMap=" + mbRequestMap + ", responseXSLT="
				+ responseXSLT + "]";
	}
}
