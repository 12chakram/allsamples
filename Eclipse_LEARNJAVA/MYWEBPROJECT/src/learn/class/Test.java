package class_notes;

class Test
{
int a,b,c;

void sum() // calle
{
	a=8;
	b=7;
	int c=a+b;
	System.out.println(c);
}

void mul()
{
	a=30;  // local veriables
	b=5;
	c=a*b;
	System.out.print(c);

}
public static void main(String args[])
{
	Test t=new Test(); // object creation
	t.sum(); // caller
	t.mul();
}
	}
