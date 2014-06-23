//LoginDaoImpI
package com.st.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.st.struts.vo.LoginVo;

public class LoginDaoImpI implements LoginDaoIntf {

	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
		
@Override
	public boolean validateUser(LoginVo lv) {
		
	String query="select *  from userdetail where uname=? and pass=? ";
	
      boolean status= false;

try{
	
	con=OracleDAOFactory.createConnection();
	ps=con.prepareStatement(query);
	
	ps.setString(1, lv.getUname());
	ps.setString(2,lv.getPass());
	
	rs=ps.executeQuery();
	while(rs.next()){
		status=true;
	}
	
}//try
catch(Exception e){e.printStackTrace();}
 return status;
 
}//validateUser	
	
}//class
