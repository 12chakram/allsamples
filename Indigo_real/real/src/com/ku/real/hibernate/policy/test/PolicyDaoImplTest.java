package com.ku.real.hibernate.policy.test;

import com.ku.real.hibernate.dao.PolicyDaoImpl;
import com.ku.real.hibernate.daoi.PolicyDao;
import com.ku.real.hibernate.persist.Policy;

public class PolicyDaoImplTest {
	
	public void testSavePolicy(Policy p){
		
		PolicyDao pd = new PolicyDaoImpl();
		pd.savePolicy(p);
	}
	
	public Policy testRetrievePolicy(int policyno){
		
		PolicyDao pd = new PolicyDaoImpl();
		return pd.retrievePolicy(policyno);
		
	
	}
	
	public static void main(String[] args) {
		
		PolicyDaoImplTest pdt = new PolicyDaoImplTest();
		Policy pc = new Policy();
		pc.setFormno(1);
		pc.setPoliceno(1088);
		pc.setPolicename("marketmoney");
		pc.setPaymenttype("monthly");
		pc.setPoliceprimium(200000);
		pc.setCname("kumar vayyala");
		pc.setAge(28);
		pc.setSex("male");
		pc.setMobile("9849889255");
		pc.setMail("kumar.v18@gmail.com");
		
		pdt.testSavePolicy(pc);
		System.out.println("ploicy created");
	Policy pp =	pdt.testRetrievePolicy(100);
	System.out.println(pp.getCname());
		
	}
	

}
