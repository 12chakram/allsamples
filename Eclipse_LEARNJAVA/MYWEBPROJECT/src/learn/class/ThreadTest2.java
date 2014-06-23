package class_notes;

 class ThreadTest2 implements Runnable 
{
	 public void run()
	 {
       withdraw();		 
	 }

void withdraw ()
{
 int bal= 10000;
 int amount = 8000;
  if (bal>amount)
	  System.out.println("take your cash");

}
//===========================

public static void main (String []args)
{
 ThreadTest2 tt = new ThreadTest2();	

 Thread t1 = new Thread(tt);
 Thread t2 = new Thread(tt);
t1.start();
t2.start();
}


}
 
 /* give bad results 
take your cash
take your cash

 
 
 in this programme two threads
 (both the two persons get the money  access the 
 withdraw method at symantaniously
 
 for this program we required "SYNCRONIZATION" */