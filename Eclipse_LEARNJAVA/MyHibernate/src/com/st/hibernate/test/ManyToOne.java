//ManyToOne.java
package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Dept;
import com.st.hibernate.vo.Employee;

public class ManyToOne {

	public static void main(String[] args) {
	
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Employee e =(Employee)session.load(Employee.class, 101);
		
		System.out.println("Empno:"+e.getEmpno());
		
		System.out.println("Nmae:"+e.getName());
		
		
		
		Dept d= e.getDeptDetails();
		
		System.out.println("Dname:"+d.getDname());
		System.out.println("Loc:"+d.getLoc());
		System.out.println("Deptno:"+d.getDeptno());
		
	}//main

}//class
