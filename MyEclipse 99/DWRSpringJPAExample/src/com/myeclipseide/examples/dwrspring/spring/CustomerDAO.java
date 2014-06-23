package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Customer;

/**
 * Data access object (DAO) for domain model class Customer.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Customer
 * @author MyEclipse Persistence Tools
 */

public class CustomerDAO extends JpaDaoSupport {
	// property constants
	public static final String CUSTOMERNAME = "customername";

	public static final String CONTACTLASTNAME = "contactlastname";

	public static final String CONTACTFIRSTNAME = "contactfirstname";

	public static final String PHONE = "phone";

	public static final String ADDRESSLINE1 = "addressline1";

	public static final String ADDRESSLINE2 = "addressline2";

	public static final String CITY = "city";

	public static final String STATE = "state";

	public static final String POSTALCODE = "postalcode";

	public static final String COUNTRY = "country";

	public static final String SALESREPEMPLOYEENUMBER = "salesrepemployeenumber";

	public static final String CREDITLIMIT = "creditlimit";

	public void save(Customer transientInstance) {
		logger.info("saving Customer instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Customer persistentInstance) {
		logger.info("deleting Customer instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Customer update(Customer detachedInstance) {
		logger.info("updating Customer instance");
		try {
			Customer result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Customer findById(Integer id) {
		logger.info("finding Customer instance with id: " + id);
		try {
			Customer instance = getJpaTemplate().find(Customer.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findByProperty(String propertyName, Object value) {
		logger.info("finding Customer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from Customer model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Customer> findByCustomername(Object customername) {
		return findByProperty(CUSTOMERNAME, customername);
	}

	public List<Customer> findByContactlastname(Object contactlastname) {
		return findByProperty(CONTACTLASTNAME, contactlastname);
	}

	public List<Customer> findByContactfirstname(Object contactfirstname) {
		return findByProperty(CONTACTFIRSTNAME, contactfirstname);
	}

	public List<Customer> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Customer> findByAddressline1(Object addressline1) {
		return findByProperty(ADDRESSLINE1, addressline1);
	}

	public List<Customer> findByAddressline2(Object addressline2) {
		return findByProperty(ADDRESSLINE2, addressline2);
	}

	public List<Customer> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<Customer> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Customer> findByPostalcode(Object postalcode) {
		return findByProperty(POSTALCODE, postalcode);
	}

	public List<Customer> findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List<Customer> findBySalesrepemployeenumber(
			Object salesrepemployeenumber) {
		return findByProperty(SALESREPEMPLOYEENUMBER, salesrepemployeenumber);
	}

	public List<Customer> findByCreditlimit(Object creditlimit) {
		return findByProperty(CREDITLIMIT, creditlimit);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		logger.info("finding all Customer instances");
		try {
			String queryString = "select model from Customer model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static CustomerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CustomerDAO) ctx.getBean("CustomerDAO");
	}
}
