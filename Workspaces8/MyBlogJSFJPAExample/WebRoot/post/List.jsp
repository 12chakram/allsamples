<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Post List</title>
	</head>
	<body>
		<f:view>
			<h:messages errorStyle="color: red" infoStyle="color: green"
				layout="table" />
			<h1>
				Post List
			</h1>
			<h:form>
				<h:commandLink action="#{postController.createPost}"
					value="New Post" />
				<h:dataTable value="#{postController.postsDataModel}" var="item"
					border="1" cellpadding="2" cellspacing="0">
					<h:column>
						<f:facet name="header">
							<h:outputText value="Id" />
						</f:facet>
						<h:commandLink action="#{postController.showCurrentPostDetails}"
							value="#{item.id}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Title" />
						</f:facet>
						<h:outputText value="#{item.title}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Content" />
						</f:facet>
						<h:outputText value="#{item.content}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Post time" />
						</f:facet>
						<h:outputText value="#{item.posttime}">
							<f:convertDateTime pattern="MM/dd/yyyy hh:mma" />
						</h:outputText>
					</h:column>
					<h:column>
						<h:commandLink value="Edit"
							action="#{postController.editCurrentPost}">
							<f:param name="id" value="#{item.id}" />
						</h:commandLink>
						<h:outputText value=" " />
						<h:commandLink value="Delete"
							action="#{postController.deleteCurrentPost}">
							<f:param name="id" value="#{item.id}" />
						</h:commandLink>
					</h:column>
				</h:dataTable>
				<h:commandLink action="home" value="Back home" />
			</h:form>
		</f:view>
	</body>
</html>
