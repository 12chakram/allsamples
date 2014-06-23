
<!-- Home.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action ="test.spring" method ="post" />
<pre>

Empno:<input type = "text" name = "empno" value = "${employee.empnno }"/>

<input type = "hidden" name = "_page" value = "1"/>

<! --this describe the page to present next on submiting this form is the wizard at index1. wizard index 
starts with 0, as specified into 'pages' property-->

 
<input type = "submit"  value = "Next"/>



</pre>

</form> 


</body>
</html>