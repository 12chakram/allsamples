package com.my.interview;

public class StaticInitilaizer {
	public static final int A = 5;
	public static final int B;
	//Static initializer block, which is executed only once when the class is loaded.
	static {
	if(A == 5)
	B = 10;
	else
	B = 5;
	}
	public StaticInitilaizer(){} // constructor is called only after static initializer block
}

 class Test {
	
	System.out.println("A =" + StaticInitilaizer.A + ", B =" + StaticInitilaizer.B);
}

