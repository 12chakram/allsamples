package com.st.dao.jdbc.test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.commons.dbcp.*;

import com.st.dao.EmployeeDAOI;
import com.st.vo.Employee;
import com.st.dao.jdbc.EmployeeDAO;

public class  EmployeeDAOTestCase{

	public static void main (String s[]) throws Exception{


	BasicDataSource ds = new BasicDataSource();

	ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");

	ds.setUrl("jdbc:oracle:thin:@loclahost:1521:myorcl");

	ds.setUsername("learn");

	ds.setPassword("learn");

	JdbcTemplate jt = new JdbcTemplate(ds);

	EmployeeDAOI edao = new EmployeeDAO (jt);

	// testing save() method 

	Employee e = new Employee();

	e.setEmpno(100);
	e.setName("vayyala kumar");
	e.setSal(9999);
	e.setDeptno(100);
	edao.save(e);


	}//main

	   }//class

