package SantoshSpring;
import SantoshSpring.AccountsDaoI;

public class AccountsBo {

	
	AccountsDaoI adao;
	CheckMinBalBo cmb;
	
	public AccountsBo(AccountsDaoI a){
		adao=a;
	}
	public void checkMinBal(CheckMinBalBo c){
	cmb=c;
	}
	public boolean withdraw(int acno,double amt)
	{
	System.out.println("in withdraw");
	double  bal= adao.getBalance(acno);
	bal=amt;
	if (cmb.checkBal(acno,bal)){
		adao.setBalance(acno,bal);
		return true;
	}//if
	
	return false;
	}

	public boolean deposit (int acno,double amt){
		System.out.println("in deposit");
		double bal =adao.getBalance(acno);
		bal+=amt;
		adao.setBalance(acno,bal);
		return true;
		
}// deposit
	
	}//main 
		
		
		
	
	
	




