package com.myeclipseide.examples.dwrspring.bean;

import java.util.ArrayList;
import java.util.List;

import com.myeclipseide.examples.dwrspring.jpa.Customer;
import com.myeclipseide.examples.dwrspring.jpa.Employee;
import com.myeclipseide.examples.dwrspring.jpa.Productline;
import com.myeclipseide.examples.dwrspring.spring.CustomerDAO;
import com.myeclipseide.examples.dwrspring.spring.EmployeeDAO;
import com.myeclipseide.examples.dwrspring.spring.ProductlineDAO;

public class CarManager {

	private CustomerDAO customerDao;

	private EmployeeDAO employeeDao;

	private ProductlineDAO productlineDao;

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public List<Customer> getCustomerList() {
		return customerDao.findAll();
	}

	public String[] getCustomerNames() {
		ArrayList<String> names = new ArrayList<String>();
		List<Customer> customers = customerDao.findAll();
		for (Customer customer : customers) {
			names.add(customer.getCustomername());
		}
		return names.toArray(new String[0]);
	}

	public String[] getProductNames() {
		ArrayList<String> names = new ArrayList<String>();
		List<Productline> products = productlineDao.findAll();
		for (Productline product : products) {
			names.add(product.getProductline());
		}
		return names.toArray(new String[0]);
	}

	public String[] getEmployeeNames() {
		ArrayList<String> names = new ArrayList<String>();
		List<Employee> employees = employeeDao.findAll();
		for (Employee employee : employees) {
			names.add(employee.getFirstname() + " " + employee.getLastname());
		}
		return names.toArray(new String[0]);
	}

	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setProductlineDao(ProductlineDAO productlineDao) {
		this.productlineDao = productlineDao;
	}

	public List<Employee> getEmployeeList() {
		return employeeDao.findAll();
	}

	public List<Productline> getProductList() {
		return productlineDao.findAll();
	}

}
