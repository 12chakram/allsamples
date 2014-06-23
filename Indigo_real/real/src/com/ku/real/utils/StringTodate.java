package com.ku.real.utils;

import java.sql.SQLData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

 
import com.ku.real.forms.FlightSearchForm;

public class StringTodate {

	private static Date date;

  public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

public static Date stringToDate(String str_date){
	 try{
		                     //new FlightSearchForm().getDate();
		 
		 DateFormat formatter;
		 
		 
		 formatter = new SimpleDateFormat("dd-MM-yy");
	
		 
		 date= (Date)formatter.parse(str_date);
		 
		 System.out.println(date);
		  return date;
		 
		 }catch(Exception e){
			 System.out.println(e);
			 return null;
		}
	 }


public static void main(String[] args) {
	
	StringTodate.stringToDate("24-08-2011");
}

}
