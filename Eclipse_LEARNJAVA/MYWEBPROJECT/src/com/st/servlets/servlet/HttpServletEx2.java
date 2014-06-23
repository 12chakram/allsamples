//HttpServletEx2.java
package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletEx2 extends HttpServlet {

	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		res.setHeader("refresh", "15");
		
		res.setHeader("refresh","15; URL = http://www.google.co.in/");
		res.setHeader("refresh","15; URL =Test.doc");
		//res.setHeader("location", "value http://www.google.co.in/");
		 out.println("Date and time:<br/>");
		
		out.println(new Date());
		
		
		
	}//service
	
	}//class
