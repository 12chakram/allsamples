<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!

int count=0;
 
public int getCount(){
	
	return ++count;
	
	
}// getCount


// this code goes into the Generated Servlet out side of the service() method


%> 

<%

 int localcount=0;

String uname = request.getParameter("uname");


%>  



<html>
<head>

<title>MY FIRST JSP PROGRAME </title>
</head>
<body>
<b>GlobalCount</b>

<% out.println(getCount());%>
<br/>

<b>LocalCount</b>: <%= ++localcount  %>
<br/>
WElcome,<%= uname %>

</body>
</html>