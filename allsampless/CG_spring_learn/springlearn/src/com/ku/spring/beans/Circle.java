package com.ku.spring.beans;

import com.ku.spring.beans.interfaces.Shape;

public class Circle implements Shape{
	
	private Point pointA;
	public Point getPointA() {
		return pointA;
	}


	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}


	public Point getPointcircle() {
		return pointcircle;
	}


	public void setPointcircle(Point pointcircle) {
		this.pointcircle = pointcircle;
	}


	public Point getPointcircle2() {
		return pointcircle2;
	}


	public void setPointcircle2(Point pointcircle2) {
		this.pointcircle2 = pointcircle2;
	}


	private Point pointcircle;
	private Point pointcircle2;

	
	public void draw(){
		
		System.out.println("from circle");
		
System.out.println("from triangle");
		
		System.out.println("pointA x value is:"+pointA.getX() +","+" y value is:"+pointA.getY());
		System.out.println("pointB x value is:"+pointcircle.getX() +","+" y value is:"+pointcircle.getY());
		System.out.println("pointC x value is:"+pointcircle2.getX() +","+" y value is:"+pointcircle2.getY());
	}
	
	
}
