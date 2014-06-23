//OracleDAOFactory

package com.st.struts.dao;
import java.sql.*;

public class OracleDAOFactory extends DAOFactory {

	public static final String driver="oracle.jdbc.driver.OracleDriver";
	
	public static final String url="jdbc:oracle:thin:@localhost:1521:myorcl";
	
	public static Connection createConnection(){
		Connection con= null;
		try{
			
			Class.forName(driver);
			 
			con=DriverManager.getConnection(url,"learn","learn");
			
		}//try
		catch(Exception e ){e.printStackTrace();}
		
		return con;
		
	}//createConnection

	@Override
	public LoginDaoIntf getLoginDao() {
		
		return new LoginDaoImpI();
	}
	
	
}//class 
