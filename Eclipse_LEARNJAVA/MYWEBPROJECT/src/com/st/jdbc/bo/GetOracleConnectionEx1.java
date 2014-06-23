package com.st.jdbc.bo;
import java.sql.*;
public class GetOracleConnectionEx1 {

		public static void main(String[] args) throws Exception 
    {

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","scott","tiger");
    
    System.out.println("connected");
    
    Statement st = conn.createStatement();
    
    String sql = "select empno,ename,job,sal,deptno from emp";
    
    ResultSet rs =st.executeQuery(sql);
    
    
  System.out.println("query correct & done");
  System.out.println("---------------------------------------------");   
    while (rs.next()){
    int e = rs.getInt(1);
    String ename = rs.getString(2);
    String j = rs.getString(3);
    double sal = rs.getDouble(4);
    int d = rs.getInt(5);
    
    
    System.out.println(e+"\t"+ename+"\t"+j+"\t"+sal+"\t"+d);
    
    
    }// while
    
    conn.close();
    System.out.println("closed");
    
 }// main
		

}//class