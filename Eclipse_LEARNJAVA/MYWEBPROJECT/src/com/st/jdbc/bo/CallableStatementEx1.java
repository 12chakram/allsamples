package com.st.jdbc.bo;
// CallableStatementEx1.java
import java.sql.*;

public class  CallableStatementEx1

{
	public static void main(String s[]) throws Exception 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");

		System.out.println("done");	

// step1

	CallableStatement cs = con.prepareCall("{call SaveEmployee(?,?,?,?)}");

	// step2 

	cs.setInt (1,Integer.parseInt(s[0])); 
	cs.setString(2, "e888");
	cs.setInt(3, 3000);
	cs.setInt(4, 10);
	
	// step3 :- nothing to do 
	// step4 
	cs.execute();
	//step5
	System.out.println("employeesaved");
	con.close();
	} // main
} // class
	
	
	




	
	
	

