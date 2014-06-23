package com.st.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.st.spring.imp.Car;



public class Travller {


public static void main(String args[]){
	
	//Car c = new Car();
	//c.move();
	
	
	
	
	ApplicationContext cxt = new ClassPathXmlApplicationContext
			("applicationcontext.xml");
	
	
	
	
	Car c=(Car)cxt.getBean("car");
	c.move();
	
	
	
	
	
}




}
