//LoginBo.java
package com.st.struts.bo;

import com.st.struts.dao.DAOFactory;
import com.st.struts.dao.DbConstants;
import com.st.struts.dao.LoginDaoIntf;
import com.st.struts.vo.LoginVo;

public class LoginBo {
 
	public static boolean validateUser(LoginVo lv){
		
		DAOFactory OracleFactory =DAOFactory.getDaoFactory(DbConstants.DBTYPE);
		
		LoginDaoIntf dao = OracleFactory.getLoginDao();
		
		return dao.validateUser(lv);
		
	}//validateUser
	
	}//class

