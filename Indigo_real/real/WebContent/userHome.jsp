<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri ="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
yes<br>
<html:form action ="getaddress.do">   
<html:hidden property="uname" />
<html:submit value= "GET ADDRESS"/>
</html:form>

</body>
</html>