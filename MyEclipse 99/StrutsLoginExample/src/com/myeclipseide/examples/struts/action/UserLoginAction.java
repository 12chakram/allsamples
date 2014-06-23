package com.myeclipseide.examples.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.myeclipseide.examples.struts.form.UserLoginForm;

public class UserLoginAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserLoginForm userLoginForm = (UserLoginForm) form;

		if (userLoginForm.getUserName().equalsIgnoreCase("myeclipse")
				&& userLoginForm.getPassword().equalsIgnoreCase("myeclipse")) {
			request.setAttribute("userName", userLoginForm.getUserName());
			return mapping.findForward("success");
		}

		return mapping.findForward("failure");

	}
}
