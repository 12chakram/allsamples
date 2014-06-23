package class_notes;

class Syn implements Runnable // runnable is a marker inter face
{
public void run()
{
display();	

}
//===================================	
	public synchronized void display()
	{
		System.out.println(Thread.currentThread().getName());
		
		for (int i=0;i<10;i++)
			System.out.println("i value is "+i);
		
	}	

	
//=======================================
	public static void main(String agrs[])
	{
		Syn s = new Syn();
		Thread t1= new Thread(s,"first");
		Thread t2= new Thread(s,"second");
		
		t1.start();
		t2.start();
		
}
	
	
	
	
	
	
	
	
	
	
	
}
