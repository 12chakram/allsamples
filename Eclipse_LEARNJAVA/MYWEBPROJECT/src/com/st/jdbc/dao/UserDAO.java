package com.st.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.st.jdbc.daoI.UserDAOI;
import com.st.jdbc.vo.User;

public class UserDAO implements UserDAOI {
	
	
	Connection con;
	private Connection getConnection ()throws Exception{
		if(con == null){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		}
		return con;
}	
	
	
	public User findUser(String uname, String pass) {
		
		
		try{
		Connection con = getConnection();
		
		System.out.println("connected to db");
		
		String sql = "select uname,pass from  userdetails where uname ='" +uname+ "' and pass='"+pass+"'";
		
		System.out.println(sql);
		
		Statement st = con.createStatement();  
		
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			 String un = rs.getString(1);
			 String pa = rs.getString(2);
			
			System.out.println(un+ "\t" +pa );
			
		}
		
		
		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch
		
	return null;
	}//findUser

	

	
	public boolean checkPass(String uname, String pass) {
		
		try{
			Connection con = getConnection();
			
			System.out.println("connected to db");
			
			String sql = " select  uname ,pass from userdetails   where uname='"+uname+"'and pass='"+pass+"'";
			
			System.out.println(sql);
			
			
			
			Statement st = con.createStatement();  
			
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
			String un = rs.getString(1);
			String pa = rs.getString(2);
			
			if(!(pa==pass)) {
				return false;
				
			}
			else{
				
			}
			}//while

			
			
			
			
			
		}//try
		
		catch(Exception e ){
			
			
		}//catch
		return true;
		
		
		
		
		
		
		
		
		
		
		
	}//checkPass

	public void changePass(String uname,  String newpass) {
		

		try{
			Connection con = getConnection();
			
			System.out.println("connected to db");
			
			String sql = " update  userdetails set pass ='" +newpass+ "'  where uname='"+uname+"'";
			
			System.out.println(sql);
			
			Statement st = con.createStatement();  
			
			int rs = st.executeUpdate(sql);
			
			System.out.println("passwordchange done:"+rs);
			
		}//try
		
		catch(Exception e ){
			
			
		}//catch
		
			
		
		
			
		
		
		
		
		
		
		
	}//changePass

}
