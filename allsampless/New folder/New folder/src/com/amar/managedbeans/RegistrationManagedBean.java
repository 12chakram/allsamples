package com.amar.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registrationManagedBean")
@RequestScoped

public class RegistrationManagedBean{
	
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String companyName;
	private String country;
	private String city;
	private boolean agreed = true;
	
	public boolean isAgreed() {
		return agreed;
		}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
		}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getcompanyName() {
		return companyName;
	}
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
