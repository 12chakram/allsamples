<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>


<html:form action="Login.do">
UserName:<html:text property="uname"> </html:text>
Password:<html:password property="pass"></html:password>

<html:submit value="login"></html:submit>

</html:form>


</body>
</html>