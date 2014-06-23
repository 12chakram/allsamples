package com.st.servlets.servlet;

import java.io.IOException;

import com.st.jdbc.vo.EmployeeVO;

import com.st.jdbc.daoI.ServletEmployeeDAOI;
import com.st.spring.bo.AccountsBo;
import com.st.spring.bo.EmployeeBo;


import java.io.*;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
public class CreateEmployeeServlet extends GenericServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		try{
			//read the request parameter
			
			EmployeeVO e = new EmployeeVO();
			e.eno = Integer.parseInt(req.getParameter("eno"));
			
			e.en = req.getParameter("en");
			
		e.salary = Double.parseDouble(req.getParameter("salary"));
			
			e.dno = Integer.parseInt(req.getParameter("dno"));
			BeanFactory beans = new XmlBeanFactory(new ClassPathResource("spring-cfg.xml"));
			
			 //get the spring bean from the container 
			 
			 Object o = beans.getBean("emp");
			EmployeeBo ebo = (EmployeeBo)o;
			
			ebo.saveEmployee(e);
			
			
			System.out.println("Employee datails saved sucessfully");
			
	}//try
		catch(Exception e){
			e.printStackTrace();
			out.println("invalid inputs or problem in accessing database try again");
		}//catch
		
	}

}
