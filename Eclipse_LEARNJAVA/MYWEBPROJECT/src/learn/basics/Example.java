package learn.basics;

	
//	To access a static member of a class from a different class you need to precede
//	the reference to it with the class name in which it resides.
	
//	The same happens with static methods.
	
   public  class Circle {
	
      public static final float PI = 3.14f;
	
     public static float getPI() { return PI; }
	
   
	
   public class Example {
	
       public static void main (Stringargs[]) {
	
           System.out.println(“PI” + Circle.PI);
	
             System.out.println(“PI” + Circle.getPI());
	
      }
	
   }
	
	
	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


