package com.ku.bob;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;














@ManagedBean(name="bankloginBean")
@RequestScoped
public class LoginMBean{
	
	private String email;
	private String passWord;
	private long mobile;
	
	
	
	

	
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




	public String doLogin(){
			
		if(email != null && passWord != null){
			
				return "mycourses";
			}
		return "login";
	}
	
	
	// validate email field with validator 
	
	public void validateEmail(FacesContext context,UIComponent toValidate,Object value) throws ValidatorException {
			String emailStr = (String) value;
			if (-1 == emailStr.indexOf("@")) {
			FacesMessage message = new FacesMessage("Invalid emailaddress");
			throw new ValidatorException(message);
			}
			}



    
	public long getMobile() {
		return mobile;
	}




	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
	
	
}
	
	