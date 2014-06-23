package com.st.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class GettingOracleConnection {
	static Connection con;
	public static Connection getConnection ()throws Exception{
		if(con == null){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		}
		return con;
	}
}