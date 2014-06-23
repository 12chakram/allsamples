// this will work in MYSQL only 


package com.st.jdbc.bo;

import java.sql.*;

public class SavaComplaintsOnMysqlEx1 {

	public static void main(String s[]) throws Exception{

		//get connection
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection 	con = DriverManager.getConnection("jdbc:mysql:///mylearn",
			        "root", "ideserveit");

		
		
		Statement st =con.createStatement();
		String sql ="insert into complaints(cname,description)values('user9','power problem')";
		
		int i =st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		
		if (i==1 ||i==Statement.SUCCESS_NO_INFO){
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			System.out.println("complaints id:"+rs.getInt(1));
			
			
}//if
		
}//main	
	
}//class
