package com.ku.real.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ku.real.hibernate.daoi.PolicyDao;
import com.ku.real.hibernate.persist.Policy;
import com.ku.real.hibernate.utils.GetConnection;


public class PolicyDaoImpl implements PolicyDao {

	@Override
	public void savePolicy(Policy p) {
	
		Session se = GetConnection.getSessionFactory();
		Transaction tx = se.beginTransaction();
		se.save(p);
		tx.commit();
	//se.close();
	}
	
	@Override
	public Policy retrievePolicy(int policyno) {
	
		Session se = GetConnection.getSessionFactory();
		String hql = "FROM Policy p WHERE p.policeno="+policyno+"";
		Query q = se.createQuery(hql);
		List <Policy> p =q.list();
		if(p.isEmpty()){
			return null;
		}
		Policy pc = p.get(0);
		return pc;
	}
}

