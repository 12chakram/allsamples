<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<%System.out.println("Control in userForm.jsp"); %>
<form:form method="POST" commandName="user">
	<table>
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
				<form:radiobutton path="gender" value="F" label="F" /><form:errors path="gender" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Country :</td>
			<td>
			<form:select path="country">
			<form:option value="0">Select</form:option>
			<form:options items="${countryList}" itemLabel="countryName" itemValue="countryId"/>
			</form:select><form:errors path="country" cssClass="error"/>
		
		<%-- 	<form:select path="country">
				<form:option value="0" label="Select" />
				<form:option value="1" label="India" />
				<form:option value="2" label="USA" />
				<form:option value="3" label="UK" />
			</form:select>
			--%>
			</td>
		</tr>
		<tr>
			<td>About you :</td>
			<td><form:textarea path="aboutYou" /><form:errors path="aboutYou" cssClass="error"/></td>
		</tr>
		
		<tr>
			<td>Community :</td>
			<td>
			<form:checkboxes items="${communityList}" path="community"/><form:errors path="community" cssClass="error"/>
			<%-- 
			<form:checkbox path="community" value="Spring"
				label="Spring" /> <form:checkbox path="community" value="Hibernate"
				label="Hibernate" /> <form:checkbox path="community" value="Struts"
				label="Struts" />
				--%>
				</td>
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