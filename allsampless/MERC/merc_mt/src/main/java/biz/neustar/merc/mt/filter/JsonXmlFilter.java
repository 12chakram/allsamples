package biz.neustar.merc.mt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.neustar.merc.mt.constants.MTConstants;
import biz.neustar.merc.mt.filter.impl.JsonXmlConverter;
import biz.neustar.merc.mt.wrappers.MTJsonRequestWrapper;
import biz.neustar.merc.mt.wrappers.MTJsonResponseWrapper;

/**
 * JSON <-> XML Servlet Filter
 *
 */
public class JsonXmlFilter implements Filter {

	private static Logger logger = Logger.getLogger(JsonXmlFilter.class);
	private FilterConfig filterConfig;
	private JsonXmlConverter jxConv;
	
	/**
	 * Init
	 * 
	 *  (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) 
			throws ServletException 
	{
		this.filterConfig = filterConfig;
		
		// get Spring bean
		this.jxConv = getJsonXmlConverterBean(filterConfig.getServletContext());
		
		// verify
		if (this.jxConv == null)
		{
			logger.fatal("Unable to get jsonXmlConverter Spring bean. Null!");
			throw new ServletException("Unable to get jsonXmlConverter Spring bean in Servlet Filter. Null!");
		}
	}
	
	/**
	 * Filter : identifies JSON payload through Accept/Content-Type HTTP HEADERS
	 * 
	 *  (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, 
						ServletResponse response,
						FilterChain chain)
						throws IOException, ServletException 
	{
		HttpServletRequest httpRequest=(HttpServletRequest)request;
	    HttpServletResponse httpResponse=(HttpServletResponse)response;
	    String fmt=null;

	    logger.info("Content-Type:"+httpRequest.getHeader(MTConstants.HTTP_HEADER_CONTENT_TYPE));
	    logger.info("Accept:"+httpRequest.getHeader("Accept"));

	   
	    if(httpRequest.getMethod().equalsIgnoreCase("GET")){
	    	fmt= httpRequest.getParameter("fmt");
	    }
	    
	    // when content-type header has value or, HTTPGET with "fmt"
	    if(httpRequest.getHeader(MTConstants.HTTP_HEADER_CONTENT_TYPE)!=null || fmt!=null)
	    {
		    // check - is content-type header json type
			if (httpRequest.getHeader(MTConstants.HTTP_HEADER_CONTENT_TYPE).contains(MTConstants.HTTP_HEADER_CONTENT_TYPE_VALUE_JSON) || "JSON".equalsIgnoreCase(fmt))
			{
				try 
				{
					// create wrappers to modify the content
					// this is again accessed from ValidationFilter for the payload
					MTJsonRequestWrapper cr = new MTJsonRequestWrapper(httpRequest, jxConv);
					MTJsonResponseWrapper cs = new MTJsonResponseWrapper(httpResponse); 	
			      
					// json/xml converter with custom wrappers
					chain.doFilter(cr,cs);
			      
			        // XML -> JSON response
			        if (cs instanceof MTJsonResponseWrapper) {
			        	String respData = new String(cs.getData());
			        	
			        	logger.info("Original response payload : " + respData);
			        	
			        	try
			        	{
							// convert xml response payload to json
				    	    respData = jxConv.convertToJson(respData);
			        	}
			        	catch (Exception ex)
			        	{
			        		logger.fatal("Converting response payload to JSON failed. " + ex.getMessage());
			        		// make simple json with error message and original xml
			        		respData = makeErrorJson(respData, "Conversion from XML to JSON failed: "+ex.getMessage());
			        	}
			    	    logger.info("Modified response payload : " + respData);
			    	    
			    	    response.setContentLength(respData.length());
		    		    response.getWriter().write(respData);
			        }
			      
				} 
				catch (Exception e)
				{
					logger.fatal("Applying JSON/XML filtering failed. " + e.getMessage());
				}
		    }
			else
			{
				// normal flow
				chain.doFilter(request, response);
			}
	    }//Header Content Type
		else
		{
			// normal flow
			chain.doFilter(request, response);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get JsonXmlConverter Spring Bean
	 * 
	 * @param servlet request
	 * @return Spring Bean
	 */
	private JsonXmlConverter getJsonXmlConverterBean(ServletContext context) 
	{
		WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		
		// verify
		if (springContext == null)
		{
			logger.fatal("Unable to get Spring Context from ServletRequest & ServletContext. Null!");
			return null;
		}
		
		JsonXmlConverter jxConv = (JsonXmlConverter)springContext.getBean("jsonXmlConverter");
		return jxConv;
	}

	/**
	 * Make simple JSON String from XML
	 * 	Note: This is called when JSON conversion from XML fails.
	 * 
	 * @param xml string
	 * @param error message
	 * @return simple json string
	 */
	private String makeErrorJson(String xml, String error)
	{
		
		String retJson = "{\"direct-xml\":{"
						+ "\"error-mesg\":{"
						+ "\"$\":\""
						+ error.replace("\"", "\\\"").replace("\n", " ")
						+ "\"},"
						+ "\"xml\":{"
						+ "\"$\":\""
						+ xml.replace("\"", "\\\"").replace("\n", " ") 
						+ "\"}"
						+ "}"
						+ "}";
		return retJson;
	}
}
