
package com.st.jdbc.bo;
import java.sql.*;

public class JdbcMysqlEx3 {

	public static void main(String args[])throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection 	con = DriverManager.getConnection("jdbc:mysql:///mylearn",
		        "root", "ideserveit");
		
	System.out.println("YES connect to Mysql");	
		
Statement st = con.createStatement();		
ResultSet rs =st.executeQuery("select * from complaints");

System.out.println("statement execute on Mysql");

while(rs.next()){
	int cid =rs.getInt(1);
	String cname =rs.getString(2);
	String description = rs.getString(3);
	
	System.out.println(cid+ "\t"+ cname+"\t"+description);
}
		
		
		
		
		
		
		
		
		
	}//main
	   
	
	
	
	
	
	
	
	
	
	
	
}
