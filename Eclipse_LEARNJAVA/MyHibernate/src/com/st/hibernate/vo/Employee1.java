//Employee.java

package com.st.hibernate.vo;

import java.util.Collection;

public class Employee1 {
   
	private int empno,deptno;
	private String name;
	private double sal;
	private Collection<Course>courses;
	
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public Collection<Course> getCourses() {
		return courses;
	}
	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
