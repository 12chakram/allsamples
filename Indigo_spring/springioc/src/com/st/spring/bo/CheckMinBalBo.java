//CheckMinBalBo.java
package com.st.spring.bo;

import com.st.spring.intf.AccountsDaoI;

public class CheckMinBalBo {
	
	AccountsDaoI adao;

	
	public CheckMinBalBo(AccountsDaoI a){

		adao=a;
	}// constractor 

	
	public boolean checkBal(int acno,double amt){
		
			System.out.println("in checkbal");
			return true;

}// checkBal
	
}


	