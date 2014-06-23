<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
request.setAttribute("cdate", new java.util.Date());
%>

ToDay Date is<br/>

US style:<fmt:formateDate type="both" value="${cdate}"/> 

<br/>

