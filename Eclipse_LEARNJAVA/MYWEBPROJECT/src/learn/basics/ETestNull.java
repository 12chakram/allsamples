package learn.basics;

import java.io.FileNotFoundException;

public class ETestNull {

	
	void show() throws KumaarFileNotFoundException,  Arthemtic, NullPointerExe{
		
		System.out.println(" iam from show method");
		
		
	}
	
	public static void main (String args[])throws DataAccessException{
		
		ETestNull etn =  new ETestNull();
		
		try{
			etn.show();
			
			
			
		}catch(Throwable  f){
			
			
throw  new RuntimeExceptiom ("palease check the file ");
		
			
		}
		
		
	}
	
	
	
	
}
