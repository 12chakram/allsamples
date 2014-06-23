package com.st.jdbc.vo;

import java.io.Serializable;



public class User  implements Serializable{
	
	private String unmae,firstName,lastName,email,mobile,pass;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUnmae() {
		return unmae;
	}

	public void setUnmae(String unmae) {
		this.unmae = unmae;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

	//set and get methods for all these properties
	
	
}//class
