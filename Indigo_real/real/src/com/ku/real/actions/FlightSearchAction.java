package com.ku.real.actions;

//import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.actions.DispatchAction;

import com.ku.real.forms.FlightSearchForm;
//import com.ku.real.hibernate.dao.FlightRoutesDAOImpL;
//import com.ku.real.hibernate.daoi.FlightRoutesDAO;
//import com.ku.real.hibernate.daoi.FlightsDAO;
//import com.ku.real.hibernate.daoi.FlightsDAOImpL;

import com.ku.real.helper.BusinessDeligateHelper;
//import com.ku.real.hibernate.dao.FlightsDAO;
//import com.ku.real.hibernate.daoi.FlightsDAOImpL;
import com.ku.real.hibernate.persist.Flights;
import com.ku.real.spring.service.daoi.FlightsServiceInterface;
//import com.ku.real.spring.services.Flightsservicebean;
//import com.ku.real.spring.services.FlightsRoutesServicebean;
import com.ku.real.utils.StringTodate;
import com.ku.real.vo.FlightSearchVo;

public class FlightSearchAction extends Action{
	
	public ActionForward execute(ActionMapping am,ActionForm af,
		HttpServletRequest req,HttpServletResponse rea) throws Exception{

		FlightSearchForm fsf =  (FlightSearchForm)af;
		
		FlightSearchVo fsv = new FlightSearchVo();
		
		fsv.setFrom(fsf.getFrom());
		fsv.setTo(fsf.getTo());
	
 java.util.Date date =StringTodate.stringToDate(fsf.getDate());
 
 java.sql.Date sdate = new java.sql.Date(date.getTime());
		
		fsv.setDate(sdate);
				
		try{
		//FlightsDAO fd = new FlightsDAOImpL(fsv);
		 //FlightsServiceInterface fsi = new Flightsservicebean();
		
	FlightsServiceInterface	fsi =  (FlightsServiceInterface) BusinessDeligateHelper.getService("fservice");
		
		
		List<Flights> flights = fsi.searchFlightBySourceDestinationService(fsv);
		   
		//List<Flights> flights =fd.searchFlightBySourceDestination();
		
		int noofflights = flights.size();
		     if(!(noofflights==0)){
		
		    // req.setAttribute("flights",fd.searchFlightBySourceDestination());
		 		req.setAttribute("flight", flights);
		    	 
	    		 return am.findForward("avilableflights");
		    	}
		    		   
			}catch(Exception e){
	
				throw new RuntimeException("run time from action");
				 //e.printStackTrace();   // show some more page 
					
					 
			  	}

		return am.findForward("flightsnotavilable");
	    
	}
}