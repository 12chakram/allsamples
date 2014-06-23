package com.st.spring.imp;

import com.st.spring.intf.Vehical;

public class YamahaRX135 implements Vehical{
	
	
	
	public void speed(){
		
		System.out.println("very fast");
	}

	@Override
	public void move() {
		System.out.println("35 km for 1 l");
		
	}

}
