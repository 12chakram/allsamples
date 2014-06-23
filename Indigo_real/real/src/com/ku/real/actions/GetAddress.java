package com.ku.real.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ku.real.forms.LoginForm;
import com.ku.real.helper.BusinessDeligateHelper;
import com.ku.real.hibernate.dao.AddressDao;
import com.ku.real.hibernate.daoi.AddressDaoI;
import com.ku.real.hibernate.persist.Address;
import com.ku.real.spring.service.daoi.AddressService;
import com.ku.real.spring.service.daoi.Loginservice;
import com.ku.real.vo.LoginVo;

public class GetAddress extends Action {
	
  private Address ad;
	
  public ActionForward execute(ActionMapping am , ActionForm af,
			HttpServletRequest req , HttpServletResponse res) throws Exception {
	  
//		
//		LoginVo lv = new LoginVo();
//		
//		LoginForm lf =  (LoginForm)af;
//		
//		
//		lv.setUname(lf.getUname());
//		
//		String uname = lv.getUname();
//	  
	 String uname = req.getParameter("uname");
		
	AddressService as = (AddressService) BusinessDeligateHelper.getService("aservice");
		
	ad=as.userAddress(uname);
		
	req.setAttribute("addr", ad);

//		AddressDaoI adi = new AddressDao();
//		
//		ad=adi.getAddress(uname);
		
		if(ad!=null){
			return am.findForward("avilable");
		}else{
		
			return am.findForward("noaddress");
			
		}
		
		
		
		
		
		
	}
	

}
