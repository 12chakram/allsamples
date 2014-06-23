<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties,java.io.FileInputStream,java.net.URL,java.io.FileNotFoundException"%> 
<html>
<body>
<%
    	 Properties property = new Properties();
    	 try{
			String configLocation = config.getServletContext().getInitParameter("decesspConfigLocation");
			property.load(new FileInputStream(configLocation));
    	 }catch(FileNotFoundException fne){
    		String configLocation = config.getServletContext().getRealPath("/").concat("WEB-INF/config.properties").toString();
    		property.load(new FileInputStream(configLocation));
    	 }
		String homePage=property.getProperty("portalGlobal.url");
		response.sendRedirect(homePage);
 %>
</body>
</html>