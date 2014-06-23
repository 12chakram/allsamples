package learn.basics;

public class Milticatch {
	
	public static void main(String args[])
	{
	try
	{
	int a =10;
	int b=Integer.parseInt(args[0]);
	int c = a/b; // exception possible statement 
	System.out.println("the c value is:"+c);
	int d[]={10};
	d[5]=50; 
	System.out.println("d[5] value is:"+d[5]);
	}
//-------------------------------------- 
	catch (ArithmeticException ex) // specific
	{ 
		System.out.println(ex);
	}

	catch (ArrayIndexOutOfBoundsException ex)  // specific
	{
		
	System.out.println(ex);
	
	}
	
	//-------------------------------------- 
	catch(Exception ex) // generic exception catch block 
	{
		System.out.println(ex);
	}
	
	
	


// note : generic exception catch block is the last catch block, other wise comilation error 


	int x=10;
	int y= 20;
	int z= x+y;
	System.out.println("the z value is:"+z);

}
	
}
