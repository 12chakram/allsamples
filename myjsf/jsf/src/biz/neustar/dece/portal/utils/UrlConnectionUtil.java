/*
 * Copyright (c) 2010 Neustar, Inc. All Rights Reserved.
 *
 * UrlConnectionUtil.java
 */
package biz.neustar.dece.portal.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * The <code>UrlConnectionUtil.java</code> class encapsulates objects defined
 * for DECE.
 * 
 * @author Vikas Charak vikas.charak@neustar.com
 * @version $Revision: 1.1 $ $Date: 2012/01/10 13:06:44 $
 */
public class UrlConnectionUtil
{
	private static final Logger UrlConnectionUtil = Logger.getLogger(UrlConnectionUtil.class);

	/**
	 * Gets connection to the requested URL
	 * 
	 * @param servletContext
	 * @param fileUrl
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public URLConnection openUrlConnection( ServletContext servletContext, String fileUrl ) throws URISyntaxException,
	        IOException
	{
		String timeOutStr = (String) servletContext.getAttribute("http.urlconnect.timeoutinmillis");
		int timeOutInMillis = 5000; // default
		if( null != timeOutStr && !"".equals(timeOutStr.trim()) )
		{
			timeOutInMillis = Integer.parseInt(timeOutStr.trim());
		}

		Proxy proxy = getProxy(servletContext);	
		 		
		URI uri = new URI(fileUrl);
		URL url = uri.toURL();
		URLConnection urlConn = null;
		if( null != proxy )
		{
			UrlConnectionUtil.info("Connecting to [" + fileUrl + "] using proxy [" + proxy.address() + "]");
			urlConn = url.openConnection(proxy);
			urlConn.setReadTimeout(timeOutInMillis);
			urlConn.setConnectTimeout(timeOutInMillis);
			urlConn.connect();
			UrlConnectionUtil.info("Connection to [" + fileUrl + " successful.");
		}
		else
		{
			UrlConnectionUtil
			        .error("No proxy found. Check config.properties and make sure http.proxyHost and http.proxyPort are specified");
		}
		return urlConn;
	}

	/**
	 * Gets connection to the requested URL
	 * 
	 * @param servletContext
	 * @param fileUrl
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public byte[] getByteStream(ServletContext servletContext, String fileUrl) throws HttpException, IOException
	{
		
		byte[] byteStream = null;
		String timeOutStr = (String) servletContext.getAttribute("http.urlconnect.timeoutinmillis");
		int timeOutInMillis = 5000; // default
		if( null != timeOutStr && !"".equals(timeOutStr.trim()) )
		{
			timeOutInMillis = Integer.parseInt(timeOutStr.trim());
		}
		
		
		ProxyHost proxy = getProxyHost(servletContext);	
		
		String host = (String) servletContext.getAttribute("http.proxyHost");
		String port = (String) servletContext.getAttribute("http.proxyPort");
		int p = -1;
		if( null != port && !"".equals(port) )
		{
			p = Integer.parseInt(port);
		}
		
		HttpClient httpclient = new HttpClient();		
		httpclient.getHostConfiguration().setProxy(host, p);			 
		GetMethod httpget = new GetMethod(fileUrl);
		HttpMethodParams params = new HttpMethodParams();
		params.setSoTimeout(timeOutInMillis);
		httpget.setParams(params);
		
		try { 
		    httpclient.executeMethod(httpget);
		    byteStream=httpget.getResponseBody();
		    UrlConnectionUtil.info("byteStream length: "+byteStream.length);
		    UrlConnectionUtil.info(byteStream);
		    
		    
		 } finally {
		    httpget.releaseConnection();
		 }
		 return byteStream;
	}
	
	/**
	 * 
	 * @param servletContext
	 * @return
	 */
	private Proxy getProxy( ServletContext servletContext )
	{
		Proxy proxy = null;
		String host = (String) servletContext.getAttribute("http.proxyHost");
		String port = (String) servletContext.getAttribute("http.proxyPort");
		int p = -1;
		if( null != port && !"".equals(port) )
		{
			p = Integer.parseInt(port);
		}
		// set the proxy for URL connection of flash
		if( null != host && !"".equals(host) && p > -1 )
		{
			SocketAddress addr = new InetSocketAddress(host, p);
			proxy = new Proxy(Proxy.Type.HTTP, addr);
		}
		return proxy;
	}

	/**
	 * 
	 * @param servletContext
	 * @return
	 */
	private ProxyHost getProxyHost( ServletContext servletContext )
	{
		ProxyHost proxy = null;
		String host = (String) servletContext.getAttribute("http.proxyHost");
		String port = (String) servletContext.getAttribute("http.proxyPort");
		int p = -1;
		if( null != port && !"".equals(port) )
		{
			p = Integer.parseInt(port);
		}
		// set the proxy for URL connection of flash
		if( null != host && !"".equals(host) && p > -1 )
		{
			
			proxy = new ProxyHost(host, p);
		}
		return proxy;
	}
	
}
