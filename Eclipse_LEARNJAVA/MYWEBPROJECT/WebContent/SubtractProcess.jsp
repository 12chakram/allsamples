<!--AddProcess.jsp  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page errorPage = "Error.jsp" %>

<% 
int OP1 = Integer.parseInt(request.getParameter("op1"));
 int OP2 = Integer.parseInt(request.getParameter("op2"));
 
 int RESULT = OP1-OP2;
 

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<b>:RESULT</b>

<%=RESULT %>
<br/>

<%@ include file = "/AddServiceForm.html" %>
</body>
</html>