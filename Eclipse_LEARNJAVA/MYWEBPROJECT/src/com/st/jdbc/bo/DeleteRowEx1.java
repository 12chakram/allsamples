package com.st.jdbc.bo;
import java.sql.*;

public class DeleteRowEx1 {
	public static void main(String s[]) throws Exception
{
		int inputeno=Integer.parseInt(s[0]);
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl", "learn", "learn");

//now we get connection to database
Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
String sql ="select eno,en,salary,dno from emp";
ResultSet rs=st.executeQuery(sql);
//ResultSet rs2=rs;

while (rs.next()){
	int eno = rs.getInt(1);
	 String en = rs.getString(2);
	 double salary = rs.getDouble(3);
	 int dno = rs.getInt(4);
System.out.println(eno+"\t"+en+"\t"+salary+"\t"+dno);

// move to the row to delete 
if(eno==inputeno){
rs.deleteRow();

}// if


//DatabaseMetaData dbmd = con.getMetaData();
//dbmd.deletesAreDetected(eno);

}// while		


System.out.println(rs.rowDeleted());


con.close();
			
		}//main
}//class