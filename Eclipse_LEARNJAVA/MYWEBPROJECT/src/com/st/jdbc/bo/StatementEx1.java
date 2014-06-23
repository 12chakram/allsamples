package com.st.jdbc.bo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementEx1 {
 
	public static void main(String s[])throws Exception{
	 // for getting connection 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl", "learn", "learn");
	
 System.out.println("connected to DB");
 
 Statement st = con.createStatement();
 ResultSet rs = st.executeQuery("select * from emp");
 
 System.out.println("query executed");
 
 while (rs.next()){
 int eno  =rs.getInt(1);
 String name = rs.getString(2);
 double sal = rs.getDouble(3);
 int dn = rs.getInt(4);
 
 System.out.println(+ eno + "\t" + name + "\t" + sal + "\t" + dn);
 
	 
 }//while
 con.close();
 
 System.out.println("connection close");
 
 }//main
 
	
	}//class
