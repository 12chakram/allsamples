package com.ku.real.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginForm extends ActionForm{

	private String uname;
	private String pass;
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	

@Override
public ActionErrors validate(ActionMapping mapping,
		HttpServletRequest request) {
	
	ActionErrors errors = new ActionErrors();
	if(uname !=null && uname.equals("")){
		
		errors.add("uname", new ActionMessage("u.name"));
			
	}
	
	if(pass !=null && pass.equals("")){
		errors.add("pass", new ActionMessage("pass"));
	} else{
		System.out.println("ok");
	
	}


	
	return errors;
	

}

}






