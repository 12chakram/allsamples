package com.ku.real.hibernate.daoi;

import java.sql.Date;
import java.util.List;

import javax.management.Query;

import org.hibernate.Session;

import com.ku.real.hibernate.dao.FlightRoutesDAO;
import com.ku.real.hibernate.dao.FlightsDAO;
import com.ku.real.hibernate.persist.Flights;
import com.ku.real.hibernate.utils.GetConnection;
import com.ku.real.vo.FlightSearchVo;
public class FlightsDAOImpL implements FlightsDAO {

 	
	
		@Override
	public List<Flights> searchFlightBySourceDestination(FlightSearchVo fsv) {
		
		 FlightRoutesDAO frd = new FlightRoutesDAOImpL();
	   		 
		int from =frd.getCityIdByName(fsv.getFrom());
		int to = frd .getCityIdByName(fsv.getTo());
		Date d = fsv.getDate();
		
		if (from == to){
		
	              return null;  // some message to user 
	              
		}
		Session se = GetConnection.getSessionFactory();
		
String hql ="FROM Flights f WHERE f.flightsource ='"+from+"' and f.flightdestination ='"+to+"" +
		"' and f.avilabledate ='"+d.getDate()+"-"+(d.getMonth()+1)+"-"+(d.getYear()-100)+"'";

 System.out.println(hql);

 org.hibernate.Query q = se.createQuery(hql);
 

 List<Flights> flights =q.list();
 
 
 return flights;
 
 }

		 
			
		

}
