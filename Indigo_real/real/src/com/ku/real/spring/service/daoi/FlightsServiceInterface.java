package com.ku.real.spring.service.daoi;

import java.util.List;

import com.ku.real.hibernate.persist.Flights;
import com.ku.real.vo.FlightSearchVo;

public interface FlightsServiceInterface {
	
	public List<Flights> searchFlightBySourceDestinationService(FlightSearchVo fsv);

}
