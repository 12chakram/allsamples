package SantoshSpring;
import SantoshSpring.AccountsDaoI;
public class AccountsDaoTestImp  implements AccountsDaoI{

	@Override
	public void setBalance(int accno, double amt) {
		// after this can be implemented with jdba or 
		//any other data access API to persist the balance
		System.out.println("InSetBalance:DAOTestImp");
	
	}//setBalance 

	
	public double getBalance(int accno) {
	
		// todo :access the datastore get the balance of 
		//given accno&return
	   // fordemo
		System.out.println("ingetBalance:DaoTestIpm");
		return 1000;
	}//getbalance

}//class
