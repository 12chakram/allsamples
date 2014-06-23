//EmployeeMinDetails.java
package com.st.hibernate.vo;

public class EmployeeMinDetails {

	public EmployeeMinDetails (int empno, String dname, double sal){
		
		this.empno=empno;
		this.dname=dname;
		this.sal=sal;
	}//Constructor
	
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}


	public int empno;
	public String dname;
	public double sal;
	

}//calss
