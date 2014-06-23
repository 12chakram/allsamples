//AccountsDaoTestImp.java
package com.st.spring.imp;

import com.st.spring.intf.AccountsDaoI;

public class AccountsDaoTestImp implements AccountsDaoI {

	@Override
	public void setBalance(int accno, double amt) {
		
		System.out.println("InSetBalance:DAOTestImp");
		

	}//setbalance
	
	
	
	@Override
	public double getBalance(int accno) {
		System.out.println("ingetBalance:DaoTestIpm");
		return 1000;

	
	}//getbalance

}//class
