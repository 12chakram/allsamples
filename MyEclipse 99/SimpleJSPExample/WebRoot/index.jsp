<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="com.myeclipseide.examples.jsp.MyJavaClass"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
		<%
			String path = request.getContextPath();
			String pageURL = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
		%>
		<h2>
			Use this simple JSP page to
		</h2>
		<ul>
			<li>
				Determine how the page URL and relative context path can be
				determined programmatically
			</li>
			<li>
				Practice setting breakpoints on the lines of Java code to test JSP
				debugging
			</li>
			<li>
				Access a Java class in your source folder
			</li>
		</ul>
		<table width="70%" border="1" align="center">
			<tbody>
				<tr>
					<td>
						<strong>URL:</strong>
					</td>
					<td>
						<%=pageURL%>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Context Path:</strong>
					</td>
					<td>
						<%=path%>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Date:</strong>
					</td>
					<td>
						<%=new Date().toString()%>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Java Class:</strong>
					</td>
					<td>
						<%=MyJavaClass.getMessage()%>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
