package com.bankofbaroda.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;





@ManagedBean(name="bankloginBean")
@RequestScoped
public class LoginMBean{
	
	private String email;
	private String passWord;
	
	
	public String doLogin(){
				
		if(email != null && passWord != null){
				return "mycourses";
			}
			
		
		
		return "login";
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	 
}// class
