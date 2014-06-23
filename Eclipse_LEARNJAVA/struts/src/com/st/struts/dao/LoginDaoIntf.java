// LoginDaoIntf.java

package com.st.struts.dao;
import com.st.struts.vo.LoginVo;

public interface LoginDaoIntf {

	public boolean validateUser(LoginVo lv);
		
}
