<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<html>
	<body>
		<h1>
			MyBlog JSF/JPA CRUD
		</h1>
		<f:view>
			<h:form>
				<h:commandLink action="list" value="Post List" />
				<br>
				<h:commandLink action="#{postController.createPost}"
					value="New Post" />
			</h:form>
		</f:view>
	</body>
</html>