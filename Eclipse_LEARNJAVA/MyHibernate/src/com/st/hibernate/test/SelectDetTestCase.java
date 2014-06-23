////JoinTestCase.java
//
//
//package com.st.hibernate.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import com.st.hibernate.vo.Dept;
//import com.st.hibernate.vo.Employee;
//
//public class SelectDetTestCase {
//
//public static void main(String s[]) throws Exception{
//		
//		Configuration cfg=new Configuration();
//		
//		cfg.configure();
//		
//		SessionFactory sf= cfg.buildSessionFactory();
//		Session session=sf.openSession();
//		
//	
//		
//		String hql="SELECT d.dname,d.loc FROM Dept d";
//
//		//String hql = "SELECT e.empno,e.name,e.sal FROM Employee e";
//		Query q=session.createQuery(hql);
//		// no mparameters
//		
//		List<Object[]>results=q.list();
//		
//		List<String>  l  = new ArrayList<String>();
//		
//		l.add("Kumar");
//		l.add("Deva");
//		l.add("Kumar");
//		
//		//Iterator<String> i = l.enu();
//		while(i.hasNext()){
//			String sfd = i.next();
//		}
//		
//		
//		
//		
//		for(String ss : l){
//		System.out.println(ss);
//		}
//		
//		Set<String> se = new HashSet<String>(l);
//		
//		for(String sss: se){
//			System.out.println(sss);
//		}
//		
////		Kumar k = new Kumar();
////		k.setNmae(s)
////		Map<String, Obect> mymap1 = new HashMap<>();
////		m.put("Kumar", k);
////	.get("kumar");
////	
////	
//	
//	
//		
//		
//		System.out.println("Dname\tlocation");
//		//System.out.println("Empno\tEname\tSal");
//		System.out.println("----------------------------------------");
//		
//		
//		for(Object[]result :results){
//			
//			
//			System.out.print(result[0]+"   \t");
//			System.out.print(result[1]+"   \n");
//			//System.out.print(result[2]+"    \n");
//			
//			
//		}//for
//		
//		session.close();
//		
//		
//	}//main
//	
//}//class
