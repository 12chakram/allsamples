//DAOFactory.java
package com.st.struts.dao;

public abstract  class DAOFactory {

	
	public static final int Oracle=1;
	
	/*
	 * public static final int Db2=2;
	 * 
	 *  public static final int Sybase=3;
	 * 
	 *  public static final int Mysql=4;
	 */
	
	public static DAOFactory getDaoFactory(int whichFactory){
		
		//recall switch controlflow statement 
		switch(whichFactory){
		case Oracle: return new OracleDAOFactory();
		
		/*
		 * case Db2: return new Db2DAOFactory();
		 * 
		 * case Sybase: return new SybaseDAOFactory();
		 * 
		 * case Mysql: return new MysqlDAOFactory();
		 */
		    default: return null;
		
		
}//switch
		
	}//getDaoFactory
	
	public abstract  LoginDaoIntf getLoginDao();
	
}//class
