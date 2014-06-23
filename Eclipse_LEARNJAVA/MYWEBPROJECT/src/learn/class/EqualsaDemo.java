// BYy using this method we can able to find 
//out both objects are same are not or finding the object equality 

package class_notes;

class EqualsaDemo 
{
public static void main(String args[])
{
	String  str1 = "java";
	String  str2 = "java";  //String  str2 = "Java";
	
	if (str1.equals(str2))   //deepcomparison
    System.out.println("both are same");
     
      else 
    	  System.out.println("both are not same ");
   
	if (str1==str2) // shallow comparision
		System.out.println("both are same");
	else 
		System.out.println("both are  not same");

   }	
	
	
}
