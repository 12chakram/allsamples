package com.ku.real.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ku.real.hibernate.daoi.AddressDaoI;
import com.ku.real.hibernate.persist.Address;
import com.ku.real.hibernate.utils.GetConnection;

import com.ku.real.vo.LoginVo;

public class AddressDao implements AddressDaoI {

	Address a;
	
	String uname;
	
	@Override
	public Address getAddress(String uname) {
		
	//String uname=lv.getUname();
		
	Session se = GetConnection.getSessionFactory();
	
	String hql = "FROM Address u WHERE u.uname='"+ uname + "'";
	System.out.println(hql);
	
	Query q = se.createQuery(hql);
	
	a = (Address) q.uniqueResult();
	
	if(a!=null){
	    
		return a;

	}else{
		return null;
	}
	
	}

}