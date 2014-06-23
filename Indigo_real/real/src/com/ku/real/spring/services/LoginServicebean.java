package com.ku.real.spring.services;

import com.ku.real.hibernate.dao.LoginDao;
import com.ku.real.hibernate.daoi.LoginDaoI;
import com.ku.real.spring.service.daoi.Loginservice;
import com.ku.real.vo.LoginVo;

public class LoginServicebean  implements Loginservice{

	private LoginDaoI ldi;
	
	public LoginServicebean(LoginDaoI ldi){
		this.ldi=ldi;
	}
	
	
	// LoginDaoI ldi = new LoginDao();
	@Override
	public boolean login(LoginVo lv) {
		// TODO Auto-generated method stub
		
		 boolean flag =ldi.validateUser(lv);
		
		return flag;
			
			
	
	}

}
