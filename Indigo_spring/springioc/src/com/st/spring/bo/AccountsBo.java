//AccountsBo.java
package com.st.spring.bo;

import com.st.spring.intf.AccountsDaoI; 

public class AccountsBo {

	
	AccountsDaoI adao;
	CheckMinBalBo cmb;
	
	public AccountsBo(AccountsDaoI a){
		this.adao=a;
	
	}// constoctor
	
	
	public void setCheckMinBal(CheckMinBalBo c){
	cmb=c;
	
	}// checkminBal
	
	public boolean withdraw(int accno,double amt)
	{
	System.out.println("in withdraw");
	double  bal= adao.getBalance(accno);
	if(bal>=amt){
		adao.setBalance(accno,bal-amt);
		return true;
	}
	
	if (cmb.checkBal(accno,bal)){
		adao.setBalance(accno,bal);
		return true;
	}//if
          
	       return false;
	
	}// withdraw
	
	public boolean deposit(int accno1,double amt1)
	{
		this.accno1 = accno1+qmy
		System.out.println("in deposit");
		double bal1 =adao.getBalance(accno1);
		bal1+=amt1;
		adao.setBalance(accno1,bal1);
		return false;
		
}// deposit
	
}//main 





	
