package com.jsf.completeref.reg.Mbean;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import com.mkyong.EmployeeServicebean;


@RequestScoped
@ManagedBean(name="emp")
public class Employee {
	
	private String firstName;
	private String lastName;
	private String gender;
    private String email;
	private String serviceLevel;
	
	public String addEmployee(){
		
		
		String page = "mypage";
		
		if(firstName.equals("kumar")){
			
			page ="sucess";
			
		
		}
		
		 if( firstName.isEmpty()){
			 
			 EmployeeServicebean esb = new EmployeeServicebean();
			 
			 if(esb.somevalidations()){
				 page= "fail";
			 }
		
		}
		 
		 return page;
		 
		}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
}
