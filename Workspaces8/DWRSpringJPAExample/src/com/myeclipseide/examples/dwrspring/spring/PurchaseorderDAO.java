package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Purchaseorder;

/**
 * Data access object (DAO) for domain model class Purchaseorder.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Purchaseorder
 * @author MyEclipse Persistence Tools
 */

public class PurchaseorderDAO extends JpaDaoSupport {
	// property constants
	public static final String STATUS = "status";

	public static final String COMMENTS = "comments";

	public static final String CUSTOMERNUMBER = "customernumber";

	public void save(Purchaseorder transientInstance) {
		logger.info("saving Purchaseorder instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Purchaseorder persistentInstance) {
		logger.info("deleting Purchaseorder instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Purchaseorder update(Purchaseorder detachedInstance) {
		logger.info("updating Purchaseorder instance");
		try {
			Purchaseorder result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Purchaseorder findById(Integer id) {
		logger.info("finding Purchaseorder instance with id: " + id);
		try {
			Purchaseorder instance = getJpaTemplate().find(Purchaseorder.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Purchaseorder> findByProperty(String propertyName, Object value) {
		logger.info("finding Purchaseorder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "select model from Purchaseorder model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Purchaseorder> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Purchaseorder> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List<Purchaseorder> findByCustomernumber(Object customernumber) {
		return findByProperty(CUSTOMERNUMBER, customernumber);
	}

	@SuppressWarnings("unchecked")
	public List<Purchaseorder> findAll() {
		logger.info("finding all Purchaseorder instances");
		try {
			String queryString = "select model from Purchaseorder model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static PurchaseorderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PurchaseorderDAO) ctx.getBean("PurchaseorderDAO");
	}
}
