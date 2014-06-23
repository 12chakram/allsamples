package learn.basics;

//a news paper boy purchase 100 papers every day , 
// he purchase paper at  rs.1.50 , sell at 2.00 each paper
// any unsold papers as scrap sell at 0.50
//write a programe to read the demand and evaliate his profit 



class boy 
{
	int id;
	String name;
	String add;
	
	void setdata (int x,String y,String z)
	{
		id = x;
		name = y;
		add = z;
	}
	void print ()
	{
		System.out.println("id is:"+id);
		System.out.println("name is:"+name);
		System.out.println("add is:"+add);
		
		}
	void calc (int demand )
	{
		double cp,sp,left_over,amount = 0.0;
		if (demand>=100)
		{
			cp=100*1.50;
			sp=100*2.00;
			amount = sp - cp;
		}
	if (demand<100)
	{
		cp=100*1.50;
		left_over =100-demand;
		sp=demand*2.0+left_over*0.50;
		amount = sp - cp;
		
	}
	
	if (amount>0)
	{
		
		System.out.println("the profit is:"+amount);
	}
	else {
		System.out.println("the loss is:"+amount);
	}
		
	}	
	
}	

  public class Paperboy 
 {
public static void main(String args[])
{
	int x = Integer.parseInt(args[3]);
	System.out.println("no of papers sold are:"+x);
	
	
	System.out.println("\n");
	boy b=new boy();
	b.setdata (Integer.parseInt (args[0]), args[1], args[2]);
	b.print();
	b.calc(x);

}
 }

