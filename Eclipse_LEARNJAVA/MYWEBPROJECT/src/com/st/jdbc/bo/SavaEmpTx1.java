package com.st.jdbc.bo;
import java.sql.*;
public class SavaEmpTx1
{
public static void main (String s[])throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
	System.out.println("connected");



try{



    con.setAutoCommit(false);

    PreparedStatement ps = con.prepareStatement( "UPDATE emp SET dno=? WHERE en=?");

    ps.setInt(1,666);

    ps.setString(2,"raaga");

    ps.executeUpdate();

    PreparedStatement ps1 = con.prepareStatement( "UPDATE epersonal_details SET eno=? WHERE lname=?");

    ps1.setInt(5, 333);

    ps1.setString(3,"ku");

    ps1.executeUpdate();

    con.commit();

    con.setAutoCommit(true);

    }
catch(SQLException e){
	con.rollback();
	}

   


//finally{

             //if(ps!= null){
            	// ps.close();}

             //if(ps1 != null){
            	// ps1.close();
             //}
       if(con != null){
    	   
    	   
    	   
    	   con.close();
 
    	   System.out.println("clooooooooooooooose");
       }

    

}//main

}//class