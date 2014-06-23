package com.st.jdbc.bo;
import java.sql.*;
public class IncrementSalaryEx1 {

	public static void main (String s [])throws Exception  {

	int inputDno = Integer.parseInt(s[0]);
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl", "learn", "learn");
 // now we get connection to database 
    Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	 String sql = "select eno ,en,salary,dno from emp";
	DatabaseMetaData dbmd = con.getMetaData();
	 
	// note :- using "select * from......" with oracle will not create a Updatable Resultset , we want to 
	 // specify the individual column name 
	 
	 ResultSet rs = st.executeQuery(sql);
	 
		 while (rs.next()){
			 int eno = rs.getInt(1);
			 String en = rs.getString(2);
			 double salary = rs.getDouble(3);
			 int dno = rs.getInt(4);
System.out.println(eno+"\t"+en+"\t"+salary+"\t"+dno);
	 
 
	 
	
	// move to row to update 
	 if (dno==inputDno){ // for type use ==
		 
// we want to update this (current row)
		 rs.updateDouble(3,salary*0.5); // change the column values 
		rs.updateRow(); // change the row 

		//dbmd.ownUpdatesAreVisible(4); //ownUpdatesAreVisible(int rs TYPE)
		
		dbmd.updatesAreDetected(dno);
		
	 }// if
	 
	 
	 //System.out.println(rs.rowUpdated()); // dought

	 
	 } // while 	
		 System.out.println(rs.rowUpdated());

		 
			con.close();

	 }// main 
}//class