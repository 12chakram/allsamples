package com.ku.real.forms;

import org.apache.struts.action.ActionForm;

public class CustomerPolicyForm extends ActionForm {
	
	private static final long serialVersionUID = 9129052370368926131L;
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public int getCustomerage() {
		return customerage;
	}
	public void setCustomerage(int customerage) {
		this.customerage = customerage;
	}
	public String getCustomermobile() {
		return customermobile;
	}
	public void setCustomermobile(String customermobile) {
		this.customermobile = customermobile;
	}
	private int customerid;
	private String customername;
	private int customerage;
	private String customermobile;
	private String policyname;
	private int policyamount;
	private int policyno;
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
	public int getPolicyno() {
		return policyno;
	}
	public void setPolicyno(int policyno) {
		this.policyno = policyno;
	}

}
