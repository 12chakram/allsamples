package com.ku.real.hibernate.persist;

public class Policyc {
	
	private int customerid;
	private int policyno;
	private String policyname;
	private int policyamount;
	
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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
	public int getPolicyno() {
		return policyno;
	}
	public void setPolicyno(int policyno) {
		this.policyno = policyno;
	}
	

}
