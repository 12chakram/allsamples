
// TestCase.java 

package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Employee;

public class TestCase {

public static void main (String s[]) throws Exception {

	// 1.  create configuration Object

	Configuration cfg =  new Configuration ();

	cfg.configure(); // this load the hibernate.cfg.xml into the system

	//2 creation SessionFactory

	SessionFactory  sf = cfg.buildSessionFactory();

	// this create a sessionFactory performing various initializations for the HibernateSystem

	// 3 get thesession

	Session session = sf.openSession();  // create a new session 

	// 4 Access the session object for storing or reading data from DB

	// read the data object
	
Employee e = (Employee)
 
  session.load(Employee.class,Integer.parseInt(s[0]));  //  org.hibernate.ObjectNotFoundException
 




//e.setName("santosh sir ");  // this name will be update  on 101 




   //session.get(Employee.class,222); //  java.lang.NullPointerException
//System.out.println("Got the e");      //   need to check 


/*
Employee e1 = (Employee)

session.load(Employee.class,222);

System.out.println(e==e1);
*/

// this read the data from the DB

// here 101 is the value of the primary key top read the data 

// using Employee Object

System.out.println("Name:"+e.getName());
System.out.println("Sal:"+e.getSal());
System.out.println("Deptno:"+e.getDeptno());

// 5 close the session 

session.close();

}//main

}//class























