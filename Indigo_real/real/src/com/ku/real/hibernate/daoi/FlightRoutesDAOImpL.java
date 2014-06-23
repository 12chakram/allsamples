package com.ku.real.hibernate.daoi;



import org.hibernate.Query;
import org.hibernate.Session;


import com.ku.real.hibernate.dao.FlightRoutesDAO;
import com.ku.real.hibernate.persist.FlightRoutes;
import com.ku.real.hibernate.utils.GetConnection;

public class FlightRoutesDAOImpL implements FlightRoutesDAO {

	
	

	@Override
	public int getCityIdByName(String cityName) {
		
		Session se = GetConnection.getSessionFactory();
		Query q =se.createQuery("FROM FlightRoutes WHERE city='"+cityName+"'");
			 FlightRoutes fr= (FlightRoutes) q.uniqueResult();
			
			  return fr.getRouteid();
	}

}
