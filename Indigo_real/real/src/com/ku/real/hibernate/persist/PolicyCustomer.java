package com.ku.real.hibernate.persist;

public class PolicyCustomer {
	private int customerid;
	private String customername;
	private int customerage;
	private String customermobile;
    private Policyc policyc;
    
	public Policyc getPolicyc() {
		return policyc;
	}
	public void setPolicyc(Policyc policyc) {
		this.policyc = policyc;
	}
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
	}


