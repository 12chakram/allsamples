/**
 * 
 */
package biz.neustar.merc.mt.gateway;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import biz.neustar.merc.mt.constants.MTConstants;
import biz.neustar.merc.mt.vo.MercResponse;

/**
 * @author pkuninti
 * 
 */
public class MTResponseHandler implements ResponseHandler<MercResponse> {

	@Override
	public MercResponse handleResponse(HttpResponse httpResponse)
			throws ClientProtocolException, IOException {
		MercResponse mercResponse = null;
		if (httpResponse != null) {
			mercResponse = new MercResponse();
			if (httpResponse.getStatusLine() != null) {
				mercResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			}
			Header locationHeader = httpResponse.getFirstHeader(MTConstants.HTTP_HEADER_LOCATION);
			if (locationHeader != null) {
				mercResponse.setLocationHeader(locationHeader.getValue());
			}
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {				
				mercResponse.setResponseXML(EntityUtils.toString(httpEntity));
			}
		}
		return mercResponse;
	}
}
