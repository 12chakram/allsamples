//ResultServlet.java
package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ResultServlet  extends GenericServlet{

	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		//get the result from the request attribute
		
		Integer result = (Integer) req.getAttribute("result");
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		out.println("Result:<b>"+result+"</b><br/>");
		
		req.getRequestDispatcher("/arithemetic.html").include(req, res);
		
	
	}//service

}//class
