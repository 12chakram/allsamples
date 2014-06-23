package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Orderdetail;
import com.myeclipseide.examples.dwrspring.jpa.OrderdetailId;

/**
 * Data access object (DAO) for domain model class Orderdetail.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Orderdetail
 * @author MyEclipse Persistence Tools
 */

public class OrderdetailDAO extends JpaDaoSupport {
	// property constants
	public static final String QUANTITYORDERED = "quantityordered";

	public static final String PRICEEACH = "priceeach";

	public static final String ORDERLINENUMBER = "orderlinenumber";

	public void save(Orderdetail transientInstance) {
		logger.info("saving Orderdetail instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Orderdetail persistentInstance) {
		logger.info("deleting Orderdetail instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Orderdetail update(Orderdetail detachedInstance) {
		logger.info("updating Orderdetail instance");
		try {
			Orderdetail result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Orderdetail findById(OrderdetailId id) {
		logger.info("finding Orderdetail instance with id: " + id);
		try {
			Orderdetail instance = getJpaTemplate().find(Orderdetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Orderdetail> findByProperty(String propertyName, Object value) {
		logger.info("finding Orderdetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "select model from Orderdetail model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Orderdetail> findByQuantityordered(Object quantityordered) {
		return findByProperty(QUANTITYORDERED, quantityordered);
	}

	public List<Orderdetail> findByPriceeach(Object priceeach) {
		return findByProperty(PRICEEACH, priceeach);
	}

	public List<Orderdetail> findByOrderlinenumber(Object orderlinenumber) {
		return findByProperty(ORDERLINENUMBER, orderlinenumber);
	}

	@SuppressWarnings("unchecked")
	public List<Orderdetail> findAll() {
		logger.info("finding all Orderdetail instances");
		try {
			String queryString = "select model from Orderdetail model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static OrderdetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrderdetailDAO) ctx.getBean("OrderdetailDAO");
	}
}
