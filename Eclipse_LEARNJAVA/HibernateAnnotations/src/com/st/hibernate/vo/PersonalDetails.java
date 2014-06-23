//Personaldetails.java 

package com.st.hibernate.vo;

import javax.persistence.Entity;

import javax.persistence.Id;



@Entity

public class PersonalDetails {
	  private int empno;
	  private String fname,lname;
	//  private Address addr;
	  
	  private EmployeeOneToOne emp;
	  
	// getter and setter methods
	  
	  public void setEmp(EmployeeOneToOne e){
		  emp=e;
	  }
	  
	  public EmployeeOneToOne getEmp(){return emp; }
	  
	  
	  @Id	
    public int getEmpno(){ return empno;}
	  public void setEmpno(int i){empno=i;}
	 
	  
	  public void setFname(String s){fname=s; }
	
	public String getFname(){return fname;}
	
public void setLname(String s){lname=s;}
	
	public String getLname(){return lname;}
	

/*	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {    
		this.addr = addr;                // need some clarity 
	}
	*/
	
	
}
