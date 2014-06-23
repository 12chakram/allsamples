package com.ku.real.hibernate.persist;

import java.sql.Date;

public class  Flights {

	private String flightname;
	private double fare;
	private Date avilabledate;
	private int flightsource;
	private int flightdestination;
	public String getFlightname() {
		return flightname;
	}
	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Date getAvilabledate() {
		return avilabledate;
	}
	public void setAvilabledate(Date avilabledate) {
		this.avilabledate = avilabledate;
	}
	public int getFlightsource() {
		return flightsource;
	}
	public void setFlightsource(int flightsource) {
		this.flightsource = flightsource;
	}
	public int getFlightdestination() {
		return flightdestination;
	}
	public void setFlightdestination(int flightdestination) {
		this.flightdestination = flightdestination;
	}
}