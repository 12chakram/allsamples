package com.st.servlets.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ViewCookiesServlet extends HttpServlet {

	
 public void service(HttpServletRequest req ,HttpServletResponse res )
throws ServletException,IOException{

	
	//get the all the cookies in this request 
	 
	 Cookie cs[] = req.getCookies();
	 
	 res.setContentType("text/html");
	 
	 PrintWriter out = res.getWriter();
	 
	 if(cs==null){
		 
		 out.println("no cookies are available in this request");
		 
}//if
	 else{
		 
		 out.println("<table border=1>");
		 
		 out.println("<tr><th>Name</th>");
		 
		 out.println("<th>Value</th></tr>");
		 
		 for(Cookie c:cs){
			 
			 out.println("<tr><td>"+c.getName()+"</td><td>"+c.getValue()+"</td></tr>");
			 
	}//for
		 
		 out.println("</table>");
		 
}//else

	 out.println("<br><a href='Cookie.html'>AddCookie</a>");
	 

 }//service	
}//class
