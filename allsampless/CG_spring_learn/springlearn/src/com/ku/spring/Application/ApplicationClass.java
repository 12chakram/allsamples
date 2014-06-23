package com.ku.spring.Application;




import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ku.spring.beans.Triangle;
import com.ku.spring.beans.interfaces.Shape;

public class ApplicationClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/* here we are creating triangle object to call draw method of 
	        draw();
		*/
		
//		  Triangle triangle = new Triangle();
//		  triangle.draw();
/*		  
  here we are depending an to creating triangle object are call trhe appopriate method
      this is called tite coupluling.
	
		*/
		  
//		  Circle circle = new Circle();
//		  circle.draw();
		
// and to create 2 saparate object and call to that methods 
		  
		  //Shape shape = new Triangle();
		  //shape.draw();
		  
//		  Shape shape1 = new Circle();
//		  shape1.draw();
		  
// this is called polymarpisam based on our input it will call apropriate method 
		  
		 // myDraw(shape);
		  
		 // here to reduce code we are using polymarpisam using interface concept,
		  // to decalre the draw() method in to shape interface , and our class implements the shape interface 
		  
		  // this is the starting point to bootstartup the spring application 
		
		//one using BeanFactory
		
//		  BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
//		 Triangle triangle = (Triangle)factory.getBean("triangle");
//		 triangle.draw();
//		  

		
		
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		// Triangle triangle = (Triangle)context.getBean("triangle");
		// triangle.draw();
		 
		 Shape shape = (Shape)context.getBean("circle");
		 
		 shape.draw();
		
	}
	
	
//	public static void myDraw(Shape shape){
//		
//		shape.draw();
//	}
//	

}
