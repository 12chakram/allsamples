
//HttpServletEx3.java
package com.st.servlets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletEx3 extends HttpServlet {

	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException{
		
		System.out.println("In HttpServletEx3");
		
		res.sendRedirect("http://www.google.co.in/");
		
		
		
	}//service
	
	}//class
