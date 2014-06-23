package com.ku.real.forms;

import org.apache.struts.action.ActionForm;

public class PolicyForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4972004766875755136L;
		
	private int formno;
	private int policyno;
	private String policyname;
	private  double policyprimium;
	private String paymenttype;
	private int lifecover;
	private String cname;
	private int age;
	private String sex;
	private String mobile;
	private String mail;
	public int getFormno() {
		return formno;
	}
	public void setFormno(int formno) {
		this.formno = formno;
	}
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
	public double getPolicyprimium() {
		return policyprimium;
	}
	public void setPolicyprimium(double policyprimium) {
		this.policyprimium = policyprimium;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public int getLifecover() {
		return lifecover;
	}
	public void setLifecover(int lifecover) {
		this.lifecover = lifecover;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	
}//class
			