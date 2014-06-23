<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Edit Post</title>
	</head>
	<body>
		<f:view>
			<h:messages errorStyle="color: red" infoStyle="color: green"
				layout="table" />
			<h1>
				Edit Post
			</h1>
			<h:form>
				<h:inputHidden value="#{postController.currentPost}"
					immediate="true" />
				<h:panelGrid columns="2">
					<h:outputText value="Id:" />
					<h:outputText value="#{postController.currentPost.id}" title="Id" />
					<h:outputText value="Title:" />
					<h:inputText id="title" value="#{postController.currentPost.title}"
						title="Title" />
					<h:outputText value="Content:" />
					<h:inputTextarea id="content"
						value="#{postController.currentPost.content}" title="Content"
						style="height: 82px; width: 238px" />
					<h:outputText value="Post time:" />
					<h:inputText id="posttime"
						value="#{postController.currentPost.posttime}" title="Post time">
						<f:convertDateTime pattern="MM/dd/yyyy hh:mma" />
					</h:inputText>
				</h:panelGrid>
				<h:commandButton action="#{postController.saveCurrentPost}"
					value="Save Post" />
				<br>
				<h:commandLink action="list" value="Show All Post" />
				<br>
				<h:commandLink action="home" value="Back home" />
			</h:form>
		</f:view>
	</body>
</html>
