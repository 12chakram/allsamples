package spring.mylearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Traveller {


public static void main(String s[]){
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("Traveller.xml");
	
	Car car = (Car)ctx.getBean("car");
	car.move();
	
	
	
	
	
	
	
	
}//main
	
	
	
	
	
	
	
	
	
	
	
	
}//class
