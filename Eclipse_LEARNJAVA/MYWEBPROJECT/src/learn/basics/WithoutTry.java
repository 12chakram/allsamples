// WithoutTry.java

package learn.basics;

public class WithoutTry {
	
	public static void main(String args[]) {
		try{
			

		int a=10;
		int b=0;
		int c=a/b;
		
		System.out.println("the C value is:"+c);
		
		}
		catch(Exception e ){
		System.out.println(e);
		}
			
		int x=11;
		int y=2;
		int z= x+y;
			
		System.out.println("the Z value is:"+z);
	}
	
}
