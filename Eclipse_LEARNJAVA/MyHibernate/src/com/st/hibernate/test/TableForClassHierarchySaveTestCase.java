//TableForClassHierarchyTestCase.java

package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.EmployeeClass;
import com.st.hibernate.vo.HourlyEmployee;
import com.st.hibernate.vo.SalariedEmployee;

public class TableForClassHierarchySaveTestCase {


	public static void main(String[] args) throws Exception {
		

		Configuration cfg = new Configuration();
		cfg.configure();
		
		
	 SessionFactory sf = cfg.buildSessionFactory();
	   
	 Session session = sf.openSession();
	  Transaction tx= session.beginTransaction();
		

	  SalariedEmployee se = new SalariedEmployee(); 
		
	  se.setEmpno(109);
	  se.setName("deva");
	  se.setSal(50000);
	  
	  session.save(se);


	  HourlyEmployee he = new HourlyEmployee();
	  
	  he.setEmpno(108);
	  he.setName("ku");
	  he.setMaxHoursPerDay(8);
	  he.setRatePerHour(350);
	  
	 session.save(he);
	  
	  tx.commit();
	  
	  System.out.println("Employee saved into hourly ");
	  
		session.close();
		
		
	}//main

}//class
