//ConnectionPoolEx1.jav 
package com.st.jdbc.bo;
import java.sql.*;
import javax.sql.*;
import oracle.jdbc.pool.*;

public class ConnectionPoolEx1 {

	public static void main(String s[])throws Exception{

		OracleConnectionPoolDataSource ds =new OracleConnectionPoolDataSource();  
		ds.setURL("jdbc:oracle:thin:@localhost:1521:myorcl");
		ds.setUser("learn");
		ds.setPassword("learn");
		
		PooledConnection pc = ds.getPooledConnection();
		Connection con = pc.getConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from emp");
		
		while (rs.next()){
			System.out.println(rs.getInt(1));
			
		
		}
		System.out.println("---------------------");
		con.close();
		pc.close();
		
}//main
	
 }//class
