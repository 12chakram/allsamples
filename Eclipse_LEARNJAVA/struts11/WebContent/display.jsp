<!-- display.jsp -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
  <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr><th>Empno</th><th>EmpName</th><th>Sal</th></tr>

<logic:iterate name="edetails" scope="request" id="emp">

<tr><td>
<bean:write name="emp" property="empno"/></td>

<td><bean:write name="emp" property="ename"/></td>

<td><bean:write name="emp" property="sal"/>
</tr>

</logic:iterate>
</table>

</body>
</html>