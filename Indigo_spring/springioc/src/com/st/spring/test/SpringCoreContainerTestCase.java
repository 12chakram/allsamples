//SpringCoreContainerTestCase.java
package com.st.spring.test;

import com.st.spring.bo.AccountsBo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreContainerTestCase {
 public static void main(String s[]){

	 //Bootstrap the spring core container 

	 ApplicationContext cxt = new ClassPathXmlApplicationContext
				("applicationcontext.xml");
		
	 
	 //get the spring bean from the container 
	 
	 Object o = cxt.getBean("accBo");
	AccountsBo abo = (AccountsBo)o;
	
	System.out.println("invoking withdraw() from main()");
	abo.withdraw(101, 1000);
	
}//main
	}//class

