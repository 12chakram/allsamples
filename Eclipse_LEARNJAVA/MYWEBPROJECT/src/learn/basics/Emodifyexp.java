package learn.basics;

public class Emodifyexp {
	
	
	public static void main(String[] args) {
		
		int a=10;
		int b=Integer.parseInt(args[0]);
		int c= a*b;
		
		if(c==0)
			throw new ArithmeticException("multiply by zer");
		else
			System.out.println(c);
		
		
		
		
		
	}
}
