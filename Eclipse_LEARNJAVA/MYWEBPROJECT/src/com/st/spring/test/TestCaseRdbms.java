package com.st.jdbc.operations.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;

import com.st.jdbc.operations.UpdateEmpSalry;



public class TestCaseRdbms {

	public static void main(String s[]) throws Exception{
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-rdbms.xml");
		
		UpdateEmpSalry u = (updateEmpSalary)ac.getBean("updatesalRDBMS");
				
		
		
		
		
		
		
		
		
		
		
	}//main
	
	
	
	
	
	
	
	
	
}//class
