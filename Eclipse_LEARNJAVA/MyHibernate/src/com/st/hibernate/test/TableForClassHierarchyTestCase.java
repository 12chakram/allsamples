//TableForClassHierarchyTestCase.java

package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.EmployeeClass;
import com.st.hibernate.vo.HourlyEmployee;
import com.st.hibernate.vo.SalariedEmployee;

public class TableForClassHierarchyTestCase {


	public static void main(String[] args) throws Exception {
		

		Configuration cfg = new Configuration();
		cfg.configure();
		
		
	 SessionFactory sf = cfg.buildSessionFactory();
	   
	 Session session = sf.openSession();
	 
		
		Object o = session.get(EmployeeClass.class, 101);
		
		if(o instanceof SalariedEmployee){
			
			System.out.println("salariedEmployee");
			
			SalariedEmployee se = (SalariedEmployee)o;
			
			System.out.println(se.getEmpno());
			System.out.println(se.getName());
			System.out.println(se.getSal());
		
		}//if
		else{
			
			HourlyEmployee he =(HourlyEmployee)o;
			System.out.println("HourlyEmployee");
			
			System.out.println("------------------------");
			
			System.out.println(he.getEmpno());
			System.out.println(he.getName());
			System.out.println(he.getMaxHoursPerDay());
			System.out.println(he.getRatePerHour());
			
		}//else
		
		session.close();
		
		
	}//main

}//class
