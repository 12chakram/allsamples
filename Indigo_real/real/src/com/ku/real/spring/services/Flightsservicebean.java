package com.ku.real.spring.services;

import java.util.List;

import com.ku.real.hibernate.dao.FlightsDAO;
//import com.ku.real.hibernate.daoi.FlightsDAOImpL;
import com.ku.real.hibernate.persist.Flights;
import com.ku.real.spring.service.daoi.FlightsServiceInterface;
import com.ku.real.vo.FlightSearchVo;

public class Flightsservicebean  implements FlightsServiceInterface{

  private FlightsDAO fd;	

  
  public void setFd(FlightsDAO fd){
	  this.fd = fd;
  }
	 	@Override
	public List<Flights> searchFlightBySourceDestinationService(FlightSearchVo fsv) {
			
	 		
		 //FlightsDAO fd = new FlightsDAOImpL(); // some times  by spring constructor 
		 	
		return fd.searchFlightBySourceDestination(fsv);
	}
}
