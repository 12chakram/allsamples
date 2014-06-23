package com.ku.real.hibernate.policy.test;

import com.ku.real.hibernate.dao.PolicyCustomerDetailsImpl;
import com.ku.real.hibernate.daoi.PolicyCustomerDetailsDAO;
import com.ku.real.vo.PolivyCustomerVo;


public class CustomerdetailsDaoImplTest {
	
	PolicyCustomerDetailsDAO cd = new PolicyCustomerDetailsImpl();
	

	public void  testSaveCustomer(PolivyCustomerVo cv){
		
		cd.saveCustomer(cv);
		
		
	}
	
	public static void main(String[] args) {
		
		PolivyCustomerVo cv = new PolivyCustomerVo();
	cv.setCustomerage(28);
	cv.setCustomerid(3);
	cv.setCustomermobile("9849889355");
	cv.setCustomername("vayyala");
		
		
	
	CustomerdetailsDaoImplTest cdi = new CustomerdetailsDaoImplTest();
	
	
	cdi.testSaveCustomer(cv);
	
}

}	