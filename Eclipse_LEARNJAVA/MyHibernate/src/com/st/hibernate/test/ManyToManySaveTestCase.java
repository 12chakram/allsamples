package com.st.hibernate.test;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Course;
import com.st.hibernate.vo.Employee1;

public class ManyToManySaveTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		
		
		
		Configuration cfg= new Configuration();
		cfg.configure();
		
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session = sf.openSession();

		 
		
		Transaction tx = session.beginTransaction();
		
		Employee1 e1 = new Employee1();
		
		e1.setEmpno(105);
		e1.setName("ravi");
		e1.setSal(333);
		e1.setDeptno(30);
		
		Course c1 =  new Course();
		
	 c1.setCid(4);
		 c1.setCname("oracle");
	 c1.setDur(30);
	 c1.setFee(900);
		
		
		
		
		
		
		
		tx.commit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}//main

}//class
