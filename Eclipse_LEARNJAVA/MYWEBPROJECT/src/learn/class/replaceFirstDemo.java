package class_notes;

class replaceFirstDemo
{
public static void main(String agrs[])
{
	String str1= "satyatechnologies";
//System.out.println(str1.replaceFirst("t","T"));// replace required character when it is first time accrue

System.out.println(str1.replaceAll("t","T")); // replacing the character in all accrues


}
}