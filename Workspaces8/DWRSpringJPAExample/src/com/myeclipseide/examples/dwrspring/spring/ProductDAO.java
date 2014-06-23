package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Product;

/**
 * Data access object (DAO) for domain model class Product.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Product
 * @author MyEclipse Persistence Tools
 */

public class ProductDAO extends JpaDaoSupport {
	// property constants
	public static final String PRODUCTNAME = "productname";

	public static final String PRODUCTLINE = "productline";

	public static final String PRODUCTSCALE = "productscale";

	public static final String PRODUCTVENDOR = "productvendor";

	public static final String PRODUCTDESCRIPTION = "productdescription";

	public static final String QUANTITYINSTOCK = "quantityinstock";

	public static final String BUYPRICE = "buyprice";

	public static final String MSRP = "msrp";

	public void save(Product transientInstance) {
		logger.info("saving Product instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Product persistentInstance) {
		logger.info("deleting Product instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Product update(Product detachedInstance) {
		logger.info("updating Product instance");
		try {
			Product result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Product findById(String id) {
		logger.info("finding Product instance with id: " + id);
		try {
			Product instance = getJpaTemplate().find(Product.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByProperty(String propertyName, Object value) {
		logger.info("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from Product model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Product> findByProductname(Object productname) {
		return findByProperty(PRODUCTNAME, productname);
	}

	public List<Product> findByProductline(Object productline) {
		return findByProperty(PRODUCTLINE, productline);
	}

	public List<Product> findByProductscale(Object productscale) {
		return findByProperty(PRODUCTSCALE, productscale);
	}

	public List<Product> findByProductvendor(Object productvendor) {
		return findByProperty(PRODUCTVENDOR, productvendor);
	}

	public List<Product> findByProductdescription(Object productdescription) {
		return findByProperty(PRODUCTDESCRIPTION, productdescription);
	}

	public List<Product> findByQuantityinstock(Object quantityinstock) {
		return findByProperty(QUANTITYINSTOCK, quantityinstock);
	}

	public List<Product> findByBuyprice(Object buyprice) {
		return findByProperty(BUYPRICE, buyprice);
	}

	public List<Product> findByMsrp(Object msrp) {
		return findByProperty(MSRP, msrp);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		logger.info("finding all Product instances");
		try {
			String queryString = "select model from Product model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDAO) ctx.getBean("ProductDAO");
	}
}
