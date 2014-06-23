package com.st.spring.advices;

import org.springframework.aop.ThrowsAdvice;
import java.io.*;

public class IoExceptionHandler implements ThrowsAdvice {
	
	public void afterThrowing(FileNotFoundException e ){
		
		// code to handle the FNE 
/**
	this method invoked by the proxy in case of FNE or its sub type exception  	
		 thrown by the joinpoint 
		
*/		
	
		
		System.out.println("in after throwing (FileNotFoundException)");
		
	}//afterthrowing
	
	public void afterThrowing(IOException e){
		
		/**
		this method invoked IO Exception or its subtype 
			
	*/		
		
		System.out.println("in a (IOE)");
		
	} // after throwing IOException 
	
	
}//class 
