package class_notes;

class EqulasDemo2 
{
	public static void main(String args[])
	{
		String str1="java";
		String str3 = new String("java");
		
		if (str1.equals(str3)) // deep comparison
             System.out.println("both are same");
             
         else
       System.out.println("both are not same");
	 
		
		if (str1==str3) // deep comparison
            System.out.println("both are same");
            
        else
      System.out.println("both are not same");
		
	}

}
/*equals method is depending  on value of the string
this comparison is called has deep comparison
== symbol always comparing the address b/w 2
objects and such comparison called as shallow comparison
,it always depending on address**////