//Personaldetails.java 

package com.st.hibernate.vo;

public class PersonalDetails {
	  private int empno;
	  private String fname,lname;
	  private Address addr;
	  
	  private Employee emp;
	  
	  public void setEmp(Employee e){
		  emp=e;
	  }
	  
	  public Employee getEmp(){
		  return emp;
	  }
	  
	  // getter and setter methods
	  
	  
	  public void setEmpno(int i){
		  empno=i;
	  }
	  public int getEmpno(){
		  
		  return empno;
	  }
	  
	  public void setFname(String s){
		  
		  fname=s;
	  }
	
	public String getFname(){
		
		return fname;
	}
	

	 public void setLname(String s){
		  
		  lname=s;
	  }
	
	public String getLname(){
		
		return lname;
	}
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {    
		this.addr = addr;                // need some clarity 
	}
	
	
	
}
