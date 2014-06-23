/* the following programe explain how to create the frame object and adding components to that*/
/*package class_notes;
import java.awt.*;
class MyFrame extends Frame 
{
MyFrame()
{

}

public static void main(String agrs[])
{
MyFrame mf = new MyFrame();
Button b1 = new Button("ok");
mf.add(b1);
mf.setSize(100,200);
 mf.setVisible(true);
}

  }*/
//=========================================

/* the following programe explain how to create the frame object and adding components to that*/
package class_notes;
import java.awt.*;
 class  MyFrame extends Frame 
{
MyFrame()
{Button b1 = new Button("ok");
this .add(b1);
this .setSize(100,200);
 this.setVisible(true);
}

public static void main(String agrs[])
{
new MyFrame(); // name less object
}
}