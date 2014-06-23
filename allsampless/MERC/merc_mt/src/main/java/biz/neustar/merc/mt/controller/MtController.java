package biz.neustar.merc.mt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import biz.neustar.merc.mt.controller.util.MTControllerUtil;
import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.service.MTBusinessService;
import biz.neustar.merc.mt.vo.MercResponse;

@Controller
public class MtController {
	private static final Logger logger = Logger
			.getLogger(MtController.class);

	@Autowired
	MTBusinessService mtBusinessService;

	
	@RequestMapping(method = RequestMethod.POST, value = {"/User/Streams"}, headers="Accept=application/xml,application/json", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_XML_VALUE })
	@PayloadRoot(localPart = "Stream")
	public  @ResponseBody String handleStreamPost(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response) {
		
		return handlePost(requestBody,request,response);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/User/PurchaseTitles"}, headers="Accept=application/xml,application/json", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_XML_VALUE })
	@PayloadRoot(localPart = "Purchase")
	public  @ResponseBody String handlePurchasePost(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response) {
		
		return handlePost(requestBody,request,response);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/Users"}, headers="Accept=application/xml,application/json", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_XML_VALUE })
	@PayloadRoot(localPart = "User")
	public  @ResponseBody String handleUserPost(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response) {
		
		return handlePost(requestBody,request,response);
	}
	
	/**
	 * This method handles all the POST requests
	 * @param requestBody
	 * @param request
	 * @param response
	 * @return
	 */
	
	private @ResponseBody String handlePost(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("handlePost() - POST Request received for "+request.getPathInfo());
		}
		MercResponse mtResponse = null;
		try {
			mtResponse = mtBusinessService.processRequest(MTControllerUtil.getMTRequest(request, requestBody));
			MTControllerUtil.buildServletResponse(response, mtResponse);
		} catch (MTException mte) {
			logger.warn("Exception in handlePost() - "+mte);
			MTControllerUtil.buildServletResponse(response, mtResponse, mte);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("handlePost() - "+mtResponse);
		}
		return ((mtResponse != null) ? mtResponse.getResponseXML() : null);
	}
	
	/**
	 * This method handles all PUT requests for Categories
	 * @param requestBody
	 * @param request
	 * @param response
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/User/Category/{resourceId}", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE })
	@PayloadRoot(localPart = "Category")
	public @ResponseBody String handleCategoryPut( @RequestBody String requestBody, HttpServletRequest request,
	                HttpServletResponse response, @PathVariable String resourceId )
	{
		return handlePut(requestBody, request, response, resourceId);
	}
	
	
	/**
	 * This method handles all PUT requests
	 * @param requestBody
	 * @param request
	 * @param response
	 * @param resourceId
	 * @return
	 */
	private @ResponseBody String handlePut(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable String resourceId) {
		if (logger.isDebugEnabled()) {
			logger.debug("handlePut() - PUT Request received for "
					+ request.getPathInfo());
		}
		MercResponse mtResponse = null;
		try {
			String configMapKey = request.getPathInfo().replace(resourceId,
					"{PathVariable}")
					+ "_PUT";
			mtResponse = mtBusinessService.processRequest(MTControllerUtil
					.getMTRequest(request, requestBody, resourceId),
					configMapKey);
			MTControllerUtil.buildServletResponse(response, mtResponse);
		} catch (MTException mte) {
			logger.warn("Exception in handlePut() - " + mte);
			MTControllerUtil.buildServletResponse(response, mtResponse, mte);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("handlePut() - " + mtResponse);
		}
		return ((mtResponse != null) ? mtResponse.getResponseXML() : null);
	}
	

	/**
	 * This method handles all the GET request for the URLs with no dynamic values
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/User", produces = { MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody String   handleGet( HttpServletRequest request, HttpServletResponse response)
	{
		if (logger.isDebugEnabled()) {
			logger.debug("handleGet() - GET Request received for "+request.getPathInfo());
		}
		MercResponse mtResponse = null;
		try {
			mtResponse = mtBusinessService.processRequest(MTControllerUtil.getMTRequest(request, null));
			MTControllerUtil.buildServletResponse(response, mtResponse);
		} catch (MTException mte) {
			logger.warn("Exception in handleGet() - "+mte);
			MTControllerUtil.buildServletResponse(response, mtResponse, mte);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("handleGet() - "+mtResponse);
		}
		return ((mtResponse != null) ? mtResponse.getResponseXML() : null);
	}

	/**
	 * This method handles all the GET requests with one dynamic value in the URL. Currently it doesn't support multiple dynamic values in the URL.
	 * @param request
	 * @param response
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/Title/{resourceId}/Overview", produces = { MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody String   handleGet( HttpServletRequest request, HttpServletResponse response, @PathVariable String resourceId)
	{
		if (logger.isDebugEnabled()) {
			logger.debug("handleGet() - GET Request received for "+request.getPathInfo());
		}
		MercResponse mtResponse = null;
		try {
			String configMapKey = request.getPathInfo().replace(resourceId, "{PathVariable}")+ "_GET";
			mtResponse = mtBusinessService.processRequest(MTControllerUtil.getMTRequest(request, null, resourceId), configMapKey);
			MTControllerUtil.buildServletResponse(response, mtResponse);
		} catch (MTException mte) {
			logger.warn("Exception in handleGet() - "+mte);
			MTControllerUtil.buildServletResponse(response, mtResponse, mte);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("handleGet() - "+mtResponse);
		}
		return ((mtResponse != null) ? mtResponse.getResponseXML() : null);
	}
	
	/**
	 * This method handles all the DELETE requests
	 * @param request
	 * @param response
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/User/Stream/{resourceId}", produces = { MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody String handleDelete( HttpServletRequest request,
            HttpServletResponse response, @PathVariable String resourceId )
	{
		if (logger.isDebugEnabled()) {
			logger.debug("handleDelete() - DELETE Request received for "+request.getPathInfo());
		}
		MercResponse mtResponse = null;
		try {
			String configMapKey = request.getPathInfo().replace(resourceId, "{PathVariable}")+ "_DELETE";
			mtResponse = mtBusinessService.processRequest(MTControllerUtil.getMTRequest(request, null, resourceId), configMapKey);
			MTControllerUtil.buildServletResponse(response, mtResponse);
		} catch (MTException mte) {
			logger.warn("Exception in handleDelete() - "+mte);
			MTControllerUtil.buildServletResponse(response, mtResponse, mte);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("handleDelete() - "+mtResponse);
		}
		return ((mtResponse != null) ? mtResponse.getResponseXML() : null);
	}
}
