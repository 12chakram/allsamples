package learn.basics;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Expression kumar is undefined on line 12, column 14 in Templates/Classes/Class.java.
 */
public class Tast 
{
    int a, b;
    
void setData(int x,int y)
{


a=x;
b=y;

}
    
void dispDat()
{

System.out.println("a value is:"+a);
System.out.println("b value is:"+b);
}
int sum()
{
 int c =a+b;
 return c;
}
void copy(Tast x)
{
a=x.a;
b=x.b;
}
}
//============================== B/L

class SumDemo
        {
public static void main(String args[])
{
   Tast t1 = new Tast();
   int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
   
    t1.setData(q, q); // call by value 
    t1.dispDat();
    System.out.println("\n");
   
    int z= t1.sum();
    System.out.println("the sum is:"+z);
    
    Tast t2 =new Tast();
    t2.copy(t1);  // call by reference
    System.out.println("\n");
    int i = t2.sum();
  System.out.println("the sum is :"+i);

   }//main

}//class


