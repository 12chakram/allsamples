package learn.basics;

public class B implements A{

	public void m1(Object o) {
		System.out.println(1);
		
	}// first m1
	
	
	public void m1(String o) {
		System.out.println(2);
		
	}// second  m1
	 public static void main(String s[]){
	A a1 = new B();
	String o1 = "hello";
	 a1.m1(o1);
	
	 }
	

}
