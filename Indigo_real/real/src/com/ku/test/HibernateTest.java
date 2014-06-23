package com.ku.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ku.real.hibernate.persist.User;
import com.ku.real.hibernate.utils.GetConnection;

public class HibernateTest {

	public static void main(String[] args) {
		
		
		Session se= GetConnection.getSessionFactory();
		
		
		String hql = "  from User where pass='kumar'";
				
		System.out.println(hql);
		
		Query q= se.createQuery(hql);
		
	
		
		
		User u = (User) q.uniqueResult();
		
		System.out.println(u.getUname());
		
		
//		List<User> u = q.list();
//		
//		for(User uu:u){
//			
//			System.out.printf(uu.getUname());
//			System.out.println(uu.getPass());
//			
//		}
		
		
		
		
		
		
		
	}//main
	
	
	
	
	
	
	
	
	
	
}//class
