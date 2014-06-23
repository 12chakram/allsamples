//HourlyEmployee.java

package com.st.hibernate.vo;

public class HourlyEmployeePc extends EmployeeClass {

	private int ratePerHour;
	
	private double maxHoursPerDay;

	public int getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(int ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public double getMaxHoursPerDay() {
		return maxHoursPerDay;
	}

	public void setMaxHoursPerDay(double maxHoursPerDay) {
		this.maxHoursPerDay = maxHoursPerDay;
	}
	
}//class
