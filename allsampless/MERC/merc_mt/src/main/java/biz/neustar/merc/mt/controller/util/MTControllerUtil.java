/**
 * 
 */
package biz.neustar.merc.mt.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import biz.neustar.merc.mt.constants.MTConstants;
import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.vo.MTRequest;
import biz.neustar.merc.mt.vo.MercResponse;

/**
 * @author pkuninti
 *
 */
public class MTControllerUtil {
	
	/**
	 * 
	 * @param request
	 * @param reqBody
	 * @return
	 */
	public static MTRequest getMTRequest(HttpServletRequest request, @RequestBody String reqBody){
		MTRequest mtRequest = new MTRequest();
		mtRequest.setPayload(reqBody);
		mtRequest.setUrlPath(request.getPathInfo());
		mtRequest.setMethod(request.getMethod());
		//TODO
		//NotSure how we get the user/account/node info from MToken
		//hardcoding the values for now
		mtRequest.setUserId(request.getHeader("UserId"));
		mtRequest.setAccountId(request.getHeader("AccountId"));
		mtRequest.setNodeId(request.getHeader("NodeId"));
		mtRequest.addHeader("RoleName",request.getHeader("RoleName"));
		return mtRequest;
	}

	public static MTRequest getMTRequest(HttpServletRequest request,  String reqBody, String pathVariableValue){
		MTRequest mtRequest = getMTRequest(request, reqBody);
		mtRequest.setPathVariableValue(pathVariableValue);
		return mtRequest;
	}
	/**
	 * 
	 * @param response
	 * @param mercResponse
	 */
	public static void buildServletResponse(HttpServletResponse response, MercResponse mercResponse){
		if(mercResponse != null){
			response.setStatus(mercResponse.getStatusCode());
			//TODO - we should not set the Merc Backend URL in MT location header. Setting this
			//for now just for MT testing.
			response.setHeader(MTConstants.HTTP_HEADER_LOCATION, mercResponse.getLocationHeader());
			//TODO -Set headers for caching			
		} else{//TODO - if null set 500 status code??
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
		response.setContentType("text/xml");
		
	}

	/**
	 * 
	 * @param response
	 * @param mtResponse
	 * @param mtException
	 */
	public static void buildServletResponse(HttpServletResponse response,MercResponse mtResponse, MTException mtException) {
		if (mtException != null) {
			response.setStatus(mtException.getErrorId());
			if (mtResponse == null) {
				mtResponse = new MercResponse();
			}
			mtResponse.setResponseXML(mtException.getErrorDescription());
		}
	}
}
