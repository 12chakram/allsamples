package SantoshSpring;
import SantoshSpring.AccountsDaoI;
public class CheckMinBalBo {
	public  CheckMinBalBo(AccountsDaoI a){

		adao=a;
	}// constractor 
	
public boolean checkBal(int acno,double amt){
// to do use accountsdao for reading the nessary details 
	//of the accounts and check for min bal availability
	System.out.println("in checkbal");
	return true;
	
}//checkBal

AccountsDaoI adao;
}// class

