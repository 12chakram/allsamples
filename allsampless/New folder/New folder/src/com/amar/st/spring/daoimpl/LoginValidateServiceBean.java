package com.amar.st.spring.daoimpl;

import com.amar.jsf.Bo.LoginBo;
import com.amar.jsf.vo.LoginVo;
import com.amar.st.spring.dao.LoginValidateService;

public class LoginValidateServiceBean implements LoginValidateService {
	private LoginBo loginbo;
	//boolean var=false;
	 
	public LoginBo getLoginbo() {
		return loginbo;
	}

	public void setLoginbo(LoginBo loginbo) {
		this.loginbo = loginbo;
	}

	public boolean validateUserService(LoginVo lv) {
		
		
		//LoginValidate loginvalidate=new LoginBo();
		//return loginvalidate.validateUser(lv);

//		var=loginvalidate.validateUser(lv);
//		return var;
		
		return loginbo.validateUser(lv);
	}

}
