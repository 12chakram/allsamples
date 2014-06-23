
 
public class Add { //class name 

  int a,b,c,d; // variable declaration
  

void total() // method 
 {
   a=33;
   b=44;
   c=66;
   d=a+b-c;
    	System.out.println(d);
    }
public static void main (String[] args)// main method
   	 {
        Add b=new Add(); // object 
         b.total();
}
}
