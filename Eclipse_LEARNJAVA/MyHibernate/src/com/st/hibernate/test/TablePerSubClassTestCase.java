// TablePerSubClassTestCase .java
package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.HourlyEmployee;
import com.st.hibernate.vo.SalariedEmployee;

public class TablePerSubClassTestCase {


	public static void main(String[] args) throws Exception {
		

		Configuration cfg = new Configuration();
		cfg.configure();
		
		
	 SessionFactory sf = cfg.buildSessionFactory();
	   
	 Session session = sf.openSession();
	  Transaction tx= session.beginTransaction();
		
	
	  SalariedEmployee se = new SalariedEmployee(); 
		
	  se.setEmpno(107);
	  se.setName("devav");
	  se.setSal(999);
	  
	  session.save(se);
	  System.out.println("Employee saved into salaried ");
	
	  
	  HourlyEmployee he = new HourlyEmployee(); 
		
	  he.setEmpno(106);
	  he.setName("deva");
	  he.setMaxHoursPerDay(5);
	  he.setRatePerHour(600);
	  session.save(he);
	  
	  
	  System.out.println("Employee saved into hourly ");
	  
	  
	  
	  
	  
	  
	  
	  
	
	  
	  
	tx.commit();
	  
	  
		session.close();
	
	}//main	

}//class
