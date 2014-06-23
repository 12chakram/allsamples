package com.ku.real.spring.services;

import com.ku.real.hibernate.daoi.PolicyDao;
import com.ku.real.hibernate.persist.Policy;
import com.ku.real.spring.service.daoi.PolicyService;

public class PolicyServicebean implements PolicyService {

	
	PolicyDao pdao;
//	public void PoliceServicebean(PolicyDao pdao){
//		
//	}
//	
	public  PolicyServicebean(PolicyDao pdao){
		this.pdao = pdao;
		
	}
	
	
	@Override
	public void savePolicy(Policy p) {
		pdao.savePolicy(p);
	}

	@Override
	public Policy retrievePolicy(int policyno) {
		return pdao.retrievePolicy(policyno);
		
	}

}
