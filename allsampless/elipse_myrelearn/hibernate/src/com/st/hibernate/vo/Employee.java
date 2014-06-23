 //Employee.java

package com.st.hibernate.vo ;
 
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import com.st.hibernate.vo.Employee;
 
 @Entity
 @Table(name="emp")
 @NamedQuery(name="query1", query="FROM Employee e WHERE e.sal<200 ")
 public class  Employee
 
{
	private String name;
	private int empno,deptno;       
	private double sal;
	//private PersonalDetails pddetails;
	//private Dept deptDetails;
	
	
	//public void setDeptDetails(Dept d){ deptDetails=d;}
	
	//public Dept getDeptDetails() { return deptDetails;}
	
	

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
	
	/**
	 * @param pdetails the pdetails to set
	 */
	//public PersonalDetails getPddetails() {return pddetails;}
	//public void setPddetails(PersonalDetails pddetails) {this.pddetails = pddetails;}
	/**
	 * @return the pdetails
	 */


}// class 
