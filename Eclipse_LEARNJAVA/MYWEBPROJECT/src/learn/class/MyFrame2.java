//border layout the components are arranged into different 
//direction like east,west,north,south...etc
 //if does not specify any direction the default is center
 //,chances of over laping the components

package class_notes;
import java.awt.*;
class MyFrame2 extends Frame
{

MyFrame2()
{
	}
public static void main(String args[])
{
	
MyFrame2 mf=new MyFrame2();
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
}
