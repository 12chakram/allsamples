package com.ku.real.hibernate.policy.test;

import com.ku.real.helper.BusinessDeligateHelper;
import com.ku.real.hibernate.persist.Policy;
import com.ku.real.spring.service.daoi.Loginservice;
import com.ku.real.spring.service.daoi.PolicyService;
import com.ku.real.spring.services.PolicyServicebean;

public class PoliceServicebeanTest {
	PolicyService ps = (PolicyService)BusinessDeligateHelper.getService("policyservice");
	
	public void testSavePolicy(Policy p) {
	ps.savePolicy(p);
	}
	public Policy testretrievePolicy(int policyno){
	 return ps.retrievePolicy(policyno);		
    }
	
	public static void main(String[] args) {
		PoliceServicebeanTest pst = new PoliceServicebeanTest();
		Policy pc = new Policy();
		pc.setFormno(1);
		pc.setPoliceno(100);
		pc.setPolicename("moneyplus");
		pc.setPaymenttype("monthly");
		pc.setPoliceprimium(200000);
		pc.setCname("kumar");
		pc.setAge(28);
		pc.setSex("male");
		pc.setMobile("9849889255");
		pc.setMail("kumar.v18@gmail.com");
		
		pst.testSavePolicy(pc);
		System.out.println("policy created from service test");
	Policy p=pst.testretrievePolicy(100);
	
        System.out.println(p.getMail());	
	
	
	
	}


}
