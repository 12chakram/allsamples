//SaveEmployeeTx1
package com.st.jdbc.bo;
import java.sql.*;
public class SaveEmployeeTx1 {
public static void main(String s[])throws Exception{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
	System.out.println("connected");
	
	try
	{
		Statement st =con.createStatement();
		con.setAutoCommit(false);
		
		int i =st.executeUpdate("inser into emp values("+s[0]+",'vayyala',1000,10)");
		
		//int j =st.executeUpdate("inser into epersonal_details values(1001,'kumar','v',"+s[0]+")");
		
		if(i==1 || i==Statement.SUCCESS_NO_INFO)
			{
			con.commit();
			System.out.println("employee save successfully");
				
			}//if
			else{
				con.rollback();
				
				System.out.println("problem in save employee");
				
				
			}//else
		
	}//try
	catch (Exception e){
		con.rollback();
		System.out.println("Error:"+e.getMessage());
		
	}//catch

	
    }//main
}//call

