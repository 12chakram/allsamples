//Course.java

package com.st.hibernate.vo;

import java.util.Collection;

import com.st.hibernate.vo.Employee1;

public class Course {

	private int cid,dur;
	private double fee;
	private String cname;
	
	private Collection<Employee1>emps;
	
	
	
	public Collection<Employee1> getEmps() {
		return emps;
	}
	public void setEmps(Collection<Employee1> emps) {
		this.emps = emps;
	}
	
	public int getCid() {
		return cid;
	}
	
	
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getDur() {
		return dur;
	}
	public void setDur(int dur) {
		this.dur = dur;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	//setter and getter methodes 
	
}//class
