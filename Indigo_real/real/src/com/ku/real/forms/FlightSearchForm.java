package com.ku.real.forms;

import org.apache.struts.action.ActionForm;

public class FlightSearchForm extends ActionForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4926283377287570628L;
	
	private String from;
	private String to;
	private String date;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	} 

}
