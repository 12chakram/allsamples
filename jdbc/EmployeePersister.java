// EmployeePersister.java
// this class implements the code
//to persist the employee details into dbserver

package com.st.jdbc.dao;
import java.sql.*;

import com.st.jdbc.vo.Employee;

public class EmployeePersister implements EmployeeDAOI {
	Connection con;
	private Connection getConnection ()throws Exception{
		if(con == null){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		}
		return con;
	}
	
	
	
	
/
	public void saveEmployee (int eno,String en, double salary,int dno) throws Exception{
		
	Connection con =getConnection();

 System.out.println("connected to DB ");		
 
String sql = "INSERT INTO EMP VALUES(" + eno +",'" + en + "'," + salary + "," + dno +")";
System.out.println(sql);
 Statement st =con.createStatement();
 st.executeUpdate(sql);	
 System.out.println("save query done");
  
 con.close();
 System.out.println("close");
 

}//save 
*/
/*
public void getEmployee() throws Exception{
	
	Connection con = getConnection();		
 System.out.println("connected to DB ");		
 
String sql = "select eno,en,salary,dno from  emp";

 Statement st =con.createStatement();
 ResultSet rs =st.executeQuery(sql);	
 System.out.println("query done");
  while (rs.next()){
	  int no = rs.getInt(1);
	  String na = rs.getString(2);
	  double sal = rs.getDouble(3);
	  int dnum = rs.getInt(4);
	  System.out.println(+no+"\t"+na+"\t"+sal+"\t"+dnum);

	  
	 
	  

	  
	  
	  
  }//while
 
 
 
}
*/




public void save(int eno, String en, double salary, int dno) throws Exception {

	Connection con =getConnection();

	 System.out.println("connected to DB ");		
	 
	String sql = "INSERT INTO EMP VALUES(" + eno +",'" + en + "'," + salary + "," + dno +")";
	System.out.println(sql);
	 Statement st =con.createStatement();
	 st.executeUpdate(sql);	
	 System.out.println("save query done");
}





public void getEmployee(Employee e)  throws Exception{
		
		Connection con = getConnection();		
	 System.out.println("connected to DB ");		
	 
	String sql = "select eno,en,salary,dno from  emp";

	 Statement st =con.createStatement();
	 ResultSet rs =st.executeQuery(sql);	
	 System.out.println("query done");
	  while (rs.next()){
		  int no = rs.getInt(1);
		  String na = rs.getString(2);
		  double sal = rs.getDouble(3);
		  int dnum = rs.getInt(4);
		  System.out.println(+no+"\t"+na+"\t"+sal+"\t"+dnum);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} 
  
  
  
}//class






	
}





















