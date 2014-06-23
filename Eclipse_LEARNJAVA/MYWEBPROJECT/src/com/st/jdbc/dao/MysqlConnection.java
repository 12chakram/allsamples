package com.st.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
	
	 static Connection con = null;
	public static void main(String[] args) throws SQLException  {
		
	
	try{
	
	  Class.forName("com.mysql.jdbc.Driver");
	  String url = "jdbc:mysql://localhost:3306/mylearn?user=root&password=ideserveit";
	   con = DriverManager.getConnection(url); 
	  System.out.println("yes connectted");
	}
	catch(Exception e){
		e.printStackTrace();
	}

	Statement st =con.createStatement();
//	String sql ="insert into complaints(cname,description)values('user9','power problem')";
//	
//	int i =st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
//	
//	if (i==1 ||i==Statement.SUCCESS_NO_INFO){
//		ResultSet rs=st.getGeneratedKeys();
//		rs.next();
//		System.out.println("complaints id:"+rs.getInt(1));

	
	String sql = "select * from emp";
	
	ResultSet rs = st.executeQuery(sql);
	
	while (rs.next()){
		
		int eno =rs.getInt(1);
		String name =rs.getString (2);
		Double sal= rs.getDouble (3);
		
		
		System.out.println(eno);
		
		System.out.println(name);
		
	
	}// while 
		
	
}//main	

}//class
