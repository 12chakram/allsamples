package com.st.jdbc.dao;

import com.st.jdbc.bo.GetOracleConnectionEx1;
import com.st.jdbc.daoI.EmployeeDAOI;
import com.st.jdbc.utils.GettingOracleConnection;

import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;


public class EmployeeDAO {
	
//	Connection con;
//	private Connection getConnection ()throws Exception{
//
//		if(con == null){
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
//		}
//		return con;
//}	

	public void updateEmp(int eno) throws Exception{
  
		//Connection con =getConnection();

		Connection con = GettingOracleConnection.getConnection();

		
		System.out.println("connected to DB ");

		 String sql = "update  emp set ename='kumar ' where eno="+eno;
		 System.out.println(sql);
		 Statement st = con.createStatement();
		 st.executeUpdate(sql);
		 System.out.println("updated"+sql);
		 
}//update
	
//	public void remove(int eno) throws Exception{
//		
//	
//		Connection con =getConnection();
//
//		 System.out.println("connected to DB ");
//		 
//		 String sql = "delete emp where eno="+eno;
//		 Statement st = con.createStatement();
//		 st.executeUpdate(sql);
//		 
//		System.out.println("query ok  and record is deleted ");
//		
//     // con.close();
//	
//	}//remove
//
//	public void saveEmployee (int eno,String en, double salary,int dno) throws Exception{
//		
//		Connection con =getConnection();
//
//	 System.out.println("connected to DB ");		
//	 
//	String sql = "INSERT INTO EMP VALUES(" + eno +",'" + en + "'," + salary + "," + dno +")";
//	System.out.println(sql);
//	 Statement st =con.createStatement();
//	 st.executeUpdate(sql);	
//	 System.out.println("save query done");
//	  
//	}//save
//	
//	public void getEmp() throws Exception {
//		
//		Connection con =getConnection();
//
//		 System.out.println("connected to DB ");
//
//		 String sql = "select * from emp";
//		 Statement st =con.createStatement();
//		 ResultSet rs = st.executeQuery(sql);
//	
//		while (rs.next()){
//			
//			int eno = rs.getInt(1);
//			String en = rs.getString(2);
//			double sal=rs.getDouble(3);
//			int dno = rs.getInt(4);
//		
//		
//		System.out.println(+eno+ "\t" +en+ "\t"+sal+ "\t" +dno);
//		
//		}//while
//		
//		
//	}//getEmp
//	public void save() throws Exception {
//
//
//		Connection con =getConnection();
//
//		 System.out.println("connected to DB ");
//
//		 String sql = "insert into emp values (?,?,?,?)";
//		 PreparedStatement ps =con.prepareStatement(sql);
//		 
//		 ps.setInt(1, 444);
//		 ps.setString(2, "santoshsir");
//		 ps.setDouble(3, 999);
//		 ps.setInt(4, 40);
//		 
//		 ps.executeUpdate();
//		 
//		 }//save 
//	
	
	public static void main(String[] args) throws Exception {

		EmployeeDAO edo =new EmployeeDAO();
		
		edo.updateEmp(113);
		//edo.getEmp();
		
		
		
	}
}

		 
			 
			 
			 
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

