package com.ku.real.forms;

import org.apache.struts.action.ActionForm;

public class PolicyDetailsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2230406628427410930L;
	
	private  int policyno;
	private String policyname;
	private int policyamount;
	public int getPolicyno() {
		return policyno;
	}
	public void setPolicyno(int policyno) {
		this.policyno = policyno;
	}
	public String getPolicyname() {
		return policyname;
	}
	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}
	public int getPolicyamount() {
		return policyamount;
	}
	public void setPolicyamount(int policyamount) {
		this.policyamount = policyamount;
	}
}
