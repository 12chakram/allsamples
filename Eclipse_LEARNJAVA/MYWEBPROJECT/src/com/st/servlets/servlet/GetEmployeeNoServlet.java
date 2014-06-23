package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GetEmployeeNoServlet extends GenericServlet {

	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
	
		Connection con = null;
	
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
 con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		
		
	Statement st = con.createStatement();	
	ResultSet rs = st.executeQuery("select * from emp");	
	
	out.println("<table border=1>");
	out.println("<tr><th>Empno</tr></th>");
	out.println("<tr><th>Name</tr></th>");
	
	
	
	while (rs.next()){
		
		out.println("<tr><td>");
		out.println(rs.getInt(1));
		out.println("<tr><td>");
		out.println(rs.getString(2));
		out.println("</td></tr>");
}//while
		
	out.println("</table>");
	
}//try
		catch(Exception e){
			
		}//catch
			
		}//service
}//class