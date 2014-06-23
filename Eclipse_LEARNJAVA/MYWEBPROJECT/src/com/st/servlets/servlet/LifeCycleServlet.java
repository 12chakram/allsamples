package com.st.servlets.servlet;
import javax.servlet.*;

import java.io.*;

public class LifeCycleServlet implements Servlet {

	public void init(ServletConfig sc)throws ServletException{
	
		
		this. sc=sc;
		System.out.println("in init method ");
		
		// here we want to implement the code for initializing this servlet
		
		
}//init

	public void service(ServletRequest req,ServletResponse res)throws IOException,ServletException{
		
		System.out.println("in service method ");
		
		//here we want to implement the code execute for every time
		//the client request for this servlet
		
		// this is used to write some content to the client
		//i.e. response content 
		PrintWriter out =res.getWriter();
		
		out.println("hello from servlet");
		
	}//service 
	
	public void destroy(){
		
		System.out.println("in destroy method");
		
		// here we implement the finalization code
		
}//desrtoy

	public ServletConfig getServletConfig(){
		return sc;
		
}//getservletconfig
	
	
	public String getServletInfo(){
		
		return "LifecycleServlet";
		
	}//getservletInfo
	
	private ServletConfig sc;
	
}//class
