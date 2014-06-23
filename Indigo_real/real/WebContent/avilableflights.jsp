<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri ="http://struts.apache.org/tags-bean"  prefix="bean" %>
    <%@ taglib uri ="http://struts.apache.org/tags-html"  prefix="html" %>
    <%@ taglib uri ="http://struts.apache.org/tags-logic" prefix="logic" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	 <link type = "text/css" href = "css/avilableflights.css" rel ="stylesheet" media ="screen"  />
		
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights pages</title>
		</head>
			<body style="border: double;border-color: orange; size: auto; "><p>AVIALABLE FLIGHTS FOR U R SEARCH</p>
			       <CENTER>
				<table style="table-layout: auto;color: orange;" bordercolor="red";>
				 	<tr>
                   		<th>FLIGHT</th>
						<th>DATE</th>
						<th>FROM</th>
						<th>TO</th>
						<th>FARE</th>
                  </tr>
					 <logic:iterate name="flight" id="flights">
				  <tr style="background-color: green;">	
				 <td><bean:write name="flights" property="flightname"/></td>
                  	<td><bean:write name="flights" property="avilabledate"/></td>
                  	<td><bean:write name="flights" property ="flightsource" /></td>
                  	<td><bean:write name = "flights" property = "flightdestination" /></td>
                  	<td><bean:write name = "flights" property = "fare" /></td>
                  	
                  </tr>
				  </logic:iterate>
                  
               </table>
                  </CENTER>
			</body>
</html>