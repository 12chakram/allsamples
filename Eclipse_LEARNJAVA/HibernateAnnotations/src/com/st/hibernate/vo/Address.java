package com.st.hibernate.vo;

import javax.persistence.Entity;

@Entity
public class Address {
	
	private String street,city,state;
	private int pin;
	private PersonalDetails pddetails;
	
	
	// setter and getter methods 
	
	
	public PersonalDetails getPddetails() {
		return pddetails;
	}
	public void setPddetails(PersonalDetails pddetails) {
		
		this.pddetails = pddetails;
		
		//this.pddetails = pddetails;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}//class
