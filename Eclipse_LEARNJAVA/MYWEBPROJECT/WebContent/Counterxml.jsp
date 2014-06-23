<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">





<jsp:declaration>

int count=0;
 
public int getCount(){
	
	return ++count;
	
	
}// getCount


// this code goes into the Generated Servlet out side of the service() method

</jsp:declaration>



<jsp:scriptlet>
 

int localcount=0;

String user = request.getParameter("user");


</jsp:scriptlet>




<html>
<head>

<title>MY FIRST JSP PROGRAME </title>
</head>
<body>
<b>GlobalCount</b>

GlobalCount:<jsp:expression> ++count </jsp:expression>
<br/>

LocalCount:<jsp:expression>++localcount</jsp:expression>
<br/>
WElcome,<jsp:expression>request.getParameter("user")</jsp:expression>

</body>
</html>