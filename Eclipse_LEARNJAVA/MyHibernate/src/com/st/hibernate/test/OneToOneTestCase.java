//OneToOneTestCase.java
package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.Employee;
import com.st.hibernate.vo.PersonalDetails;
/**
 * @author user
 *
 */
public class OneToOneTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception  {
		
Configuration cfg = new Configuration();
cfg.configure();

SessionFactory sf = cfg.buildSessionFactory();

Session session = sf.openSession();
/*
Employee e = (Employee) session.load(Employee.class, 101);

System.out.println("Name:"+e.getName());
System.out.println("Sal:"+e.getSal());

PersonalDetails pd = e.getPddetails();




System.out.println("Lname:"+pd.getLname());

*/

PersonalDetails pd = (PersonalDetails)session.load(PersonalDetails.class, 101);

//Employee e = (Employee)session.load(Employee.class, 101);

Employee e = pd.getEmp();

System.out.println("Name:"+pd.getFname());

System.out.println("sal:"+e.getSal());

System.out.println("name:"+e.getName());
System.out.println("Empno:"+e.getEmpno());

session.close();
	
		
	}//main


	
	
}//class
