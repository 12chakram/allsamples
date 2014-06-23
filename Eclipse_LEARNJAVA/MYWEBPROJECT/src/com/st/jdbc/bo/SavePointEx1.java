package com.st.jdbc.bo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointEx1 {

	public static void main(String s[])throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		
		System.out.println("connected to DATABASE");
	
		Statement st =con.createStatement();
		
		con.setAutoCommit(false);
		
		System.out.println("autocommit false");
		
		st.executeUpdate("insert into emp values (001,'vayyala',1000,50)");
	
		st.executeUpdate("insert into emp values (002,'vayyala2',1000,50)");
		
		Savepoint sp1 = con.setSavepoint(); 
		
		st.executeUpdate("insert into emp values (003,'vayyala3',3000,50)");
		
		st.executeUpdate("insert into emp values (004,'vayyala4',1000,50)");
		
		con.rollback(sp1);
		
		st.executeUpdate("insert into emp values (005,'vayyala5',1000,50)");
	
		con.commit();
		
		System.out.println("all are done ");
	
	
     }//main 
	
}//class
