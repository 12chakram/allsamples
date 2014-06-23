package com.myeclipseide.examples.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UserLoginForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String password;

	private String userName;

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if (getUserName() == null || getUserName().length() < 1) {
			errors.add("userName", new ActionMessage("error.name.required"));
		} else if (!getUserName().equalsIgnoreCase("myeclipse")) {
			errors.add("userName", new ActionMessage("error.name.myeclipse"));
		}
		if (getPassword() == null || getPassword().length() < 1) {
			errors
					.add("password", new ActionMessage(
							"error.password.required"));
		} else if (!getPassword().equalsIgnoreCase("myeclipse")) {
			errors.add("password",
					new ActionMessage("error.password.myeclipse"));
		}

		return errors;

	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		password = null;
		userName = null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
