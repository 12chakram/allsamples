package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateEmployeeServlet1  extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String uname = req.getParameter("username");
		
		out.println("createEmployeeForm<br>");
		out.println("<form method = 'post'action = 'createEmployee'><pre>");
		
		out.println("Empno:<input type = 'text' name = 'empno'/>");
		out.println("Name:<input type = 'text' name = 'ename'/>");
		out.println("Salary:<input type = 'text' name = 'sal'/>");
		out.println("Deptno:<input type = 'text' name = 'deptno'/>");
		
		out.println("<input type = 'hidden'name = 'username' value=   '"  +uname+ "'  />");
		out.println("<input type = 'submit' value = 'AddEmployee'/> </pre></form>");
		
		out.println("<a href = 'home?username="+uname+"'>Back to Home</a>");
	}// doGet
		
		public void doPost(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException{
			
		String uname = req.getParameter("username");
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal = req.getParameter("sal");
		String deptno = req.getParameter("deptno");
		
		// we want to set all these details into employee VO and send it to the 
		//DAO for saving into DB
		
		//demo code
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.println("<pre>Employeedetails:");
		out.println("<b>Empno</b>:"+empno);
		out.println("<b>ename</b>:"+ename);
		out.println("<b>sal</b>:"+sal);
		out.println("<b>deptno</b>:"+deptno);
		
		out.println("<b>Addedby</b>:"+uname);
		
		out.println("<b>Addedon</b>:"+new Date());
		
		out.println("<a href = 'home?username="+uname+"'>Back to Home</a>");
		
	}//dopost
	

}//class
