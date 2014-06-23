package learn.basics;

abstract class shape
{
	
	abstract void area();   // no implementation
	}

//--------------------------------------------
class Rect extends shape
{
	int l,b;
	void setRect ()  // sub class own method can't called by super class /sub type 
	                    //by directly , we want casting  
	{
		l=20;
		b=20;
	}
	
	void area()   // overriding
	
	{
		int z=l*b;
		System.out.println ("the area of Rectangle is:"+z);
		
		
	}
}

//-------------------------------------------------------------

class Square extends shape 
{
	int a;
	void setSquare() // subclass own method
	{
		a=40;
	}
	void area()   //overriding
	{
		int x= a*a;
		System.out.println("the area of Square is:"+x);
		
		}
	
	
}

//----------------------------------------------------

class Shapedemo 
{ 
	public static void main(String[] args)
	{
		// shapes =new shapes();
		//s.area();               NOT POSSIBLE
		
		
		
	/* Rect r1 =new RECT();
		r1.setRect();
	    r1.area();                        
		                                    // not dynamic polymarphic code
		Square sq =new Square();

          sq.setsquare();
          sq.area();
          */	
	
	//------------------------------------------------------
		// in dynamic polymarphism
		
		shape x = new Rect();
        ((Rect)x).setRect();
        x.area();
    //-----------------
        x = new Square();
        ((Square)x).setSquare();
        x.area();
	
	}

}



















