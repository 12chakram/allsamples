package com.vaannila.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CountryServiceList {
	
	
	public static List<CountryService> countryList;
	static{
	
		CountryService countryService=new CountryService();
		countryList=new ArrayList();
		countryService.setCountryId("1001");
		countryService.setCountryName("India");
		countryList.add(countryService);
		
		CountryService countryService1=new CountryService();
		countryService1.setCountryId("1002");
		countryService1.setCountryName("America");
		countryList.add(countryService1);
		
	}
	public List<CountryService> getCountry()
	{
		return countryList;
	}
	

	
	

}
