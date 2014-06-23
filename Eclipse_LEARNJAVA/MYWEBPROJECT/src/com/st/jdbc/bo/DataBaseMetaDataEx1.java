//DataBaseMetaDataEx1
package com.st.jdbc.bo;
import java.sql.*;
public class DataBaseMetaDataEx1 {

	public static void main(String S[])throws Exception
	{

 Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
 
 
 System.out.println("connect to database");
 
 
 DatabaseMetaData dbms  = con.getMetaData();
 
		System.out.println(dbms.getDatabaseProductName());
		System.out.println(dbms.getDatabaseProductVersion());
		System.out.println(dbms.getDriverName());
		System.out.println(dbms.getDriverVersion());
		System.out.println(dbms.allTablesAreSelectable());
		System.out.println(dbms.allProceduresAreCallable());
 		dbms.allTablesAreSelectable();
 	
		con.close();
		
		
}// main

	
	
	
}// class
