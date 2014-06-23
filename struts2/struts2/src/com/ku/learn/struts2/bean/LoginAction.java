package com.ku.learn.struts2.bean;

public class LoginAction {
	
	
	private String uname;
	private String pword;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	
	
	public String userLogin(){
		
		
		if(uname.equals("kumar") && pword.equals("vayyala")){
			
			return "done";
			
		}
		
		return "fail";
	}

}
