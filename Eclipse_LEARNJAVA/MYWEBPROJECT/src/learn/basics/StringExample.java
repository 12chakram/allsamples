package learn.basics;

public class StringExample {
	
	public static void main(String[] argv) {
	
		String str = new String("my name is Thanassis");
	    
		System.out.println("The string contains:" + str );
	    
		System.out.println("its length is :" + str.length() + " chars");
	    
		System.out.println("uppercase :" + str.toUpperCase());
	    
		System.out.println("lowercase :" + str.toLowerCase());
	    
		System.out.print("-is- starts at :" + str.indexOf("is") );
	      if (str.equals("my name is Thanasis")) 
	         System.out.println("Yes");
	 
	         
	         else System.out.println("No");

	}// main


}//class
