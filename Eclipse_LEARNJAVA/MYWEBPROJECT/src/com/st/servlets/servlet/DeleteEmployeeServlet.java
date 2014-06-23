package com.st.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.st.jdbc.daoI.EmployeeDAOI;
import com.st.servlets.utils.DAOFactory;

public class DeleteEmployeeServlet extends GenericServlet {
private EmployeeDAOI edao;

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
		
		// get employeenos to delete 
		
		String empnos = req.getParameter("eno");
		if (empnos == null){
			
			out.println("no employess are selected to delete ");
			
		}//if 
		else {
			
			for (String eno:empnos){
				edao.remove(Integer.parseInt(eno));
				out.println("Employee"+eno+"removed<br/>");
				
		}//for
			
	}//elase

		
		
		out.println("<br/>");
		out.println("<a href = 'getEmployees'>viewEmployees</a>");
		
}//service 

}//class
