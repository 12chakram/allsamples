package com.ku.real.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ku.real.hibernate.daoi.PolicyCustomerDetailsDAO;

import com.ku.real.hibernate.persist.PolicyCustomer;
import com.ku.real.hibernate.persist.Policyc;
import com.ku.real.hibernate.utils.GetConnection;
import com.ku.real.vo.PolivyCustomerVo;

public class PolicyCustomerDetailsImpl implements PolicyCustomerDetailsDAO {

	
	@Override
	public void  saveCustomer(PolivyCustomerVo pcv) {

		PolicyCustomer c = new PolicyCustomer();
		
		
		c.setCustomerid(pcv.getCustomerid());
		c.setCustomername(pcv.getCustomername());
		c.setCustomerage(pcv.getCustomerage());
		c.setCustomermobile(pcv.getCustomermobile());
		
		Policyc pc = new Policyc();	
		
		
		pc.setCustomerid(pcv.getCustomerid());
		pc.setPolicyno(pcv.getPolicyno());
		pc.setPolicyname(pcv.getPolicyname());
		pc.setPolicyamount(pcv.getPolicyamount());
		
		c.setPolicyc(pc);

		
		Session s = GetConnection.getSessionFactory();
		Transaction tx = s.beginTransaction();
        s.save(c);
			

		
		tx.commit();
		

                        
                        
		
}//class

	
	

}	
	