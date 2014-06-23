package com.ku.real.hibernate.daoi;

import com.ku.real.hibernate.persist.Policy;

public interface PolicyDao {
	
	public void savePolicy(Policy p);
	public Policy retrievePolicy(int policyno);

}
