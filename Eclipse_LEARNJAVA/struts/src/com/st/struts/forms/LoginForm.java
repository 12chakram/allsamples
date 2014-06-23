//LoginForm.java
package com.st.struts.forms;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	
	private String uname,pass;

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
	
	
}//class
