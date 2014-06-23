package biz.neustar.merc.mt.filter;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSON <-> XML Servlet Filter
 *
 */
public class SecurityFilter implements Filter {

	private static Logger logger = Logger.getLogger(SecurityFilter.class.getName());
	private FilterConfig filterConfig;
	
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
		HttpServletRequest httpRequest=(HttpServletRequest)request;
	    HttpServletResponse httpResponse=(HttpServletResponse)response;

	    logger.info("Into Security Filter ");
			// normal flow
			chain.doFilter(request,response);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
