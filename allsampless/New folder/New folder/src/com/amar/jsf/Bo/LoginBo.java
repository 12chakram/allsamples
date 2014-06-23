package com.amar.jsf.Bo;

import com.amar.jsf.vo.LoginVo;

public class LoginBo {
	
	public boolean validateUser(LoginVo lv)
	{
		if(lv.getUsername().equals("amar") && lv.getPassword().equals("vayyala") )
			return true;
		else
			return false;
	}

}
