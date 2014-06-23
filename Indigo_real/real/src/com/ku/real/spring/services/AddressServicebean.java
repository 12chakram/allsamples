package com.ku.real.spring.services;

import com.ku.real.hibernate.daoi.AddressDaoI;
import com.ku.real.hibernate.persist.Address;
import com.ku.real.spring.service.daoi.AddressService;
import com.ku.real.vo.LoginVo;




public class AddressServicebean implements AddressService {

	
	private AddressDaoI adi;
	
	 public AddressServicebean(AddressDaoI adi){
		 
		 this.adi=adi;
	 }
	
	
	@Override
	public Address userAddress(String uname) {
		Address ad =adi.getAddress(uname);
		return ad;
	}

}
