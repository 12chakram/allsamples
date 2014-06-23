package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Productline;

/**
 * Data access object (DAO) for domain model class Productline.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Productline
 * @author MyEclipse Persistence Tools
 */

public class ProductlineDAO extends JpaDaoSupport {
	// property constants
	public static final String TEXTDESCRIPTION = "textdescription";

	public static final String HTMLDESCRIPTION = "htmldescription";

	public static final String IMAGE = "image";

	public void save(Productline transientInstance) {
		logger.info("saving Productline instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Productline persistentInstance) {
		logger.info("deleting Productline instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Productline update(Productline detachedInstance) {
		logger.info("updating Productline instance");
		try {
			Productline result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Productline findById(String id) {
		logger.info("finding Productline instance with id: " + id);
		try {
			Productline instance = getJpaTemplate().find(Productline.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Productline> findByProperty(String propertyName, Object value) {
		logger.info("finding Productline instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "select model from Productline model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Productline> findByTextdescription(Object textdescription) {
		return findByProperty(TEXTDESCRIPTION, textdescription);
	}

	public List<Productline> findByHtmldescription(Object htmldescription) {
		return findByProperty(HTMLDESCRIPTION, htmldescription);
	}

	public List<Productline> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	@SuppressWarnings("unchecked")
	public List<Productline> findAll() {
		logger.info("finding all Productline instances");
		try {
			String queryString = "select model from Productline model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static ProductlineDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductlineDAO) ctx.getBean("ProductlineDAO");
	}
}
