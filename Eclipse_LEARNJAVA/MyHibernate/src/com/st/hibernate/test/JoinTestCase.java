//JoinTestCase.java


package com.st.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Dept;
import com.st.hibernate.vo.Employee;

public class JoinTestCase {

public static void main(String s[]) throws Exception{
		
		Configuration cfg=new Configuration();
		
		cfg.configure();
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session=sf.openSession();
		
	
		
		String hql="FROM Employee e JOIN e.deptDetails d";

		
		Query q=session.createQuery(hql);
		// no mparameters
		
		List<Object[]>results=q.list();
		
		System.out.println("Empno\tDeptno\tPlace\tDname");
		System.out.println("----------------------------------------");
		
		
		
		
		for(Object[]result :results){
			
			Employee employee=(Employee)result[0];
			
			Dept dept=(Dept)result[1];
			
			//System.out.print(result[0]+"\t"+result[1]);
			
			System.out.print(employee.getEmpno()+"\t");
			System.out.print(dept.getDeptno()+"\t");
			System.out.print(dept.getLoc()+"\t");
			System.out.print(dept.getDname()+"\n");
		}//for
		
		session.close();
		
		
	}//main
	
}//class
