<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri ="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
	<head>
		<script type="text/javascript">

         
				function validateLoginForm(){
			var uname = document.getElementById("u").value;
			var pass =  document.getElementById("p").value;

				if(uname == ""){
                      alert("Please Enter The UserName");
                      return false;
					}else if (uname.length<5){
						alert("UserName Should be Minimun 5 Characters");
						return false;
					}else if(pass ==""){ 
						alert(" Password must");
						return false;
						}
			return true;
			}


		</script>
	<link type = "text/css" href = "css/avilableflights.css" rel ="stylesheet" media ="screen"  />
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
	</head>
<body>
	  <center>
		<html:form action = "login.do"><br/><br/>
<table>
	<tr>
		<td>UserName:</td>
		<td class="td"><html:text property="uname" styleId = "u" /></td>
	</tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td></td><td></td></tr>
	
	<tr>	
		<td>passWord:</td>
		<td class=td><html:password property="pass" styleId = "p"  /></td>
	</tr>
	<tr><td></td><td></td></tr>
	
	<tr>
		<td colspan = "2" align = "center"><html:submit value="login" onclick ="return validateLoginForm();"/></td>
	</tr>
</table>			
</html:form>
	</center>		
</body>
</html>