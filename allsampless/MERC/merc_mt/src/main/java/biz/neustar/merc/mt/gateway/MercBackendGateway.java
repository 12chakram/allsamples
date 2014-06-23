/**
 * 
 */
package biz.neustar.merc.mt.gateway;

import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.exceptions.MTException.MTResponseCode;
import biz.neustar.merc.mt.vo.MTRequest;
import biz.neustar.merc.mt.vo.MercResponse;

//TODO: Should we use @Component or @Service
 
@Component

public class MercBackendGateway {

	private static final Logger logger = Logger.getLogger(MercBackendGateway.class);
	private @Value("${merc.backend.connection.timeout}") int connectionTimeout;
	private @Value("${merc.backend.socket.timeout}") int socketTimeout;
	private @Value("${merc.backend.connection.stale.check}") boolean connectionStaleCheck;
	
	/**
	 * 
	 * @param mtRequest
	 * @param mbURL
	 * @param requestPayload
	 * @return
	 * @throws MTException
	 */
	public MercResponse processPostRequest(MTRequest mtRequest, String mbURL, String requestPayload) throws MTException {
		if (logger.isDebugEnabled()) {
			logger.debug("processPostRequest()\n\t connectionTimeout="+connectionTimeout+" URL=" + mbURL + " \n\tPayload=" + requestPayload);
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(mbURL);
			httpPost.setEntity(new StringEntity(requestPayload));			
			loadHeadersAndSetConnectionParams(httpPost, mtRequest);
			httpPost.addHeader("Content-Type","application/xml");
			return httpClient.execute(httpPost, new MTResponseHandler());
		} catch (Exception ex) {
			logger.error("processPostRequest() - Exception while communicating with Merc Backend API - " + ex.getMessage());
			ex.printStackTrace();
			throw new MTException(MTResponseCode.INTERNAL_SERVER_ERROR);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * 
	 * @param mtRequest
	 * @param mbURL
	 * @return
	 * @throws MTException
	 */
	public MercResponse processGetRequest(MTRequest mtRequest,String mbURL) throws MTException{
		if (logger.isDebugEnabled()) {
			logger.debug("processGetRequest()\n\t URL=" + mbURL);
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet(mbURL);
			loadHeadersAndSetConnectionParams(httpGet, mtRequest);
			return httpClient.execute(httpGet, new MTResponseHandler());
		} catch (Exception ex) {
			logger.error("processGetRequest() - Exception while communicating with Merc Backend API - " + ex.getMessage());
			ex.printStackTrace();
			throw new MTException(MTResponseCode.INTERNAL_SERVER_ERROR);
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * 
	 * @param mtRequest
	 * @param mbURL
	 * @return
	 * @throws MTException
	 */
	public MercResponse processDeleteRequest(MTRequest mtRequest, String mbURL) throws MTException{
		if (logger.isDebugEnabled()) {
			logger.debug("processDeleteRequest()\n\t URL=" + mbURL);
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpDelete httpDelete = new HttpDelete(mbURL);
			loadHeadersAndSetConnectionParams(httpDelete, mtRequest);
			return httpClient.execute(httpDelete, new MTResponseHandler());
		} catch (Exception ex) {
			logger.error("processDeleteRequest() - Exception while communicating with Merc Backend API - " + ex.getMessage());
			ex.printStackTrace();
			throw new MTException(MTResponseCode.INTERNAL_SERVER_ERROR);
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * 
	 * @param mtRequest
	 * @param mbURL
	 * @param requestPayload
	 * @return
	 * @throws MTException
	 */
	public MercResponse processPutRequest(MTRequest mtRequest, String mbURL, String requestPayload)  throws MTException{
		if (logger.isDebugEnabled()) {
			logger.debug("processPutRequest()\n\t URL=" + mbURL + " \n\tPayload=\n" + requestPayload);
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPut httpPut = new HttpPut(mbURL);
			httpPut.setEntity(new StringEntity(requestPayload));
			loadHeadersAndSetConnectionParams(httpPut, mtRequest);
			httpPut.addHeader("Content-Type","application/xml");		
			return httpClient.execute(httpPut, new MTResponseHandler());
		} catch (Exception ex) {
			logger.error("processPutRequest() - Exception while communicating with Merc Backend API - " + ex.getMessage());
			ex.printStackTrace();
			throw new MTException(MTResponseCode.INTERNAL_SERVER_ERROR);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	/**
	 * 
	 * @param mbRequest
	 * @param mtRequest
	 */
	private void loadHeadersAndSetConnectionParams(HttpRequest mbRequest, MTRequest mtRequest){
		if(mbRequest != null) {
			mbRequest.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeout);
			mbRequest.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
			mbRequest.getParams().setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, connectionStaleCheck);
			if(mtRequest != null && mtRequest.getHeaders() != null && mtRequest.getHeaders().size() > 0){
				for (Map.Entry<String, String> entry : mtRequest.getHeaders().entrySet()) {
					mbRequest.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
	}
}
