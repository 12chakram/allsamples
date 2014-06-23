package com.st.jdbc.bo;
import java.sql.*;

public class BatchUpdateEx2 {
	public static void main(String s []) throws Exception
	 {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		 
		 System.out.println("connected");
		  
		 String sql = "insert into emp values(?,?,?,?)";
		 PreparedStatement ps = con.prepareStatement(sql);
		 
ps.setInt(1, 901);		 
ps.setString(2, "kumar");
ps.setDouble(3, 999);
ps.setInt(4, 90);
ps.addBatch();

System.out.println("first batch done ");

ps.setInt(1, 801);		 
ps.setString(2, "suresh");
ps.setDouble(3, 888);
ps.setInt(4, 80);
ps.addBatch();

System.out.println("second  batch done ");
ps.setInt(1, 701);		 
ps.setString(2, "deva");
ps.setDouble(3, 777);
ps.setInt(4, 70);
ps.addBatch();


System.out.println("3rd batch done ");



try {
	 int []counts=ps.executeBatch();
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


	 System.out.println("close done ");

}


	
	
	System.out.println("close done ");
	

	 }//main
}//class
