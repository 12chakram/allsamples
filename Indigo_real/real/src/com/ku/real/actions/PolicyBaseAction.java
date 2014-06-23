package com.ku.real.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ku.real.forms.CustomerPolicyForm;
import com.ku.real.helper.BusinessDeligateHelper;
import com.ku.real.hibernate.dao.PolicyCustomerDetailsImpl;
import com.ku.real.hibernate.daoi.PolicyCustomerDetailsDAO;
import com.ku.real.spring.service.daoi.PolicyBaseService;
import com.ku.real.vo.PolivyCustomerVo;

public class PolicyBaseAction extends DispatchAction{

	public ActionForward createpolicycustomer(ActionMapping am,ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws 
			Exception{
		
		CustomerPolicyForm cf = (CustomerPolicyForm)af;
		
		PolivyCustomerVo cv  = new PolivyCustomerVo();
		
		cv.setCustomerid(cf.getCustomerid());
		cv.setCustomername(cf.getCustomername());
		cv.setCustomerage(cf.getCustomerage());
		cv.setCustomermobile(cf.getCustomermobile());
		cv.setPolicyno(cf.getPolicyno());
		cv.setPolicyname(cf.getPolicyname());
		cv.setPoliceamount(cf.getPolicyamount());
		
		
		
		try{
		
			//PolicyCustomerDetailsDAO cdd = new PolicyCustomerDetailsImpl();
		//cdd.saveCustomer(cv);
			
		PolicyBaseService ps=(PolicyBaseService) BusinessDeligateHelper.getService("policybaseservice");
		 ps.saveCustomerservice(cv);

		
		return am.findForward("customercreated");
		}catch(Exception e){
			return am.findForward("customer not created");
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
