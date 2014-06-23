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

public class SelectNewTestCase {

public static void main(String s[]) throws Exception{
		
		Configuration cfg=new Configuration();
		
		cfg.configure();
		
		SessionFactory sf= cfg.buildSessionFactory();
		Session session=sf.openSession();
		
		
		
	
	
	
	
	
	
	String hql="SELECT NEW com.st.hibernate.vo.EmployeeMinDetails( e.empno,d.dname,e.sal) FROM Employee e JOIN e.deptDetails d";

		
		Query q=session.createQuery(hql);
		// no mparameters
		
		List<EmployeeMinDetails>results=q.list();
		
		System.out.println("Empno\tDname\tSal");
		System.out.println("----------------------------------------");
		
		
		for(EmployeeMinDetails emd : results){
			
			
			
			System.out.print(emd.getEmpno()+"\t");
			System.out.print(emd.getDname()+"\t");
			System.out.println(emd.getSal()+"\n");
		}//for
		
	
		session.close();
		
		
	}//main
	
}//class
