package com.ku.real.spring.service.daoi;

import com.ku.real.hibernate.persist.Policy;

public interface PolicyService {

	public void savePolicy(Policy p);
	public Policy retrievePolicy(int policyno);
	
}
