//PostLoggingAdvice.java
package com.st.spring.advices;

import org.springframework.aop.*;
import java.lang.reflect.*;



public class PostLoggingAdvice  implements AfterReturningAdvice{

	public void afterReturning(Object rv, Method m, Object[] args,
			Object target) throws Throwable {
		
		/*
		here we can have logic to execute after the successful execution 
		the joinpoint .for example the post logging 
		*/

	System.out.println("in afterReturning of postLOg, the method :" +
			""+m.getName()+"" +
			"executed sucessfilly returning"+rv);

	}//method 

}//class
