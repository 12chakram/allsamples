package com.st.jdbc.test;
import com.st.jdbc.dao.EmployeeDAO;
import com.st.jdbc.daoI.EmployeeDAOI;

public class EmployeeDAOTestcase {

	public static void main(String s[]) throws Exception{
		
		EmployeeDAOI edo = new EmployeeDAO();
		
		edo.remove(222);  // done
		System.out.println("remove done");
		
		
		edo.getEmp();
		System.out.println("getEmp  done");
		
		edo.saveEmployee(666, "santosh", 900, 11);
		System.out.println("svaeEmployee  done");
		
		edo.updateEmp(103);
		System.out.println("update done");
		
		edo.save();
		System.out.println("save done");
		
		
	System.out.println("all are done");	
	
	}
	
	
	
	
	
	
}
