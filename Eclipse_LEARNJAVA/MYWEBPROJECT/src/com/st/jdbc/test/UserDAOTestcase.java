package com.st.jdbc.test;
import com.st.jdbc.dao.UserDAO;
public class UserDAOTestcase {

	public static void main(String s[]){
		
	UserDAO ud = new UserDAO();
		
		ud.findUser("kumar", "raaga");
		ud.changePass("kumar", "indira");
		ud.checkPass("kumar", "indira");
		
		//System.out.println("user valid");
		
		
		
		
		
		
		
		
		
		
		
		
		
	}//main
	
	
	
	
	
	
	
	
	
}//class
