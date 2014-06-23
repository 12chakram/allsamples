package com.st.servlets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCookieServlet extends HttpServlet {
 public void service(HttpServletRequest req ,HttpServletResponse res )
 throws ServletException,IOException{
	 
	 
	  String name=req.getParameter("name");
	  
	  String value=req.getParameter("value");
	 
	 //create a new Cookie
	  
	  Cookie c = new Cookie(name,value);
	  
	  //Add the cookie to client
	  
	res.addCookie(c);
	
	res.setContentType("text/html");
	
	res.getWriter().println("Cookieadded suscussfully<br/>");
	
	req.getRequestDispatcher("/Cookie.html").include(req, res);
	
	  
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }//service
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
