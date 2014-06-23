package com.st.hibernate.test;

import org.hibernate.*;
import org.hibernate.cfg.*;




import com.st.hibernate.vo.Employee;
import com.st.hibernate.vo.PersonalDetails;

public class SaveTeasCase {

	public static void main (String s[]){
		
		
		
		
		
		//create the Configuration object
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		// get the session factory 
		
	 SessionFactory sf = cfg.buildSessionFactory();
	   
	 // open the session
	 
	 Session session = sf.openSession();
	 
	 Transaction tx = session.beginTransaction();
	 
	 
		Employee e = new Employee();
		
		e.setEmpno(999);
		e.setName("kumar");
		e.setSal(300);
		e.setDeptno(90);
		
		
		
		  PersonalDetails pd = new PersonalDetails();
		
		pd.setEmpno(999);
		pd.setFname("vayyala");
		pd.setLname("kumar");
		
		e.setPddetails(pd);
		
		session.save(e);
		
		//session.saveOrUpdate(e);
		
		//session.delete(e);
		
		
		tx.commit();
		
		System.out.println("Employee save sucessfully");
		System.out.println("Personaldetails save sucessfully");
		session.close();


    

   
		
		

		
		
		
		
	}//main
	
}//class
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

