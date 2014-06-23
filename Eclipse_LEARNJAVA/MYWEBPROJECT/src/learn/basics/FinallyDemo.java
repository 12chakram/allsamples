package learn.basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FinallyDemo {
	

	
	public static void main(String args[]) throws Exception{
		
		FileInputStream fis= null;   // global refernce variable 
		
		
		try
		{
 fis=new FileInputStream("abc.text");
			
			int a=10;
			int b=0;
			int c=a+b;
			
		}
		
		catch(FileNotFoundException e){
			
			System.out.println(e);
		}
		
		finally
		{
			
			System.out.println("we are in finally");
			
			System.out.println("\t");
	
			fis.close();
		}
		
		
		System.out.println("out side of finally");
		
	}
	
}
	



