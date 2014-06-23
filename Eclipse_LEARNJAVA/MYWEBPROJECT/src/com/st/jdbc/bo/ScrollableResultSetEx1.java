package com.st.jdbc.bo;
import java.sql.*;

public class ScrollableResultSetEx1 {

public static void main(String []s) throws Exception {

	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");

Statement st =con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;

ResultSet rs = st.executeQuery("select eno,en,salary,dno from emp");
 // now the pointer is at before first
// we can use ResultSet.getRow()method to get the current row number

while (rs.next()){
int eno = rs.getInt(1);
String name = rs.getString(2);
double sal = rs.getDouble(3);
int dno = rs.getInt(4);
 
System.out.println(rs.getRow()+"\t"+eno+"\t"+name+"\t"+sal+"\t"+dno);
} // while

// now the pointer is at afterlast
// to move to first row 

rs.first();
System.out.println("---------------");
System.out.println("first row :"+rs.getInt(1));

// to move to 3rd row

rs.absolute(3);
System.out.println("3rd row :"+rs.getInt(1));

// using relative 

rs.relative(-2); // go to last 
System.out.println("1 st  :"+rs.getInt(1));









}//main

} // class 
