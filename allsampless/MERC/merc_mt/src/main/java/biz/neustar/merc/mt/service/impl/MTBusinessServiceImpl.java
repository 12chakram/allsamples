/**
 * 
 */
package biz.neustar.merc.mt.service.impl;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import biz.neustar.merc.mt.constants.MTConstants;
import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.exceptions.MTException.MTResponseCode;
import biz.neustar.merc.mt.gateway.MercBackendGateway;
import biz.neustar.merc.mt.service.MTBusinessService;
import biz.neustar.merc.mt.springbeans.MBRequestInfo;
import biz.neustar.merc.mt.springbeans.MTRequestInfo;
import biz.neustar.merc.mt.transformer.MTXSLTransformer;
import biz.neustar.merc.mt.vo.MTRequest;
import biz.neustar.merc.mt.vo.MercResponse;

/**
 * @author pkuninti
 * 
 */
public class MTBusinessServiceImpl implements MTBusinessService {

	private static final Logger logger = Logger.getLogger(MTBusinessServiceImpl.class);

	private Map<String, MTRequestInfo> mtRequestMap;
	
	@Autowired
	private MercBackendGateway mercBackendGateway;

	@Autowired
	private MTXSLTransformer mtXSLTransformer;

	@Override
	public MercResponse processRequest(MTRequest mtRequest) throws MTException{
		String mtRequestMapKey = (mtRequest.getUrlPath() + "_" + mtRequest.getMethod());
		return processRequest(mtRequest, mtRequestMapKey);
	}
	/**
	 * 
	 */
	@Override
	public MercResponse processRequest(MTRequest mtRequest, String mtRequestMapKey) throws MTException{
		MercResponse mercResponse = null;
		if (logger.isDebugEnabled()) {
			logger.debug("processRequest() " + mtRequest);
		}
		if(mtRequest == null || mtRequest.getUrlPath() == null || mtRequest.getMethod() == null) {
			throw new MTException(MTResponseCode.BAD_REQUEST);
		}
		
		if (mtRequestMap == null || mtRequestMap.size() == 0 || !mtRequestMap.containsKey(mtRequestMapKey)) {
			if(logger.isDebugEnabled()) {
				logger.debug("processRequest() - " + mtRequestMapKey + " not found in config file");
			}
			throw new MTException(MTResponseCode.BAD_REQUEST);
		}
		final String httpMethod = mtRequest.getMethod();
		MTRequestInfo mtRequestInfo = mtRequestMap.get(mtRequestMapKey);
		if (mtRequestInfo != null && mtRequestInfo.getMbRequestMap() != null && mtRequestInfo.getMbRequestMap().size() > 0) {
			for (Map.Entry<String, MBRequestInfo> entry : mtRequestInfo.getMbRequestMap().entrySet()) {
				MBRequestInfo mbRequestInfo = entry.getValue();
				if (mbRequestInfo != null){
					String mbURL = getMercBackendURL(mtRequest, entry.getKey());
					String payload = null;
					if (MTConstants.REQUEST_METHOD_POST.equals(httpMethod)) {
						payload = getPayload(mtRequest, mbRequestInfo);
						mercResponse = mercBackendGateway.processPostRequest(mtRequest, mbURL, payload);	
						if(mercResponse == null || mercResponse.getStatusCode() != HttpStatus.SC_CREATED) {
							logger.warn("processRequest() Post Request failed for MB URL="+mbURL);
							//resource create failed, check if we need to delete any resources already created in this transaction
							if(mbRequestInfo.getUrlsToExecuteOnFailure() != null && mbRequestInfo.getUrlsToExecuteOnFailure().size() > 0){
								//TODO Call Delete APIs
							}
							return mercResponse;
						}else {// successfully created resource
							loadResourceIdsFromResponseLocationHeader(mtRequest, mercResponse, mbRequestInfo);
						}
					} else if (MTConstants.REQUEST_METHOD_GET.equals(httpMethod)) {
						mercResponse = mercBackendGateway.processGetRequest(mtRequest, mbURL);
					} else if (MTConstants.REQUEST_METHOD_DELETE.equals(httpMethod)) {
						mercResponse = mercBackendGateway.processDeleteRequest(mtRequest, mbURL);
					} else if (MTConstants.REQUEST_METHOD_PUT.equals(httpMethod)) {
						payload = getPayload(mtRequest, mbRequestInfo);
						mercResponse = mercBackendGateway.processPutRequest(mtRequest, mbURL, payload);
					} 
					if(logger.isDebugEnabled()){
						logger.debug("processRequest() \n\tmbURL="+mbURL+" \n\t"+mercResponse);
					}
				}
			}
		}		
		return mercResponse;
	}

