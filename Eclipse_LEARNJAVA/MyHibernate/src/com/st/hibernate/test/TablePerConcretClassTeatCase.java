package com.st.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.st.hibernate.vo.HourlyEmployeePc;
import com.st.hibernate.vo.SalariedEmployeePc;

public class TablePerConcretClassTeatCase {

	
	
	public static void main(String[] args) throws Exception {
		

		Configuration cfg = new Configuration();
		cfg.configure();
		
		
	 SessionFactory sf = cfg.buildSessionFactory();
	   
	 Session session = sf.openSession();
	 
	 SalariedEmployeePc se = (SalariedEmployeePc) session.get(SalariedEmployeePc.class,102);
	
	HourlyEmployeePc hep =(HourlyEmployeePc)session.get(HourlyEmployeePc.class, 102);
	
	System.out.println(+se.getEmpno()+"\t"+se.getSal());
	
	System.out.println(+hep.getEmpno()+"\t"+hep.getRatePerHour());
	
	
	session.close();
	
	
	
	}//main
	
	
	
	
	
	
	
}//class
