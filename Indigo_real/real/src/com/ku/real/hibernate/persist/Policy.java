package com.ku.real.hibernate.persist;

public class Policy {

	private int formno;
	private int policeno;
	private String policename;
	private  double policeprimium;
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
	public int getPoliceno() {
		return policeno;
	}
	public void setPoliceno(int policeno) {
		this.policeno = policeno;
	}
	public String getPolicename() {
		return policename;
	}
	public void setPolicename(String policename) {
		this.policename = policename;
	}
	public double getPoliceprimium() {
		return policeprimium;
	}
	public void setPoliceprimium(double policeprimium) {
		this.policeprimium = policeprimium;
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
	
	
}
