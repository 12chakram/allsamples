package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.servlets.dao.UserDAODummy;
import com.st.servlets.daoI.UserDAOI;

public class LogingServlet  extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		
		
		
		String uname = req.getParameter("uname");
		String pword = req.getParameter("pword");
		
		// Access the DAO for validation 
		
		//if(uname.equals("kumar")||pword.equals("vayyala")){
			
		
		
		
		UserDAOI udao = new UserDAODummy();
		if (udao.findUser(uname,pword)){
			
			//user is valid thus forward request to home servlet that prepare home page 
			
			RequestDispatcher rd = req.getRequestDispatcher("/home");
			rd.forward(req, res);
			
	}//if
		else{
			
			//user invalid, present login view along with error message 
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.println("please enter correct user nmae");
			RequestDispatcher rd= req.getRequestDispatcher("/Loging.html");
			rd.include(req, res);
			
}//else
	}//service
}//class
		
		
		
		
		
		
		
		
		
		
		
		
