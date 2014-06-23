package com.javatpoint.Test;

import com.javatpoint.Employee;
import com.javatpoint.service.Servicelocator;

public class EmployeeTest {

	public static void main(String[] args) {
		
		Employee employee = (Employee) Servicelocator.getService("employee");
		
		employee.show();
	}
	
}
