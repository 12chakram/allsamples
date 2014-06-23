/**
 * 
 */
package com.ku.real.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.real.forms.LoginForm;
import com.ku.real.helper.BusinessDeligateHelper;
import com.ku.real.hibernate.dao.LoginDao;
import com.ku.real.hibernate.daoi.LoginDaoI;
import com.ku.real.spring.service.daoi.Loginservice;
import com.ku.real.vo.LoginVo;

/**
 * @author user
 *
 */
public class LoginAction extends Action {
	
	public ActionForward execute(ActionMapping am , ActionForm af,
			HttpServletRequest req , HttpServletResponse res) throws Exception {
		
	LoginVo lv = new LoginVo();
	LoginForm lf = (LoginForm)af;   //here we type cast the loginform into af;
	
	
	// and get the data from login form and set the data in to loginvo
	
	
	lv.setUname(lf.getUname());
	lv.setPass(lf.getPass());
	
	Loginservice ls = (Loginservice) BusinessDeligateHelper.getService("lservice");
	
	
	if(ls.login(lv)){
	req.setAttribute("lv", lv);
	return am.findForward("done");
	
	}
	return am.findForward("fail");
	}//execute
	

}//execute
