package com.st.jdbc.test;
import com.st.jdbc.dao.*;
import com.st.jdbc.dao.EmployeePersister;
import com.st.jdbc.vo.Employee;

//import santosh.Mylearn.EmployeePersister;
// test case to test the emp persister object 
public class EployeePersisterTestCase {
	public static void main (String s[])throws Exception {
		
		Employee e =new Employee();
		
		e.saveEmployee(333,"santosh", 1101, 11);
		e.getEmployee();
	System.out.println("save employee() executed");
		
		
		
		
		
		
	}// main 

}// class
