/***Takes the empno as command line  arg 
name,sal,and deptno hardcode(any values)
Insert the emp record into the database
using ResultSet.***/


package com.st.jdbc.bo;
import java.sql.*;
public class InsertEmpEx1 
{
public static void main(String s[])throws Exception{
	int inputempno = Integer.parseInt(s[0]);
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");	
	
  System.out.println("connect to database");
	
Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
String sql = "select eno,en,salary,dno from emp";
ResultSet rs = st.executeQuery(sql);

System.out.println("query execute on  database");
	
while (rs.next())
 {
	int eno = rs.getInt(1);
	 String en = rs.getString(2);
	 double salary = rs.getDouble(3);
	 int dno = rs.getInt(4);
System.out.println(eno+"\t"+en+"\t"+salary+"\t"+dno);
	

// /***
// now we want to move to the insert row 

if (eno==inputempno)
{
	rs.moveToInsertRow();
	
	//System.out.println("new row for insert");
	
	// step 2 
	rs.updateInt(1,eno);
	rs.updateString(2, "kumar");
	rs.updateDouble(3, 999);
	rs.updateInt(4, 90);
	
	// step3 
	rs.insertRow();
	
	//System.out.println("row inserted");
	
	}//if 

//System.out.println("row inserted");



 } // while 	
con.close();	
	
System.out.println("database connection close");
	
	 } // main



}//class