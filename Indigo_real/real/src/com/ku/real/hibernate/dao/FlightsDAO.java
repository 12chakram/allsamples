package com.ku.real.hibernate.dao;

import java.util.List;

import com.ku.real.hibernate.persist.Flights;
import com.ku.real.vo.FlightSearchVo;

public interface FlightsDAO {
	
public List<Flights> searchFlightBySourceDestination(FlightSearchVo fsv);

}
