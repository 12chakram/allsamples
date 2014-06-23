//TestCase.java

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.st.spring.vo.Employee

public class TestCase {

public stativc void main (String s[]) throws Exception {

	// 1.  create configuration Object

	Configuration cfg =  new Configuration ();

	cfg.Configure(); // this load the hibernate.cfg.xml into the system

	//2 creation SessionFactory

	SessionFactory  st = cfg.buildSessionFactory();

	// this create a sessionFactory performing various initializations for the HibernateSystem

	// 3 get the session

	Session session = sf.openSession();  // create a new session 

	// 4 Access the session object for storing or reading data from DB

	// read the data object

Employee e = (Employee)
	session.load(Employee.class,101);

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























}//class