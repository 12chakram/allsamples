package com.ku.spring.ioc.Applucation;

import com.ku.spring.classes.Circle;
import com.ku.spring.classes.Triangle;
import com.ku.spring.interfaces.Shape;

public class ApplicationClass {
	
	
	public static void main(String[] args) {
		
	/*	Circle circle = new Circle();
		circle.draw();
		
		Triangle triangle = new Triangle();
		triangle.draw();*/

	// using Polymorphism concept 
		
		
		Shape shape = new Triangle();
		//shape.draw();
		
		/*Shape shape = new Circle();
		shapeC.draw();*/
		
		
		myDrawMethod(shape);
		
		
	}
	
public static void myDrawMethod(Shape shape){
	
	shape.draw();
	
}
	

}
