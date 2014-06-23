package class_notes;

class Syn1  implements Runnable
{
	public void run()
	{
		display();
	}
	
	void display()
	{
		System.out.println(Thread.currentThread().getName());

	synchronized(this);

		//synchronized(getclass())

		for (int i=0;i<10;i++)
			System.out.println(i);
	}
	public static void main(String args[])
	{
		Syn1 s1= new Syn1();
		Thread t1 = new Thread (s1,"first");
		Thread t2 = new Thread (s1,"second");

		t1.start();
        t2.start();

       Syn1 s2= new Syn1();
       	Thread t3 = new Thread (s1,"first");
       	Thread t4 = new Thread (s1,"second");

       	t3.start();
       	t4.start();
}
}
	

