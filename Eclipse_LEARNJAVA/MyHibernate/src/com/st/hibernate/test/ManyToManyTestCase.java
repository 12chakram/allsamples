//ManyToManyTestCase.java

package com.st.hibernate.test;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Course;
import com.st.hibernate.vo.Employee1;




public class ManyToManyTestCase {

	public static void main(String s[]) throws Exception{
		
		Configuration cfg= new Configuration();
		cfg.configure();
		
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session = sf.openSession();

		 
	
		

		Employee1 e = (Employee1)session.load(Employee1.class,104);
		
		System.out.println("Name:"+e.getName());
		
		System.out.println("Deptno:"+e.getDeptno());

		Collection<Course>courses = e.getCourses();
	

	for(Course c:courses){
		
		System.out.println(c.getCid()+"\t"+c.getCname());
		
		
		}//for
	
	
		
	
		
	/*
		Course  c= (Course)session.load(Course.class,3);
		
		System.out.println("CourseNmae:"+c.getCname()+"\t"+"CourseDuration:"+c.getDur());
		
		Collection<Employee1>empss=c.getEmps();
		System.out.println("The Registered Employees for this Course are :");
		for(Employee1 e:empss){
			
			
			System.out.println(e.getEmpno()+"\t"+e.getName());
			
			
}// for
		
	*/
		
		session.close();
		

	}//main
	
}//class
