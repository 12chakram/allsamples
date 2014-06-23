//RetriveDispaly.java
package com.st.struts.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.st.struts.vo.Edetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RetriveDispaly extends Action {
 
	 public ActionForward execute
	 (ActionMapping am, ActionForm af, HttpServletRequest req, HttpServletResponse res) 
	 throws Exception{
		 
		 // here we not recommended to write any data base connection logic 
		 // but this program to test the Database connection 
		  
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myorcl","learn","learn");
		  
		 Statement st=con.createStatement();
		 
		 ResultSet rs=st.executeQuery("select * from emp");
		 
		 // instantiate Arrary list
		 
		 ArrayList al = new ArrayList();
		 
		while(rs.next()){
			 
			 al.add (new Edetails (rs.getInt(1),rs.getString(2),rs.getDouble(3)));
		 }//while
		 
		 	 //set  the attribute
		 
		 req.setAttribute("edetails", al);
		 
		 return am.findForward("done");
		  
		 }//execute
		 
}//class
