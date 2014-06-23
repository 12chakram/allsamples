package com.st.hibernate.test;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.st.hibernate.vo.PersonalDetails;
import com.st.hibernate.vo.Address;

public class PersonalDetailsTestCase {

	
	
	public static void main(String s[]){ 
	
		//create the Configuration object
	
	Configuration cfg = new Configuration();
	cfg.configure();
	
	// get the session factory 
	
 SessionFactory sf = cfg.buildSessionFactory();
   
 // open the session
 
 Session session = sf.openSession();
 
 Transaction tx = session.beginTransaction();
 
 
 // create persistent class objects (here pesonalDeatils and Address classes)
 
 
 PersonalDetails pd = new PersonalDetails();
 
       // set the values to personalDetails table 
 
  pd.setEmpno(777);
  pd.setFname("kumar");
  pd.setLname("vayyala");
 
  Address addr = new Address();
  
  addr.setStreet("bhar");
  addr.setCity("hyd");
  addr.setState("AP");
  addr.setPin(500583);
  
  
  
  pd.setAddr(addr);
  
  session.save(pd);
  
  
 /*
 
 PersonalDetails pd = (PersonalDetails)
 
 session.load(PersonalDetails.class, 103);
 
 
 Address addr = pd.getAddr();
 
 System.out.println("city:"+pd.getAddr().getCity());
 System.out.println("Lname:"+addr.getPddetails().getLname());
 */
 
 
 
 
 

 
 
  
  tx.commit();
  
  session.close();
  
  //System.out.println("PersonalDetails svae in to the DB");
  
 
	}// main 
	
}//class
