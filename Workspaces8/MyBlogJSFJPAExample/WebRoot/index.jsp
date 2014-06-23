<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MyBlog JSF/JPA CRUD</title>
	</head>
	<body>
		<h1>
			MyBlog JSF/JPA CRUD Example Project
		</h1>
		<div
			style="padding-left: 5px; padding-top: 10px; padding-bottom: 10px; background: #f0f0f0">
			<b><a href="Home.faces">Click here</a> to begin testing this
				example</b>
		</div>
		<h2>
			Description
		</h2>

		MyBlogJSFJPA is a simple web blog application with a JSF web UI and
		JPA
		<br>
		persistence.
		<br>
		<br>
		This project demonstrates full CRUD support for a single blog database
		table.
		<br>
		Also this project is preconfigured to run on the MyEclipse Server
		Sandbox,
		<br>
		i.e., embedded Tomcat 6 and Derby database with a sample schema that
		ships with
		<br>
		MyEclipse 6.0.
		<br>
		&nbsp;
		<br>
		The project architecture consists of:
		<br>
		<ol>
			<li>
				A JSF page for each of the 4 CRUD operations on a Post database
				table
			</li>
			<li>
				A managed bean that serves as a controller for maintaining the UI
				state and managing persistence actions
			</li>
			<li>
				A Post JPA entity and a PostDAO&nbsp; for persistence
			</li>
			<li>
				The MYBLOG schema required by this example is included in the
				MyEclipse Server Sandbox
			</li>
		</ol>

		To help you better understand the structure of the MYBLOG schema, an
		<br>
		entity-relation diagram of the schema is included the database folder
		in this
		<br>
		project.&nbsp; Double-click the file MYBLOG.mer to view an ER Diagram
		of the schema.
		<br>
		You can generate your own ER diagram by opening the MyEclipse Database
		<br>
		Explorer Perspective and right-clicking on the schema you wish to
		generate the
		<br>
		ER Diagram for and selecting &quot;New ER Diagram&quot;, then
		selecting where you want
		<br>
		the ER Diagram generated.
		<br>
		<h2>
			Requirements
		</h2>
		<ul>
			<li>

				MyEclipse 6.0 or later
			</li>
			<li>
				Java SE 5 or greater
			</li>
		</ul>
		<h2>
			How to Run
		</h2>

		You can right-click on this project and go to Debug As or Run As then
		select
		<br>
		&quot;MyEclipse Server Application&quot;. MyEclipse 6.0 and later will
		automatically deploy
		<br>
		the application to the MyEclipse Tomcat Server, then start it up and
		open
		<br>
		a browser window for you to the index.jsp page.
		<br>
		<br>
		NOTE: If you observe an HTTP ERROR 500 or exception on startup,
		namely:
		<br>
		&nbsp;&nbsp; &nbsp;
		<em>SEVERE: Servlet.service() for servlet jsp threw exception <br>
			&nbsp;&nbsp; &nbsp;java.net.ConnectException: Connection refused:
			connect </em>
		<br>
		<br>
		Then chances are the MyEclipse Derby server did not automatically
		start up.
		<br>
		To fix this issue, please switch to the Servers view in the MyEclipse
		<br>
		Perspective, start the MyEclipse Derby server manually and then
		relaunch the
		<br>
		application.
		<br>
		<h2>
			Related Links
		</h2>
		<ul>
			<li>

				MyEclipse JSF Tutorial -
				<a href="http://www.myeclipseide.com/documentation/quickstarts/jsf/">http://www.myeclipseide.com/documentation/quickstarts/jsf/</a>
			</li>
			<li>
				MyEclipse JPA Tutorial -
				<a href="http://www.myeclipseide.com/documentation/quickstarts/jpa/">http://www.myeclipseide.com/documentation/quickstarts/jpa/</a>
			</li>
			<li>
				MyEclipse JSF Designer Tutorial -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/jsfdesigner/">http://www.myeclipseide.com/documentation/quickstarts/jsfdesigner/</a>
			</li>
		</ul>
		<h2>
			Feedback
		</h2>

		We hope you found this example project helpful. If you ran into any
		problems
		<br>
		while working with the example project, please feel free to post to
		our Example
		<br>
		Project Forum (
		<a href="http://www.myeclipseide.com/PNphpBB2-viewforum-f-54.html">http://www.myeclipseide.com/PNphpBB2-viewforum-f-54.html</a>)
		and
		<br>
		let us know. Also if you had any suggestions for improvements, noticed
		something
		<br>
		wrong or just wanted to ask questions we encourage you to post and let
		us know!
	</body>
</html>
