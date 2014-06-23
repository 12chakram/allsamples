//ArithemeticServiceServlet.java 
package com.st.servlets.servlet;

import java.io.IOException;

import javax.servlet.*;

public class ArithemeticServiceServlet extends GenericServlet {

	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		try {
			
			Integer.parseInt(req.getParameter("op1"));
			Integer.parseInt(req.getParameter("op2"));
			
			String s = req.getParameter("submit");
			
			ServletContext ctxt = getServletContext();
			RequestDispatcher rd = ctxt.getNamedDispatcher(s);
			
			rd.forward(req, res);
			
			
	}//try 
		catch (NumberFormatException e){
			
			res.setContentType("text/html");
			res.getWriter().println("Error:Invalid inputs<br/>");
			req.getRequestDispatcher("/arithmetic.html").include(req,res);
			
			}//catch
		
		}//service

}//class
