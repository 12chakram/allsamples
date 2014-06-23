package biz.neustar.merc.mt.filter;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * JSON <-> XML Servlet Filter
 *
 */
public class CachingFilter implements Filter {

	private static Logger logger = Logger.getLogger(CachingFilter.class.getName());
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
		logger.info("Into Caching Filter ");
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
