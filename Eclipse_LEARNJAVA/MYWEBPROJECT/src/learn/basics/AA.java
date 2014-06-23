package learn.basics;

class AA 
{
    final public int methodl(int a, int b) { return 0; } 
} 
class B extends AA 
{ 
    public int method1 (int a, int b) {return 1; } 
} 
public class Test 
{
    public static void main(String args[]) 
    { 
        B b; 
        System.out.println("x = " + b.method1(0, 1));  
    } 
}