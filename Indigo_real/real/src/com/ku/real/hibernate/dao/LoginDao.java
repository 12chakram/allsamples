package com.ku.real.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ku.real.hibernate.daoi.LoginDaoI;
import com.ku.real.hibernate.utils.GetConnection;
import com.ku.real.vo.LoginVo;

public class LoginDao implements LoginDaoI {


	
	@Override
	public boolean validateUser(LoginVo lv) {
		
		// to get the hibernate session 
		
		String uname=lv.getUname();
		String pass=lv.getPass();
		
		Session se = GetConnection.getSessionFactory();
		
		
		
	
		 
		String hql = "FROM User u WHERE u.uname='" + uname + "'and u.pass='"+pass+"'";
	System.out.println(hql);
		Query q = se.createQuery(hql);
        Object o = q.uniqueResult();
        System.out.println(o);
		
        if(o!=null){
        	return true;
        }else{
        	return false;
        }
	
	}

}
