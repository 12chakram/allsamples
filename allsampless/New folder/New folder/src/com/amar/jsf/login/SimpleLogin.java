package com.amar.jsf.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.amar.jsf.vo.LoginVo;
import com.amar.st.spring.dao.LoginValidateService;
import com.amar.st.spring.daoimpl.LoginValidateServiceBean;
import com.ku.real.helper.BusinessDeligateHelper;


@ManagedBean(name="SimpleLogin")
@RequestScoped
public class SimpleLogin{
	String loginname;
	String password;

	public SimpleLogin(){}

	public String getLoginname(){
		return loginname;
	}

	public void setLoginname(String loginname){
		this.loginname = loginname;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String CheckValidUser(){
		
		LoginVo lv=new LoginVo();
		lv.setUsername(loginname);
		lv.setPassword(password);
		
		LoginValidateService lvs = (LoginValidateServiceBean)BusinessDeligateHelper.getService("lvsb");
		if(lvs.validateUserService(lv))
		{
			return "success";	
		}
		else
		{
			return "fail";
		}
		
		
        /*if(loginname.equals("chakram") && password.equals("chakram")){
	System.out.println("chandan");
			return "success";
		}
		else{
			return "fail";
		}
*/	}
}