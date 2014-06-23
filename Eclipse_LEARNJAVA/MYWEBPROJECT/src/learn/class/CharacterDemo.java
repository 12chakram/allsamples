package class_notes;

class CharacterDemo 
{
public static void main(String agrs[])throws Exception
{
	
	String str = "java";
	
	System.out.print("before for loop:"+str.charAt(2));
	
	
	for( int i=0;i<str.length();i++)
		
	System.out.println(str.charAt(i));
	
}
}
