package com.ku.real.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ku.real.forms.PolicyForm;
import com.ku.real.helper.BusinessDeligateHelper;
import com.ku.real.hibernate.persist.Policy;
import com.ku.real.spring.service.daoi.PolicyService;

public class PolicyAction extends DispatchAction {
	
	public ActionForward createPolicy(ActionMapping am,ActionForm af,HttpServletRequest req ,
			HttpServletResponse res)throws Exception{
		
		PolicyForm pf = (PolicyForm)af;
		Policy pc = new Policy();
		pc.setFormno(pf.getFormno());
		pc.setPoliceno(pf.getPolicyno());
		pc.setPolicename(pf.getPolicyname());
		pc.setPaymenttype(pc.getPaymenttype());
		pc.setPoliceprimium(pf.getPolicyprimium());
		pc.setCname(pf.getPolicyname());
		pc.setAge(pf.getAge());
		pc.setMobile(pf.getMobile());
		pc.setMail(pf.getMail());
		
		PolicyService ps = (PolicyService) BusinessDeligateHelper.getService("policyservice"); //need clarity 
		ps.savePolicy(pc);
		
		return am.findForward("created");		
	}
	
	 

	public ActionForward retrievePolicy(ActionMapping am,ActionForm af,HttpServletRequest req ,
			HttpServletResponse res)throws Exception{
		PolicyService ps = (PolicyService) BusinessDeligateHelper.getService("policyservice"); //need clarity 
		
		PolicyForm pf = (PolicyForm)af;
	    Policy pj=ps.retrievePolicy(pf.getPolicyno());
	    
		
	if(pj!=null){
		return am.findForward("policygot"); // showpolicydetails
	}else{
		return am.findForward("policynull"); // again search;
	}

	}
	
}
