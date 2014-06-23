//LoggingAdvice.java 
package com.st.spring.advices;
import org.springframework.aop.*;
import java.lang.reflect.*;




public class LoggingAdvice  implements MethodBeforeAdvice{

	public void before(Method m, Object[] args, Object target)
			throws Throwable {
		
	// here we can implement log4J API or other any logging API perform logging 
		
		System.out.println( "in before() for:"+m.getName()+"in"+ target.getClass().getName());
		
		
		
		
}//before 

	}//class
