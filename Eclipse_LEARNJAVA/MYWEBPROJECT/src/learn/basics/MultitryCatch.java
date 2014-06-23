package learn.basics;

public class MultitryCatch {
	
	
	public static void main(String args[]) throws Throwable {
		
		try{
			int a=10;
			int b=0;
			int c=a+b;
			
  System.out.println("the c value is:"+c);
  
		}
  
  catch(Exception e ){
	  
	  throw new RuntimeException("can not divied a value buy zero"+e.getMessage());
	  
  }
	  disp(); //caller/ call another method 
	  
	}
  
 static  void disp(){
	  
	  try{
		  int d[]={10,20};
		  
		  System.out.println("d[5]value is:"+d[5]);
		  
	  }
	  catch(Exception e){
		  
		  System.out.println(e);
		  
	  }
	  
  }
  		}

		
	
	
	
	
	
	
	
	
	


