<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Address  page</title>
</head>
<body>

		<p align="right">
			username is:<%=request.getParameter("uname") %><br>
		<p>
		<table border = "1">
			
			<tr>
				<th>USERNAME</th>
				<th>CITY</th>
				<th>STATE</th>
				<th>PIN</th>
		   </tr>
		  
		   
			<tr>
				<td><bean:write name = "addr" property = "uname" /></td>
				<td><bean:write name = "addr" property = "city" /></td>
				<td><bean:write name = "addr" property = "state" /></td>
				<td><bean:write name = "addr" property = "pin" /></td>
				
			<tr>		
		
		
		</table>
	
</body>
</html>