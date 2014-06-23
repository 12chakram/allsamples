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

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.neustar.merc.mt.constants.MTConstants;
import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.validators.MTXMLValidator;
import biz.neustar.merc.mt.wrappers.MTRequestWrapper;

/**
 * JSON <-> XML Servlet Filter
 *
 */
public class MTValidationFilter implements Filter {

	private static final Logger logger = Logger.getLogger(MTValidationFilter.class);
	private FilterConfig filterConfig;
	private MTXMLValidator mtXMLValidator;
	
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
		ServletContext servletContext = this.filterConfig.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		mtXMLValidator = (MTXMLValidator)webApplicationContext.getBean("mtXMLValidator");
	}
	
	/**
	 * Filter : identifies JSON payload through Accept HTTP HEADERS
	 * 
	 *  (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, 
						ServletResponse response,
						FilterChain chain)
						throws IOException, ServletException 
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if( MTConstants.REQUEST_METHOD_POST.equalsIgnoreCase(httpServletRequest.getMethod()) 
				|| MTConstants.REQUEST_METHOD_PUT.equalsIgnoreCase(httpServletRequest.getMethod()) ){
			
		    try {
		    	MTRequestWrapper mtRequestWrapper = new MTRequestWrapper(httpServletRequest);
		    	mtXMLValidator.validateXML(mtRequestWrapper.getInputStream());
		    	if(logger.isDebugEnabled()){
					logger.debug("doFilter() - XML validation successful");
				}
		    	chain.doFilter(mtRequestWrapper,response);	
		    } catch (MTException mte) {
		    	if(logger.isDebugEnabled()){
					logger.debug("doFilter() - XML validation failed - "+mte.getErrorDescription());
				}
		    	HttpServletResponse servletResponse = (HttpServletResponse)response;
		    	servletResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
		    	servletResponse.setContentType("text/plain");
		    	servletResponse.getWriter().write(mte.getErrorDescription());
		    }	 
		} else {
			if(logger.isDebugEnabled()){
				logger.debug("doFilter() - Request method is not POST or PUT. No need to do any XML validation");
			}
			chain.doFilter(request,response);	
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.filterConfig = null;
		
	}
}
