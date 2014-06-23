package com.amar.demo.learn;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class LoginNavigationBean {
	
	public String navigateToHomePage(){
	

		return "homepage";
}

}