//LogginController.java

package com.st.spring.cnt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.springframework.web.servlet.*;

public class LogginController  implements Controller{

	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// sample code 
		
		String uname = req.getParameter("uname");
		String pass = req.getParameter("pass");
		
		// TO DO: access the DAO to validate the details
		
		if(uname.equals("user1")|| uname.equals("user2")){
			
			return new ModelAndView("/Home.jsp");
			
		}//if
		else{
			System.out.println("Enter correct user name");
			return new
			ModelAndView("/Logging.html");
			
		}//else
		
		
		
	}//handlerequest
	
	
	
	
	
	
	
	
	
	
}//class