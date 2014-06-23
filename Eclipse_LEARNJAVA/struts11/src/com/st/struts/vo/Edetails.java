//Edetails.java
package com.st.struts.vo;

public class Edetails {

	public Edetails(int empno, String ename, double sal){
		
		this.empno=empno;
		this.ename=ename;
		this.sal=sal;
	}//Constructor
	
	public void setEmpno(int empno){this.empno=empno;}
	public int getEmpno(){ return this.empno;}

	public void setEname(String ename){ this.ename=ename;}
	public String getEname(){ return this.ename;}
	
	public double getSal() {return sal;}

	public void setSal(double sal) {this.sal = sal;}

	
	private int empno;
	private String ename;
	private double sal;
	

}//class

