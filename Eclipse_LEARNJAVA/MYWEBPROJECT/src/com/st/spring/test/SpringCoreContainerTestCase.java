package com.st.spring.test;
import com.st.spring.bo.AccountsBo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.*;
import org.springframework.beans.factory.xml.*;

public class SpringCoreContainerTestCase {
 public static void main(String s[]){
	 //Bootstrap the spring core container 

	 BeanFactory beans = new XmlBeanFactory(new ClassPathResource("applicationContext-beans.xml"));
	 
	 //get the spring bean from the container 
	 
	 Object o = beans.getBean("accBo");
	AccountsBo abo = (AccountsBo)o;
	
	System.out.println("invoking withdraw() from main()");
	abo.withdraw(101, 1000);
	
}//main
	}//class
