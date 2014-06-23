package learn.basics;

public class Etest {


	
	public static void main(String args[]  ){
		
		String s = "";
		
	System.out.println(args[0]);
	
	System.out.println(args[1]);
	
	System.out.println(args[2]);
		
		try{
		 s =	args[3];
		 int i = Integer.parseInt(s);
		}
		catch(Exception ex)
		{
			s = "KU";
		}
		
		System.out.println(s);
	
	}
	
	
	
}
