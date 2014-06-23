package com.myeclipseide.examples.dwrspring.spring;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.myeclipseide.examples.dwrspring.jpa.Employee;

/**
 * Data access object (DAO) for domain model class Employee.
 * 
 * @see com.myeclipseide.examples.dwrspring.spring.Employee
 * @author MyEclipse Persistence Tools
 */

public class EmployeeDAO extends JpaDaoSupport {
	// property constants
	public static final String LASTNAME = "lastname";

	public static final String FIRSTNAME = "firstname";

	public static final String EXTENSION = "extension";

	public static final String EMAIL = "email";

	public static final String REPORTSTO = "reportsto";

	public static final String JOBTITLE = "jobtitle";

	public void save(Employee transientInstance) {
		logger.info("saving Employee instance");
		try {
			getJpaTemplate().persist(transientInstance);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void delete(Employee persistentInstance) {
		logger.info("deleting Employee instance");
		try {
			getJpaTemplate().remove(persistentInstance);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public Employee update(Employee detachedInstance) {
		logger.info("updating Employee instance");
		try {
			Employee result = getJpaTemplate().merge(detachedInstance);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Employee findById(Integer id) {
		logger.info("finding Employee instance with id: " + id);
		try {
			Employee instance = getJpaTemplate().find(Employee.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByProperty(String propertyName, Object value) {
		logger.info("finding Employee instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from Employee model where model."
					+ propertyName + "= ?1";
			return getJpaTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Employee> findByLastname(Object lastname) {
		return findByProperty(LASTNAME, lastname);
	}

	public List<Employee> findByFirstname(Object firstname) {
		return findByProperty(FIRSTNAME, firstname);
	}

	public List<Employee> findByExtension(Object extension) {
		return findByProperty(EXTENSION, extension);
	}

	public List<Employee> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Employee> findByReportsto(Object reportsto) {
		return findByProperty(REPORTSTO, reportsto);
	}

	public List<Employee> findByJobtitle(Object jobtitle) {
		return findByProperty(JOBTITLE, jobtitle);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		logger.info("finding all Employee instances");
		try {
			String queryString = "select model from Employee model";
			return getJpaTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static EmployeeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EmployeeDAO) ctx.getBean("EmployeeDAO");
	}
}
