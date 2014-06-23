package com.st.spring.imp;

import com.st.spring.intf.Wheel;

public class BridgeStone implements Wheel {

	@Override
	public void rotate() {
		System.out.println("wheel rotate");

	}

}
