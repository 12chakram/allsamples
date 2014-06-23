import java.util.*;

class Test
{
	public static void main(String[] args)
	{
		Test aTest = new Test();
		aTest.test();
	}
	
	private void test()
	{
		Person person = new Person();
		person.addAddress("123 Surrey Avenue");
		System.out.println(person.getAddress());
		Account acc = new Account();
		
		try{
			acc.deposit(-33);
		} 
		catch(IllegalAmountException e){
			System.out.println(e.getMessage());
		}
		
		person.addAccount(acc);
		Account acc2 = new Account();
	    
	    try{
	    	acc2.deposit(200);
	    } 
	    catch(IllegalAmountException e){
	    	System.out.println(e.getMessage());
	    }
		
		person.addAccount(acc2);
		
		ArrayList it = person.returnAccounts();
		Iterator t = it.iterator();
		
		Account anAccount;
		
		float balance;
		
		while(t.hasNext()){
			anAccount = (Account) t.next();
			balance = anAccount.getBalance();
		System.out.println("Balance = " + balance);	
		}
	}
}