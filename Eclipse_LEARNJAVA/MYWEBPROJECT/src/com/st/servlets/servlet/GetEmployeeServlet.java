package com.st.servlets.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.st.servlets.utils.DAOFactory;
import com.st.jdbc.daoI.EmployeeDAOI;
import com.st.jdbc.vo.Employee;

import javax.servlet.*;
public class GetEmployeeServlet extends GenericServlet{

	private 	EmployeeDAOI edao;
	
	
	public void init ()throws ServletException{
		
		try{
			
			edao = DAOFactory.getEmployeeDAO();
			
			}//try
		
		catch(Exception e){
			throw new ServletException();
			
	}//catch
		
}//init // this method called by the container
	
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
	
res.setContentType("text/html");		
	PrintWriter out = res.getWriter();
	
// get the employee using dao
	
	
		Collection<Employee> emps =  edao.getAllEmployee();
		

	
		
	out.println("<form action = 'deleteEmplyoees'>");
	
	out.println("<table border =1>");
	out.println("<tr><th></th>");
	
	out.println("<th>Empno</th>");
	out.println("<th>Name</th>");
	out.println("<th>Salary</th>");
	out.println("<th>Deptno</th></tr>");

	for(Employee e: emps){
		out.println("<tr>");
		
		out.println("<td>"<inut type = 'checkbox' name = 'eno' value = "' + e.eno+"'/></td>");
		out.println("<td>"+e.eno+"</td>");
		out.println("<td>"+e.en+"</td>");
		out.println("<td>"+e.salry+"</td>");
		out.println("<td>"+e.dno+"</td>");
		out.println("</tr>");
	}
		out.println("</table>");
	    out.println("input type = 'submit' value ='Delete'/></form>");
	    
}//service

}//class
