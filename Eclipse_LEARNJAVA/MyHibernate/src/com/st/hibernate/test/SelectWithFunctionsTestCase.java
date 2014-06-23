//SelectNewTestCase

package com.st.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Dept;
import com.st.hibernate.vo.Employee;
import com.st.hibernate.vo.EmployeeMinDetails;

public class SelectWithFunctionsTestCase {

public static void main(String s[]) throws Exception{
		
		Configuration cfg=new Configuration();
		
		cfg.configure();
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session=sf.openSession();
		
		/*
		
	String hql= "SELECT COUNT (e)  FROM Employee e";
	Query q=session.createQuery(hql);
	List<Object>	result=(List<Object>)q.list();
	
	for(Object count:result){
		System.out.println("totalEmployees:"+count);
	}//
	*/
		
		
		/*
		String hql= "SELECT COUNT (e)  FROM Employee e";
		Query q=session.createQuery(hql);
		 Object total=q.uniqueResult();
		 System.out.println("totalEmployees:"+total);
		 */

		String hql= "SELECT e.sal,avg(sal) FROM Employee e GROUP BY e.sal";
		Query q=session.createQuery(hql);
		
List<Object[]>results=q.list();
		
		System.out.println("Empno\tDeptno\tDname");
		System.out.println("----------------------------------------");
		
		
		
		
		for(Object[]result :results){
			
			Employee employee=(Employee)result[0];
			
		System.out.println(employee.getSal());
		session.close();
		
		}//for
	}//main
	
}//class
