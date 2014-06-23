package biz.neustar.dece.portal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.log4j.Logger;

public class DateUtil {
	public static final String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss z";
	private static Logger logHandler = Logger.getLogger("serviceProxyCalls");	
	private static Logger dataLogHandler = Logger.getLogger("serviceProxyDataLog");
	private static Boolean dataLoggerDebugEnabled = dataLogHandler.isDebugEnabled();  
	
	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());

	}
	public static void logMessage(String tranID,String methodCalled,String household, long elapsedTime)
	{
		logHandler.info("^("+now()+")^"+tranID+"^"+methodCalled+"^"+household+"^"+elapsedTime);		
	}	
	public static void logDataMessage(String tranID,String household, String paramsData)
	{
		dataLogHandler.info("^("+now()+")^"+tranID+"^"+household+"^"+paramsData);		
	}
	public static Boolean isDataLoggerDebugEnabled()
	{
		return Boolean.TRUE;
	}
	public static String generateUUID()
	{
		return UUID.randomUUID().toString();	
	}
	/**
	 * October 12th 2010.
	 * Method is used to retrieve age details
	 * @param month,date and year.
	 * @return String
	 */
	public static String getAgeGroup(String month,String date,String year){	
		String ageDetails="";		
		int ageDifference=0;
		
		
		Calendar tdy = new GregorianCalendar();
		System.out.println("Calendar out: "+tdy.toString());
		if(month.equals("0") && date.equals("0") && year.equals("0"))
			ageDetails="DOBInValid";
		
		else if(month.equals("0") && date.equals("0"))
			ageDetails="MonthAndDateMandatory";
		else if(date.equals("0") && year.equals("0"))
			ageDetails="DateAndYearMandatory";
		else if(month.equals("0") && year.equals("0"))
			ageDetails="MonthAndYearMandatory";
		else if("0".equals(date))
			ageDetails="DateInValid";
		else if("0".equals(month))
			ageDetails="MonthInValid";
		else if("0".equals(year))
			ageDetails="YearInValid";
		else if(isValidDate(date+"/"+month+"/"+year))
		{
			
			boolean isDOBInPast = tdy.after(new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date)));
			if(isDOBInPast){
			ageDifference = tdy.get(Calendar.YEAR) - Integer.parseInt(year); 
			if(
				(18==ageDifference) 
					&& 
					(	(Integer.parseInt(month)>(tdy.get(Calendar.MONTH)+1)) || 
						((Integer.parseInt(month)==(tdy.get(Calendar.MONTH)+1)) && (Integer.parseInt(date)>tdy.get(Calendar.DAY_OF_MONTH)))))
				ageDifference--;
			else if(
					(13==ageDifference)
					&& 
					(	(Integer.parseInt(month)<(tdy.get(Calendar.MONTH)+1)) || 
						((Integer.parseInt(month)==(tdy.get(Calendar.MONTH)+1)) && (Integer.parseInt(date)<=tdy.get(Calendar.DAY_OF_MONTH)))))
				ageDifference++;
			
			if(ageDifference<=13){
		    	   ageDetails="CHILD";
		       }else if(ageDifference>13 && ageDifference<18){
		    	   ageDetails="YOUTH";    	   
		       }else if(ageDifference>=18){
		    	   ageDetails="ADULT";     	  
		        }
			}
			else
				ageDetails="InvalidDOB";
		}
		else
        	ageDetails="InvalidDOB";
		

		/*Calendar dobDate = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        Calendar now = new GregorianCalendar();
        //String stSdf=date+"/"+month+"/"+year;
        if(isValidDate(date+"/"+month+"/"+year))
        {
	        int ageDifference = now.get(Calendar.YEAR) - dobDate.get(Calendar.YEAR);
	        if(18==ageDifference){
		        if ((dobDate.get(Calendar.MONTH) > now.get(Calendar.MONTH))|| ((dobDate.get(Calendar.MONTH) == now.get(Calendar.MONTH)) && (dobDate.get(Calendar.DAY_OF_MONTH) > now
		                .get(Calendar.DAY_OF_MONTH)))) {
		        	ageDifference--;         	
		          }
	        }
	       if(ageDifference<=13){
	    	   ageDetails="CHILD";    	  
	       }else if(ageDifference>13 && ageDifference<18){
	    	   ageDetails="YOUTH";    	   
	       }else if(ageDifference>=18){
	    	   ageDetails="ADULT";     	  
	        }
        }
        else
        	ageDetails="DateInValid";*/
       return ageDetails;
	 }	
	
	private static boolean isValidDate(String formattedDate)
	{
		boolean validDate=true;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
        format.setLenient(false); 
       
        try {
        	format.parse(formattedDate);          
        } catch (ParseException e) {         	
        	validDate=false;
        }
        return validDate;
	}
	
	public static Date getDate(String month,String date,String year){
		String dateVal = year + "/" + month + "/" + date;
	    java.util.Date utilDate = null;

	    try {
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	      utilDate = formatter.parse(dateVal);
	      
	    } catch (ParseException e) {
	     
	    }
	    return utilDate;
	}
	
	public static Map<String, String> getDobMonthMap() { 
		ResourceBundle lblsBundle = new SessionUtils().getLabelsTxtsBundle();
		//ResourceBundle lblsBundle = new SessionUtils().getLabelsBundle();
		Map<String, String> dobMonthDetailsList = new LinkedHashMap<String, String>();	
		dobMonthDetailsList.put(lblsBundle.getString("DatePart_MMM"),"0");
		dobMonthDetailsList.put(lblsBundle.getString("MON_JAN"),"01");
		dobMonthDetailsList.put(lblsBundle.getString("MON_FEB"),"02");
		dobMonthDetailsList.put(lblsBundle.getString("MON_MAR"),"03");
		dobMonthDetailsList.put(lblsBundle.getString("MON_APR"),"04");
		dobMonthDetailsList.put(lblsBundle.getString("MON_MAY"),"05");
		dobMonthDetailsList.put(lblsBundle.getString("MON_JUN"),"06");
		dobMonthDetailsList.put(lblsBundle.getString("MON_JUL"),"07");
		dobMonthDetailsList.put(lblsBundle.getString("MON_AUG"),"08");
		dobMonthDetailsList.put(lblsBundle.getString("MON_SEP"),"09");
		dobMonthDetailsList.put(lblsBundle.getString("MON_OCT"),"10");
		dobMonthDetailsList.put(lblsBundle.getString("MON_NOV"),"11");
		dobMonthDetailsList.put(lblsBundle.getString("MON_DEC"),"12");
		return dobMonthDetailsList; 
	}
	
	public static Map<String, String> getDobDateMap() {
		Map<String, String> dobDateDetailsList=new LinkedHashMap<String, String>();
		ResourceBundle lblsBundle = new SessionUtils().getLabelsTxtsBundle();
		//ResourceBundle lblsBundle = new SessionUtils().getLabelsBundle();
		dobDateDetailsList.put(lblsBundle.getString("DatePart_DD"),"0");
		String dayValue;
		for(int count=1;count<=31;count++){
			if(count<10)
				dayValue = "0"+String.valueOf(count);
			else
				dayValue = String.valueOf(count);
			 dobDateDetailsList.put(dayValue, dayValue);			   
	    }			 
	   return dobDateDetailsList;
	}
	
	public static Map<String, String> getDobYearMap() {
		Calendar now = new GregorianCalendar();
		int minYearForDoB = now.get(Calendar.YEAR);
		int maxYearForDoB = 1899;
		Map<String, String> dobYearDetailsList=new LinkedHashMap<String, String>();
		String yearVal;
		ResourceBundle lblsBundle = new SessionUtils().getLabelsTxtsBundle();
		//ResourceBundle lblsBundle = new SessionUtils().getLabelsBundle();
		dobYearDetailsList.put(lblsBundle.getString("DatePart_YYYY"),"0");
		for(int count=minYearForDoB; count>maxYearForDoB; count--){
			yearVal = String.valueOf(count);
			dobYearDetailsList.put(yearVal ,yearVal);
		}
		return dobYearDetailsList;
	}
}