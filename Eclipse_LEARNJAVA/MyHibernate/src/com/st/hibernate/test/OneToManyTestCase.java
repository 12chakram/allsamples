package com.st.hibernate.test;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.st.hibernate.vo.Dept;
import com.st.hibernate.vo.Employee;






public class OneToManyTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
				
				
				Configuration cfg = new Configuration();
				cfg.configure();
				
				SessionFactory sf = cfg.buildSessionFactory();
				Session session = sf.openSession();

		Dept d =(Dept)session.load(Dept.class,  Integer.parseInt(args[0]));
		
		
		System.out.println("DName:"+d.getDname());
		System.out.println("Location:"+d.getLoc());
		
		Collection<Employee> emps = d.getEmps();
		for(Employee e:emps){
			System.out.println(e.getName());
		}
		
			
			//System.out.println("Name:"+((Employee) e).getName());
			
		//for(Employee e = d.getEmps()){
			//System.out.println(e.getName()+"\t"+e.getEmpno());
		//}
		
		
		//Collection<Course>courses = e.getCourses();
		//for(Course c:courses){
			
			//System.out.println(c.getCid());
			
			
			//}//for
		
		session.close();
		
	
	}//main

}//class
