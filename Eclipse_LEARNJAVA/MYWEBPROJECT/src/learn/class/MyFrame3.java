package class_notes;
import java.awt.*;
class MyFrame3 extends Frame
{
   MyFrame3()
   {
	  setLayout(new FlowLayout()); 
	 
	  MyFrame mf =new MyFrame();
	  
	  Button b1= new Button("south");
	  mf.add(b1,"South");

	  Button b2= new Button("north");
	  mf.add(b2,"North");

	  Button b3= new Button("east");
	  mf.add(b3,"East");

	  Button b4= new Button("west");
	  mf.add(b1,"West");

	  Button b5= new Button("cente");
	  mf.add(b1,"Center");

	  mf. setSize(100,200);
	    mf.setVisible(true);

	   
}
 public static void main(String args[])
 {
	 new MyFrame3();
	 
	 Button b1= new Button("south");
	 // mf.add(b1,"South");
 }
	
	
	
	
	
	
}
