package learn.basics;

public class Subclass extends Superclass {

	Subclass(){
		
		System.out.println("from subclass constroctor");
	}
	
	
	
    public void printMethod() { //overrides printMethod in Superclass
        super.printMethod();
        System.out.println("Printed in Subclass");
    }
    
    
  
    public static void main(String[] args) {
    	
    Subclass s = new Subclass();
    s.printMethod();	
    
    s.mym();
    }

}