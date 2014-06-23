package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Office;

/**
 * Data access object (DAO) for domain model class Office.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Office
 * @author MyEclipse Persistence Tools
 */

public class OfficeDAO extends JpaDaoSupport {
	// property constants
	public static final String CITY = "city";

	public static final String PHONE = "phone";

	public static final String ADDRESSLINE1 = "addressline1";

	public static final String ADDRESSLINE2 = "addressline2";

	public static final String STATE = "state";

	public static final String COUNTRY = "country";

	public static final String POSTALCODE = "postalcode";

	public static final String TERRITORY = "territory";

	public void save(Office transientInstance) {
		logger.info("saving Office instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Office persistentInstance) {
		logger.info("deleting Office instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Office update(Office detachedInstance) {
		logger.info("updating Office instance");
		try {
			Office result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Office findById(String id) {
		logger.info("finding Office instance with id: " + id);
		try {
			Office instance = getJpaTemplate().find(Office.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Office> findByProperty(String propertyName, Object value) {
		logger.info("finding Office instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from Office model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Office> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<Office> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Office> findByAddressline1(Object addressline1) {
		return findByProperty(ADDRESSLINE1, addressline1);
	}

	public List<Office> findByAddressline2(Object addressline2) {
		return findByProperty(ADDRESSLINE2, addressline2);
	}

	public List<Office> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Office> findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List<Office> findByPostalcode(Object postalcode) {
		return findByProperty(POSTALCODE, postalcode);
	}

	public List<Office> findByTerritory(Object territory) {
		return findByProperty(TERRITORY, territory);
	}

	@SuppressWarnings("unchecked")
	public List<Office> findAll() {
		logger.info("finding all Office instances");
		try {
			String queryString = "select model from Office model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static OfficeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OfficeDAO) ctx.getBean("OfficeDAO");
	}
}
