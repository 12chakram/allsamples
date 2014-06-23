package com.st.servlets.dao;

import com.st.servlets.daoI.UserDAOI;

public class UserDAODummy implements UserDAOI {

	public boolean findUser(String s1, String s2) {
		
		return ("user1".equals(s1)||"user2".equals(s2));
	}

}


//if("user1".equals(s1)||"user2".equals(s2)){
//	
//	return true;
//		}else{
//			return false;
//		}