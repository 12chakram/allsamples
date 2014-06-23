package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Payment;
import com.myeclipseide.examples.dwrspring.jpa.PaymentId;

/**
 * Data access object (DAO) for domain model class Payment.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Payment
 * @author MyEclipse Persistence Tools
 */

public class PaymentDAO extends JpaDaoSupport {
	// property constants
	public static final String AMOUNT = "amount";

	public void save(Payment transientInstance) {
		logger.info("saving Payment instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Payment persistentInstance) {
		logger.info("deleting Payment instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Payment update(Payment detachedInstance) {
		logger.info("updating Payment instance");
		try {
			Payment result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Payment findById(PaymentId id) {
		logger.info("finding Payment instance with id: " + id);
		try {
			Payment instance = getJpaTemplate().find(Payment.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Payment> findByProperty(String propertyName, Object value) {
		logger.info("finding Payment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from Payment model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Payment> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	@SuppressWarnings("unchecked")
	public List<Payment> findAll() {
		logger.info("finding all Payment instances");
		try {
			String queryString = "select model from Payment model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static PaymentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PaymentDAO) ctx.getBean("PaymentDAO");
	}
}
