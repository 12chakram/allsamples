//LoginAction.java

package com.st.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.st.struts.bo.LoginBo;
import com.st.struts.forms.LoginForm;
import com.st.struts.vo.LoginVo;

public class LoginAction extends Action {

	public ActionForward execute
    (ActionMapping am,ActionForm af,
HttpServletRequest req, HttpServletResponse res)throws Exception{
		
		
		LoginVo lv=new LoginVo();
		
		LoginForm lf= (LoginForm)af;
		
		// get the data and set the data into vo
		
		lv.setUname(lf.getUname());
		lv.setPass(lf.getPass());
		
		boolean status=LoginBo.validateUser(lv);
		

		String forwardstring="sucess";
		if(!status){
			forwardstring="failed";
			
	}//if
	
		//instance the ActionForward
		
		ActionForward forward = new ActionForward();
		
		// recall the first application
		
		forward=am.findForward(forwardstring);
		
		return(forward);
		}//execute
	                                 	
}//class
