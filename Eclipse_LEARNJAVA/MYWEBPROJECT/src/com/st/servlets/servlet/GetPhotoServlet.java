//GetPhotoServlet.java
package com.st.servlets.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class GetPhotoServlet  extends  GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
	
		String eno= req.getParameter("eno");
		try{
		//getting connection 
		
		Statement st = con.cretaeStatement 
	 ResultSet rs = st.executeQuery("select photo from employee_pd where empno ="+eno);
		
		if (rs.next()){
			InputStream is = rs.getBinaryStream(1); // to get blob ar large content object
			
			//write to client 
			res.setContentType("image/gif");
			
			ServletOutputStream sos = res.getOutputStream();
			
			int i = is.read();
			while(i!=-1){
				sos.write(i);
				i= is.read();
			}
		}//if 
			else{
				res.setContentType("text/html");
				res.getWriter().println("sorry the employee has not submited the photo");
				
				}//else 
		}//try
	catch(Exception e ){
		
	}
	
		
		
	}//service

}//class 

