<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri ="http://struts.apache.org/tags-html" prefix="html" %>
    
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>
		<body>
			
				<html:form action="customerpolicy.do">
					ID:<html:text property="customerid"/><br/>
					NAME:<html:text property="customername"/><br/>
					AGE:<html:text property="customerage"/><br/>
					Mobile:<html:text property = "customermobile"/><br/>
	                policy<html:text property = "policyno"/> 			
					Policyname:<html:text property = "policyname"/>
				policeAmount:<html:text property ="policyamount"/>
					<html:submit property="method" value = "createpolicycustomer"/>
				</html:form>
			
		</body>
</html>