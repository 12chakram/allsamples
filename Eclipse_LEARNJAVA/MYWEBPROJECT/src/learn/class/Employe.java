/*write a programe to calculate 
the net salary of an employe based on
the given properties eno,ename,basic,
hra,ta,da,persnal lone,vechical lone

complete this programe eaither 3 or 4 
functions
*/

package class_notes;
class Employe 
	{
	int eno,basic,
	hra,ta,da,
	p,
	v;
	String ename,eadd;

	
void setdata()
	{
	eno = 1884;
	ename = "kumar";
	eadd = "skht";
	basic = 36000;
	hra = 1200;
	ta = 200;
	da = 600;
    p =4538;
    v = 2307;
 
	}
public int gsal()
{
int g=basic+hra+ta+da;
System.out.println("the sal is:"+g);
return g;

}

public int lone()
{
//int p,v,l;
int l=p+v;
System.out.println("theloneis:"+l);
return l;
}

void sal()
{
	
	System.out.println("sal is:"+(gsal()-lone()));	

}


public static void main(String args[])
{
    Employe e =new Employe();
   
e.setdata();
 //e.gsal();
   // e.lone();
    e.sal();

}

}