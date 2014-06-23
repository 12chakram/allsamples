package com.st.spring.cnt;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.st.spring.vo.Employee;

public class EmployeeValidator implements Validator {

	public boolean supports(Class commandClass) {
		
		return 
		commandClass.equals(Employee.class);
	}

	public void validate(Object command, Errors e) {
		
		Employee emp = (Employee) command;
		if(emp.getEmpno()<=100){
			// empno is notvalid
			e.rejectValue("empno", "errors.minlength ", new Object[]{"empno",100},"empno cannot be less than 100");
			
			//similar for other filelds 
			
			
			
		}//if
		
		
		
	

	}

}
