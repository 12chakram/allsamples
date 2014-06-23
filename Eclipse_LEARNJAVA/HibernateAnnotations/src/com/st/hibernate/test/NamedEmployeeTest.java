//NamedEmployeeTest.java

package com.st.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.st.hibernate.vo.Employee;

public class NamedEmployeeTest {


	//private static List<Employee> //emps;

	public static void main(String args[])throws Exception{
		
	
	AnnotationConfiguration cfg = new AnnotationConfiguration ();
	cfg.configure();
	
	
	SessionFactory sf= cfg.buildSessionFactory();
	Session session=sf.openSession();
	
	
	
	
	
	
	
	Query q=session.getNamedQuery("query1");
	// no mparameters
	
	List <Employee>emps=q.list();
	
	//emps=(List <Employee>)q.list();
	
	
	for(Employee e :emps){
		
		System.out.println("Name:"+e.getName());
	

	
	}//for
		
		
	}//main
	
}//class
