<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>JSP for UserLoginForm form</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body>
	<html:form action="/userLogin">
		<table border="1" cellpadding="4">
			<tbody>
				<tr>
					<td align="right">
						User Name:
					</td>
					<td>
						<html:text property="userName" />
					</td>
					<td>
						<html:errors property="userName" />
					</td>
				</tr>
				<tr>
					<td align="right">
						Password:
					</td>
					<td>
						<html:password property="password" />
					</td>
					<td>
						<html:errors property="password" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<html:submit />
						<html:cancel />
					</td>
				</tr>
			</tbody>
		</table>
	</html:form>
</body>
</html:html>
