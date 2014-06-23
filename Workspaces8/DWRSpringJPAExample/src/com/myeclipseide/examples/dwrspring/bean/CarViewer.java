package com.myeclipseide.examples.dwrspring.bean;

import java.util.List;

import com.myeclipseide.examples.dwrspring.jpa.Customer;
import com.myeclipseide.examples.dwrspring.jpa.Employee;
import com.myeclipseide.examples.dwrspring.jpa.Productline;

public class CarViewer {

	private CarManager carManager;

	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}

	public String getCustomerData() {
		StringBuffer data = new StringBuffer();
		List<Customer> customers = carManager.getCustomerList();

		// generate table header
		data.append("<table><thead><tr>");
		data.append("<th>First Name</th>");
		data.append("<th>Last Name</th></tr></thead>\n");
		data.append("<tbody>\n");

		for (Customer c : customers) {
			data.append("<tr>\n");
			data.append("<td>" + c.getContactfirstname() + "</td>\n");
			data.append("<td>" + c.getContactlastname() + "</td>\n");
			data.append("</tr>\n");
		}

		data.append("</tbody></table>\n");
		return data.toString();
	}

	public String getEmployeeData() {
		StringBuffer data = new StringBuffer();
		List<Employee> employees = carManager.getEmployeeList();

		// generate table header
		data.append("<table><thead><tr>");
		data.append("<th>First Name</th>");
		data.append("<th>Last Name</th></tr></thead>\n");
		data.append("<tbody>\n");

		for (Employee e : employees) {
			data.append("<tr>\n");
			data.append("<td>" + e.getFirstname() + "</td>\n");
			data.append("<td>" + e.getLastname() + "</td>\n");
			data.append("</tr>\n");
		}

		data.append("</tbody></table>\n");
		return data.toString();
	}

	public String getProductData() {
		StringBuffer data = new StringBuffer();
		List<Productline> products = carManager.getProductList();

		// generate table header
		data.append("<table><thead><tr>");
		data.append("<th>Product</th>");
		data.append("<th>Description</th></tr></thead>\n");
		data.append("<tbody>\n");

		for (Productline p : products) {
			data.append("<tr>\n");
			data.append("<td valign=top>" + p.getProductline() + "</td>\n");
			data.append("<td>" + p.getTextdescription() + "</td>\n");
			data.append("</tr>\n");
		}

		data.append("</tbody></table>\n");
		return data.toString();
	}
}
