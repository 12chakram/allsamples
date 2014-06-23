package com.ku.spring.beans;

import com.ku.spring.beans.interfaces.Shape;

public class Triangle implements Shape {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	
	
	
	
	public Point getPointA() {
		return pointA;
	}





	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}





	public Point getPointB() {
		return pointB;
	}





	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}





	public Point getPointC() {
		return pointC;
	}





	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}





	public void draw(){
		
		System.out.println("from triangle");
		
		System.out.println("pointA x value is:"+pointA.getX() +","+" y value is:"+pointA.getY());
		System.out.println("pointB x value is:"+pointB.getX() +","+" y value is:"+pointB.getY());
		System.out.println("pointC x value is:"+pointC.getX() +","+" y value is:"+pointC.getY());
	}

}
