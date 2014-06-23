package com.st.jdbc.daoI;

import com.st.jdbc.vo.User;

public interface UserDAOI {
	
	User findUser(String uname, String pass);
	
	boolean checkPass(String uname , String pass);
	 void changePass(String uname,String newpass);
	 
}
