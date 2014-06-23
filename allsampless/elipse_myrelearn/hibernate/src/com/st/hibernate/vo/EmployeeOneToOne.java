 //Employee.java

package com.st.hibernate.vo ;
 
 import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.st.hibernate.vo.PersonalDetails;


 
 @Entity
 @Table(name="emp")
 public class  EmployeeOneToOne
 
{
	private String name;
	private int empno,deptno;       
	private double sal;
	private PersonalDetails pddetails;
	
	/*private Dept deptDetails;
public void setDeptDetails(Dept d){ deptDetails=d;}
	public Dept getDeptDetails() { return deptDetails;}
	*/
	

// properties 

// write setter and getter methods 
@Id	
@Column(name="eno")
public int getEmpno(){return empno;}
public void setEmpno (int i){empno=i;}
	
public int getDeptno(){return deptno;}
public void setDeptno(int i){deptno=i;}

//here property name and column names are same , so no need
//to specify the @column annotation

public double getSal(){ return sal;}
public void setSal(double d){sal=d;}

@Column(name="ename")
    public String getName(){return name;}
	public void  setName(String s){name=s;}
	
	
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	
	public PersonalDetails getPddetails() {return pddetails;}
	public void setPddetails(PersonalDetails pddetails) {this.pddetails = pddetails;}
	

}// class 
