package com.st.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.st.spring.imp.Car;
import com.st.spring.intf.Vehical;
import com.st.spring.intf.Wheel;

public class Travler {

	
	
	public static void main(String args[]){
		
		//Car c = new Car();
		//c.move();
		
		
		//Vehicle v = new Car();
		
		//v.move();

	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	Vehical v = (Vehical)ctx.getBean("vehical");
	Vehical k =(Vehical)ctx.getBean("ku");
	Vehical ku=(Vehical)ctx.getBean("kuu");
	
	k.move();

	v.speed();
	
	ku.move();
	ku.speed();

	
	
	Wheel w = (Wheel)ctx.getBean("safari");
	
	w.rotate();

	
	
	
	}
}
