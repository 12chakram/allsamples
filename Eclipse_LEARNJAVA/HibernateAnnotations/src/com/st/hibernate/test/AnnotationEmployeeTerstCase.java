//AnnotationEmployeeTerstCase.java
package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


import com.st.hibernate.vo.Employee;

public class AnnotationEmployeeTerstCase {

	public static void main(String args[]){
		
		
		
		//create the AnnotationConfiguration object
		
		AnnotationConfiguration cfg = new AnnotationConfiguration ();
		cfg.configure();
		
		//get the SessionFactory
		
		SessionFactory sf=cfg.buildSessionFactory();
		
		//open the session
		
		Session session = sf.openSession();
		
		Employee e=(Employee)session.load(Employee.class,101);
		
		
		System.out.println("Name:"+e.getName());
		System.out.println("salary:"+e.getSal());
		
		session.close();
		
		
	}//main
	
}//class
