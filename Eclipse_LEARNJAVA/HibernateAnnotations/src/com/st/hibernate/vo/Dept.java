package com.st.hibernate.vo;

import java.util.*;

public class Dept {

	
	private  int deptno;
	 private String dname,loc;
	Collection <Employee>emps;
	
	
	
	
	 public Collection<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Collection<Employee> emps) {
		this.emps = emps;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	 
	 
}// class

