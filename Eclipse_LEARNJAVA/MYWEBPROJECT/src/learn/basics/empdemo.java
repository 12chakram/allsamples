package learn.basics;

class emp
{
	int eno;
	String ename,eadd;



	void setempdata(int eno,String ename,String eadd)
	{
		this.eno = eno;
		this.ename = ename;
		this.eadd = eadd;
}

  void dispemp()
	{
  
  System.out.println("enois:"+eno);
  System.out.println("enameis:"+ename);
  System.out.println("eaddis:"+eadd);
  
  }

}

class pemp extends emp

{

String dept,desig;
 
 void setemp(String x,String y)
	{
 dept=x;
 desig=y;
 System.out.println(x+y);
 }

void printemp()
	{
	System.out.println("dept is:"+dept);
	System.out.println("desig is:"+desig);
	}
}

class empdemo
{
	public static void main (String agrs[])
	{
{
emp p = new emp();

p.setempdata(101,"kumar","skht");
p.dispemp();
//p.setemp("comp","pro");
}
	}
}