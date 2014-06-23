<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Post Detail</title>
	</head>
	<body>
		<f:view>
			<h:messages errorStyle="color: red" infoStyle="color: green"
				layout="table" />
			<h1>
				Post Detail
			</h1>
			<h:form>
				<h:panelGrid columns="2">
					<h:outputText value="Id:" />
					<h:outputText value="#{postController.currentPost.id}" title="Id" />
					<h:outputText value="Title:" />
					<h:outputText value="#{postController.currentPost.title}"
						title="Title" />
					<h:outputText value="Content:" />
					<h:outputText value="#{postController.currentPost.content}"
						title="Content" />
					<h:outputText value="Post time:" />
					<h:outputText value="#{postController.currentPost.posttime}"
						title="Posttime">
						<f:convertDateTime pattern="MM/dd/yyyy hh:mma" />
					</h:outputText>
				</h:panelGrid>
				<h:commandButton action="edit" value="Edit Post" />
				<br>
				<h:commandLink action="list" value="Show All Post" />
				<br>
				<h:commandLink action="home" value="Back home" />
			</h:form>
		</f:view>
	</body>
</html>
