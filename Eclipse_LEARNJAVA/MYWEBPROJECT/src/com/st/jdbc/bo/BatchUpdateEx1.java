package com.st.jdbc.bo;
import java.sql.*;
public class BatchUpdateEx1 
{
 public static void main(String s []) throws Exception
 {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
	 
	 System.out.println("connected");
	 
	 Statement st = con.createStatement();
	 String sql1  ="insert into emp values(999,'newemp',1000,10)"; 
	 st.addBatch(sql1);

	 System.out.println("row inserted:"+sql1);
	 
	 
	String sql2 = "update emp set en='ku' where eno=999";
	 st.addBatch(sql2);
	 
	 System.out.println("row  updated:"+sql2);
	 
	 String sql3 = "delete emp where eno=109";
	 st.addBatch(sql3);
	 
	 System.out.println("row deleted:"+sql3);
	 
	 st.executeBatch();
	 System.out.println("DONE");
	 
	 try {
		 int []counts=st.executeBatch();
		 System.out.println("batch executed sucessfully");
		 
		 for (int i=0;i<counts.length;i++){
			 System.out.println("update count of statement"+(i+1)+counts[i]);	 
			 
			}//for
	 
	 }//try
	 catch (BatchUpdateException e){
		 System.out.println("batch failed to execute");
		 int[]counts=e.getUpdateCounts();
		 if (counts==null){
			 System.out.println("batch terminated between & the information about update counts and " +
			 		"which statement failed is unkown: ");
		 }//if
		 else {
			 if (counts.length==3){
				 System.out.println("batch executed till end ");
				 for(int i=0;i<counts.length;i++){
					 
				
				 if (counts[i]==Statement.EXECUTE_FAILED){
					 System.out.println("statement"+(i+1)+"failed to execute");
				 }
				 
				 else {
					 System.out.println("update counts of statement"+(i+1)+"\t"+counts[i]);
					 
				 }//for
				 }//else
			 }//else counts==null
		 }//catch
		 con.close();
	 }
 }//main
}//class
