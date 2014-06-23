package com.ku.real.spring.services;

import com.ku.real.hibernate.daoi.PolicyCustomerDetailsDAO;
import com.ku.real.spring.service.daoi.PolicyBaseService;
import com.ku.real.vo.PolivyCustomerVo;

public class PolicyBaseServiceImpL implements PolicyBaseService {

	
	   PolicyCustomerDetailsDAO pcd;
	   
	   public void setPcd(PolicyCustomerDetailsDAO pcd){
		   this.pcd = pcd;
	   }

	@Override
	public void saveCustomerservice(PolivyCustomerVo pcv) {
		
		pcd.saveCustomer(pcv);
	}

	   
}



