
package  learn.basics;
class Add 
{
int i;
int y;
int c;
void sum ()
{
	i=6;
	y=70;
	c=i+y;
	System.out.println(c);
}
void p()
{
	
  System.out.println("just print");
}

public static void main(String args[])
{
Add a=new Add();

a.p();
a.sum();
}

}
