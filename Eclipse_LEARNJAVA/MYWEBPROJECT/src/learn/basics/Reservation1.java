package learn.basics;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Reservation1
{

    public static int smoking[]=new int[5];
	public static int nons[]=new int[5];
	public static void main(String args[])
	{
		String output="Enter 1 for smoking"+"Enter 2 for nonsEnter 3 for seats info"+"Enter 4 for Exit";
		
		JOptionPane.showMessageDialog(null,output,"ReservationSystem",JOptionPane.INFORMATION_MESSAGE);
		String ch=JOptionPane.showInputDialog("Enter a choice");
		int choice=Integer.parseInt(ch);
		int i=0,j=0;
		while(choice !=4)
		{
			switch(choice)
			{

				case 1:
					if(i>4 ){
							JOptionPane.showMessageDialog(null,"All seats are book in smokingsection"+" Do u want a seat in nons");


							break;}
         		 smoking(i);
					i++;
					break;
				case 2:
				        	if(j>4){
							JOptionPane.showMessageDialog(null,"All seats are book innonsmoking section"+"Do u want a seat in smoking");
							break;}

				   nons(j);
				   j++;
				   break;
				 case 3:
				   status();
				   break;
				 default:

				 JOptionPane.showMessageDialog(null,"You enter awrong choice"+"enteragain");

			}
	        ch=JOptionPane.showInputDialog("Enter a choice");
		     choice=Integer.parseInt(ch);
		}
		System.exit(0);
	}
	public static void smoking(int x)
	{
	/*	if(x>4){

				break;
			}*/

		String name=JOptionPane.showInputDialog("Enter pas name");

		smoking[x]=1;
		String output="passenger name--"+name+"seat no--"+x+"Smoking section";

		JOptionPane.showMessageDialog(null,output,"BoardingPass",JOptionPane.INFORMATION_MESSAGE);

	}
		public static void nons(int y)
        {
		String name=JOptionPane.showInputDialog("Enter pas name");

		nons[y]=1;
		String output="passenger name--"+name+"seat no--"+y+"NonSmoking section";

		JOptionPane.showMessageDialog(null,output,"BoardingPass",JOptionPane.INFORMATION_MESSAGE);
		}
		public static void status()
		{
			int t;
		JTextArea outputArea=new JTextArea(20,30);
		outputArea.append("Smoking	nonSmoking");
		for( t=0;t<5;t++)
		outputArea.append(smoking[t]+"	"+nons[t]+"");
			JOptionPane.showMessageDialog(null,outputArea,"seatinfo",JOptionPane.INFORMATION_MESSAGE);


		}

}
