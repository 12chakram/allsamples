 //Employee.java

package com.st.hibernate.vo ;
 
 import com.st.hibernate.vo.PersonalDetails;
 
 
 public class  Employee
 
{
	private String name;
	private int empno,deptno;       
	private double sal;
	private PersonalDetails pddetails;
	private Dept deptDetails;
	
	
	public void setDeptDetails(Dept d){ deptDetails=d;}
	
	public Dept getDeptDetails() { return deptDetails;}
	
	

// properties 

// write setter and getter methods 

public void setEmpno (int i)
	{
	   empno=i;
	}
	public int getEmpno()
	{
		return empno;
	}

public void setDeptno(int i)
	{
	deptno=i;
	}
public int getDeptno()
	{
	return deptno;
	}
	public void setSal(double d)
	{
		sal=d;
	}
	public double getSal()
	{ 
		return sal;
	}
	public void  setName(String s)
	{
		name=s;
	}
	public String getName()
	{
		return name;
	}
	/**
	 * @param pdetails the pdetails to set
	 */
	public void setPddetails(PersonalDetails pddetails) {
		this.pddetails = pddetails;
	}
	/**
	 * @return the pdetails
	 */
	public PersonalDetails getPddetails() {
		return pddetails;
	}

}// class 
