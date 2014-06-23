package com.st.jdbc.bo;
import java.sql.*;

public class Jdbc1 {

	public static void main (String args[]) {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      con = DriverManager.getConnection("jdbc:mysql:///mylearn",
		        "root", "ideserveit");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from emp");
		while(rs.next())
		{
		System.out.println("eno " + rs.getInt(1));
		System.out.println("en" + rs.getString(2));
		System.out.println("salary " + rs.getInt(3));
		System.out.println("dno " + rs.getString(4));
		}
		} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Exception: " + e.getMessage());
		} finally {
		try {
		if(con != null)
		con.close();
		} catch(SQLException e) {}
		}
		}
		}

