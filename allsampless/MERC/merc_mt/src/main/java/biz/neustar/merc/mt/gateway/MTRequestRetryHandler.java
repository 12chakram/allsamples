/**
 * 
 */
package biz.neustar.merc.mt.gateway;

import java.io.IOException;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

/**
 * @author pkuninti
 *
 */
public class MTRequestRetryHandler implements HttpRequestRetryHandler{

	private static final Logger logger = Logger.getLogger(MTRequestRetryHandler.class);
	
	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
		// TODO Do not retry for now..
		logger.info("\n\n\n\n\n in retryRequest()\n\n\n\n\n");
		return false;
	}

}
