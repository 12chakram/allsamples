//FromTestCase.java
package com.st.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Employee;

public class FromTestCase {

	public static void main(String s[]) throws Exception{
		
		Configuration cfg=new Configuration();
		
		cfg.configure();
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session=sf.openSession();
		
		String hql="FROM Employee e";
		//String hql="FROM Employee e WHERE e.deptno=10";
		
		Query q=session.createQuery(hql);
		// no mparameters
		List<Employee>result=(List<Employee>)q.list();
		
		for(Employee e:result){
			
			System.out.println(e.getEmpno()+"\t"+e.getName());
			
			
		}//for
		
		session.close();
		
		
	}//main
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
