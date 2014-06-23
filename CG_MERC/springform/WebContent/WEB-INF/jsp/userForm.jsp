<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="spring-tld" prefix="spring"%>
<%@ taglib uri="c-tld" prefix="c"%>
<%-- uri should match with the taglib-uri in xml file, that's taglib-location shows the actual path--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.error{
font-style:italic;
color:blue;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>

<form:form method="POST" commandName="user">
	<table>
	
		<tr><td colspan="3"><center><spring:message code="from.heading"></spring:message></center></td></tr>
		<%-- spring:message code attribute will display messages from resource boundle --%>
		<tr>
			<td>User Name :</td>
			<td><form:input path="name" /><form:errors path="name" cssClass="error"/></td>
		
		</tr>
		
		<tr>
			<td>Password :</td>
			<td><form:password path="password" /><form:errors path="password" cssClass="error"/></td>
		</tr>
		
		<tr>
			<td>Gender :</td>
			<td><form:radiobutton path="gender" value="M" label="M" /> 
				<form:radiobutton path="gender" value="F" label="F" />
				<form:errors path="gender" cssClass="error"/></td>
		</tr>
		
		<tr>
			<td>Country :</td>
			<td><form:select path="country">
				<form:option value="0" label="Select" />
				<form:option value="1" label="India" />
				<form:option value="2" label="USA" />
				<form:option value="3" label="UK" />
			</form:select><form:errors path="country" cssClass="error"/>
			
			</td>
		</tr>
		<tr>
			<td>About you :</td>
			<td><form:textarea path="aboutYou" /><form:errors path="aboutYou" cssClass="error"/></td>
		</tr>
		
		
		<tr>
			<td>Community :</td>
			<td><form:checkbox path="community" value="Spring"
				label="Spring" /> <form:checkbox path="community" value="Hibernate"
				label="Hibernate" /> <form:checkbox path="community" value="Struts"
				label="Struts" /><form:errors path="community" cssClass="error"/></td>
		</tr>
		
		
		<tr>
			<td></td>
			<td><form:checkbox path="mailingList"
				label="Would you like to join our mailinglist?" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form:form>

</body>
</html>