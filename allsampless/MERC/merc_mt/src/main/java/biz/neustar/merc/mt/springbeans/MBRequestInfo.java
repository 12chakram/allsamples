/**
 * 
 */
package biz.neustar.merc.mt.springbeans;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author pkuninti
 *
 */
public class MBRequestInfo {

	private String requestXSLT;
	private Set<String> urlsToExecuteOnFailure;
	private String resourceNamespace;
	private ArrayList<String> idsToReplaceInPayload;
	public String getRequestXSLT() {
		return requestXSLT;
	}
	public void setRequestXSLT(String requestXSLT) {
		this.requestXSLT = requestXSLT;
	}
	public Set<String> getUrlsToExecuteOnFailure() {
		return urlsToExecuteOnFailure;
	}
	public void setUrlsToExecuteOnFailure(Set<String> urlsToExecuteOnFailure) {
		this.urlsToExecuteOnFailure = urlsToExecuteOnFailure;
	}
	public String getResourceNamespace() {
		return resourceNamespace;
	}
	public void setResourceNamespace(String resourceNamespace) {
		this.resourceNamespace = resourceNamespace;
	}
	public ArrayList<String> getIdsToReplaceInPayload() {
		return idsToReplaceInPayload;
	}
	public void setIdsToReplaceInPayload(ArrayList<String> idsToReplaceInPayload) {
		this.idsToReplaceInPayload = idsToReplaceInPayload;
	}
	@Override
	public String toString() {
		return "MBRequestInfo [requestXSLT=" + requestXSLT
				+ ", urlsToExecuteOnFailure=" + urlsToExecuteOnFailure
				+ ", resourceNamespace=" + resourceNamespace
				+ ", idsToReplaceInPayload=" + idsToReplaceInPayload + "]";
	}
	
}
