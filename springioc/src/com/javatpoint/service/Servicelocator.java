package com.javatpoint.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Servicelocator {

	public static   Object getService(String servicename){
		
         ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
             Object object =	context.getBean(servicename);
              return object;
		
	}
}
