//DataAccessExceptionHandler.java
package com.st.spring.advices;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.jdbc.*;
import  org.springframework.dao.*;


public class DataAccessExceptionHandler implements ThrowsAdvice {

	public void afterThrowing(DataAccessException e ){
		
		
	System.out.println("in afterThrowing(DAE)");
		
		
	}// afterThrowing DataAccessException 
	
	
	
	public void afterThrowing(BadSqlGrammarException e ){
		
		
		System.out.println("in afterThrowing(BSGE)");
			
			
		}//afterThrowing BadSqlGrammarException
	
	
	
}//class
