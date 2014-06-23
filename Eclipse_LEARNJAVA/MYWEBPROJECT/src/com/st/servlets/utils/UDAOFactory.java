package com.st.servlets.utils;

import java.util.Properties;

import com.st.jdbc.daoI.UserDAOI;
import com.st.jdbc.daoI.ServletEmployeeDAOI;

public class UDAOFactory {
	private  static ServletEmployeeDAOI sedo;
	 private static Properties daoClasses;

	 static {
		 
		 try{
			daoClasses = new Properties();
			daoClasses.load(DAOFactory.class.getClassLoader().getResourceAsStream("ApplicationDAOS-Properties"));
			
}//try
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 
			 throw new RuntimeException ("problem loadding the DAOFactory");
			 
		}//catch
		 
};//static
	
public static EmployeeDAOI

getEmployeeDAO(){
	if (edao == null){
		try{
			Class c = Class.forName(daoClasses.getProperty("EmployeeDAOI"));
			edao = (EmployeeDAOI)c.newInstance();
			
		}
		catch (Exception e)
		{
			return null;
			
		}
		return edao;
	}
	return edao;
	
}
}


	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	

}