	/**
	 * @param mtRequest
	 * @param mercResponse
	 * @param mbRequestInfo
	 */
	private void loadResourceIdsFromResponseLocationHeader(MTRequest mtRequest, MercResponse mercResponse, MBRequestInfo mbRequestInfo) {
		if(mbRequestInfo.getResourceNamespace() != null && mbRequestInfo.getResourceNamespace().trim().length() > 0) {	
			if(MTConstants.ACCOUNT_ID_NAMESPACE.equals(mbRequestInfo.getResourceNamespace()) 
					&& mercResponse.getLocationHeader() != null 
					&& mercResponse.getLocationHeader().contains(MTConstants.ACCOUNT_ID_NAMESPACE) ){
				mtRequest.setAccountId(mercResponse.getLocationHeader().substring(mercResponse.getLocationHeader().indexOf(MTConstants.ACCOUNT_ID_NAMESPACE)));
			} else if(MTConstants.ENTITLEMENT_ID_NAMESPACE.equals(mbRequestInfo.getResourceNamespace()) 
					&& mercResponse.getLocationHeader() != null 
					&& mercResponse.getLocationHeader().contains(MTConstants.ENTITLEMENT_ID_NAMESPACE) ){
				mtRequest.setEntitlementId(mercResponse.getLocationHeader().substring(mercResponse.getLocationHeader().indexOf(MTConstants.ENTITLEMENT_ID_NAMESPACE)));
			}
		}
	}

	/**
	 * 
	 * @param mtRequest
	 * @param mbURL
	 * @return
	 */
	private String getMercBackendURL(MTRequest mtRequest, String mbURL) {
		if(mbURL.contains(MTConstants.USER_ID) && mtRequest.getUserId() != null) {
			mbURL = mbURL.replace(MTConstants.USER_ID, mtRequest.getUserId());
		}
		if(mbURL.contains(MTConstants.ACCOUNT_ID) && mtRequest.getAccountId() != null) {
			mbURL = mbURL.replace(MTConstants.ACCOUNT_ID, mtRequest.getAccountId());
		}
		if(mbURL.contains(MTConstants.PATH_VARIABLE) && mtRequest.getPathVariableValue() != null) {
			mbURL = mbURL.replace(MTConstants.PATH_VARIABLE, mtRequest.getPathVariableValue());
		}
		//TODO - need to check any other ids??
		return mbURL;
	}
	
	/**
	 * 
	 * @param mtRequest
	 * @param mbRequestInfo
	 * @return
	 */
	private String getPayload(MTRequest mtRequest, MBRequestInfo mbRequestInfo) throws MTException{
		String modifiedPayload = null;
		if (mbRequestInfo.getRequestXSLT() != null	&& mbRequestInfo.getRequestXSLT().trim().length() > 0) {	
			modifiedPayload = mtXSLTransformer.transform(mtRequest.getPayload(),mbRequestInfo.getRequestXSLT());
			if(mbRequestInfo.getIdsToReplaceInPayload() != null && mbRequestInfo.getIdsToReplaceInPayload().size() > 0) {
				for(String id : mbRequestInfo.getIdsToReplaceInPayload()) {
					if(MTConstants.ACCOUNT_ID.equals(id) && mtRequest.getAccountId() != null){
						modifiedPayload = modifiedPayload.replace(id, mtRequest.getAccountId());
					}else if(MTConstants.ENTITLEMENT_ID.equals(id) && mtRequest.getEntitlementId() != null){
						modifiedPayload = modifiedPayload.replace(id, mtRequest.getEntitlementId());
					}
				}
			}
		} 
		return ((modifiedPayload != null) ? modifiedPayload : mtRequest.getPayload());
		
	}

	/**
	 * @return the mtRequestMap
	 */
	public Map<String, MTRequestInfo> getMtRequestMap() {
		return mtRequestMap;
	}

	/**
	 * @param mtRequestMap
	 *            the mtRequestMap to set
	 */
	public void setMtRequestMap(Map<String, MTRequestInfo> mtRequestMap) {
		this.mtRequestMap = mtRequestMap;
	}

}
