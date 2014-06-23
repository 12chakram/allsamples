package com.st.spring.imp;

import com.st.spring.intf.Wheel;

public class Safari implements Wheel{
	
	/*
		Wheel w = new Wheel();
	 public void move(){
		 w.rotate();
		
		}	 
	 }
*/
	
	
	Wheel wheel;  // interface type 

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}
	

	
	 void move(){
		 
		 wheel.rotate();
	 }

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
