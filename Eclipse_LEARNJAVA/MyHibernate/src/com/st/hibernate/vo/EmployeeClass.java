//EmployeeClass.java 

package com.st.hibernate.vo;

public abstract  class EmployeeClass {

	private int empno;
	private String name;
	
	// setter and getter methods
	
	public void setEmpno(int i){ empno=i;}
	public int getEmpno(){ return empno;}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}//class
