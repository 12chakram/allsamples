package learn.basics;

/*border layout the components are arranged into different 
direction like east,west,north,south...etc
 if does not specify any direction the default is center
 ,chances of over laping the components
*/
//package class_notes;
import java.awt.*;
class MyFrame2 extends Frame
{

MyFrame2()
{
	Button b1= new Button("south");
	add(b1,"South");
	
	Button b2= new Button("north");
	add(b2,"North");

	Button b3= new Button("east");
	add(b3,"East");

	Button b4= new Button("west");
	add(b1,"West");

	Button b5= new Button("cente");
	add(b1,"Center");
	
	 setSize(100,200);
      setVisible(true);
	 }
public static void main(String args[])
{
	
new MyFrame2();
}
	
	
	
	
	
}
