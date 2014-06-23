//SwitchDemo.java

package learn.basics;

import javax.swing.JOptionPane;

public class SwitchDemo {

	public static void main(String args[]){
		
		String month = JOptionPane.showInputDialog("Enter The Month number");
		
		int monthNumber= Integer.parseInt(month);
			
switch (monthNumber){

case 1:

System.out.println("jan");
System.out.println("Happy New Year");
break;
case 2:
	System.out.println("jan");
	System.out.println("short month");
	break;
	
case 3:
	System.out.println("march");
	System.out.println("summer starting");
	break;
case 4:
	System.out.println("April");
	System.out.println("Exams time");
	break;
	
case 5:
	System.out.println("may");
	System.out.println("very hot");
	break;

case 6:
	System.out.println("jun");
	System.out.println("summer ending");
	break;
case 7:
	System.out.println("julj");
	System.out.println("raining starting ");
	break;
	
case 8:
	System.out.println("aug");
	System.out.println("independent day");
	break;
	
case 9:
	System.out.println("sep");
	System.out.println("Happy birthday");
	break;
case 10:
	System.out.println("october");
	System.out.println("Happy birthday raaga");
	break;
case 11:
	System.out.println("nov");
	System.out.println("Happy birthday to su");
	break;
case 12:
	System.out.println("dec");
	System.out.println("end of the Year");
	break;
default :
	System.out.println("the nubmer u have enterded doesnt match any monthj");
	
	break;
		
}//switch
		
		}//main	
}//class
