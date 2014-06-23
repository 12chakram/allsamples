// only work in mySQl database server
//useing Preparedstatement 
package com.st.jdbc.bo;
import java.sql.*;

public class SavaComplaintsOnMysqlEx2 {

	public static void main(String args[]) throws Exception{
		
		//get connection to MYsql
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection 	con = DriverManager.getConnection("jdbc:mysql:///mylearn",
			        "root", "ideserveit");
			
		System.out.println("YES connect to Mysql");	
		
		
		String sql ="insert into complaints(cname,description)values('user9','i dont like ')";
		
		PreparedStatement ps = con.prepareStatement(sql,new int[]{1});
				
		int i =ps.executeUpdate();
		
		if (i==1 || i==Statement.SUCCESS_NO_INFO){
			
			ResultSet rs =ps.getGeneratedKeys();
			rs.next();
			System.out.println("complaint id:"+rs.getInt(1));
			
			
			
			
			
		}//if
		
}//main
	
}//class
