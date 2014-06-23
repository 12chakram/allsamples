//SubtractServlet.java
package com.st.servlets.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SubtractServlet 
extends GenericServlet {

	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		

		int op1 = Integer.parseInt(req.getParameter("op1"));
		int op2 = Integer.parseInt(req.getParameter("op2"));
		
		int result = op1-op2;
		
		// set the result into request as attribute 
		
		req.setAttribute("result", result);
		
		ServletContext ctxt = getServletContext();
		ctxt.getNamedDispatcher("Result").forward(req, res);
		
		}//service 

}//class