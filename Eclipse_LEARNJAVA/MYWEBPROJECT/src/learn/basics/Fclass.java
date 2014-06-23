

package learn.basics;

import java.lang.reflect.*;
public class Fclass {

	public static void main(String args[]){
		
		Class clas=java.lang.Integer.class;
		
		String info;
		
		info=(clas.getName()+clas.getClass()+clas.getCanonicalName());
		
		
		System.out.println(info);
		
		
		
	}//main
	
	
	
	
	
	
}//class
