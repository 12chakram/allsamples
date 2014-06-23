//HttpServletEx1 .java
package com.st.servlets.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
//forthe HttpServletRequest, and HttpResponse, and HttpServlet
import java.io.*;
public class HttpServletEx1 extends HttpServlet {

	public void service(HttpServletRequest req , HttpServletResponse res ) 
	throws ServletException,IOException{
		
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.println("Response from HttpServletEx1<pre>");

		out.println("RequestMethod:"+req.getMethod());
		
		out.println("RequestURI:"+req.getRequestURI());
		
		
		out.println("ConextPath:"+req.getContextPath());
		
		out.println("ServletPath:"+req.getPathInfo());
		
		out.println("Query String:"+req.getQueryString());
		
		
			
	}//service
	
	
	

}//class
