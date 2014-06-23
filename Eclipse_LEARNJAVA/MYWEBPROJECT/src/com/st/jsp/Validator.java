package com.st.jsp;

import com.st.vo.Employee;


public class Validator {

	public boolean validate(Employee e){
		
		// all validations 
		
		//sample
		
		
		return(e.getDeptno()>9 && e.getDeptno()<100);
		
		
		
		
	}//validate
	
	
	
	
	
	
}//class
