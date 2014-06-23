/**
 * 
 */
package biz.neustar.merc.mt.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pkuninti
 *
 */
public class MTRequest {

	private String urlPath;
	private String queryString;
	private Map<String, String> headers;
	private String method;
	private String payload;
	private String mToken;
	private String userId;
	private String accountId;
	private String nodeId;//Storefront Id
	//TODO this needs to be somewhere else
	private String entitlementId;
	//TODO may need to change this to list
	private String pathVariableValue;
	
	/**
	 * @return the urlPath
	 */
	public String getUrlPath() {
		return urlPath;
	}
	/**
	 * @param urlPath the urlPath to set
	 */
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}
	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}
	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	/**
	 * @return the mToken
	 */
	public String getmToken() {
		return mToken;
	}
	/**
	 * @param mToken the mToken to set
	 */
	public void setmToken(String mToken) {
		this.mToken = mToken;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public void addHeader(String name, String value){
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		if(name != null && value != null){
			headers.put(name, value);
		}
	}
	public String getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}
	public String getPathVariableValue() {
		return pathVariableValue;
	}
	public void setPathVariableValue(String pathVariableValue) {
		this.pathVariableValue = pathVariableValue;
	}
	@Override
	public String toString() {
		return "MTRequest [urlPath=" + urlPath + ", queryString=" + queryString
				+ ", headers=" + headers + ", method=" + method + ", payload="
				+ payload + ", mToken=" + mToken + ", userId=" + userId
				+ ", accountId=" + accountId + ", nodeId=" + nodeId
				+ ", entitlementId=" + entitlementId + ", pathVariableValue="
				+ pathVariableValue + "]";
	}
	
	
	
}
