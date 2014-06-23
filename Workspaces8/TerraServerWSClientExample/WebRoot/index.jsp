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
			TerraServer Web Service Client Example Project
		</h1>
		<div
			style="padding-left: 5px; padding-top: 10px; padding-bottom: 10px; background: #f0f0f0">
			<b><a href="TerraServer.jsp">Click here</a> to begin testing this
				example</b>
		</div>
		<h2>
			Description
		</h2>
		TerraServerWSClientExample is an example web application that
		demonstrates interfacing with the
		<br>
		<a href="http://terraserver.microsoft.com/webservices.aspx">Microsoft
			TerraServer imaging web services</a>. A user submits a postal address
		through a web form and the application converts it to a longtitude and
		latitude coordinate pair using the getTileMetaFromLonLatPt web
		service.
		<br>
		<br>
		This project demonstrates interfacing with a public web service.&nbsp;
		To simplify operation this project is preconfigured to run with the
		1-Click application launcher on the MyEclipse Server Sandbox,&nbsp;
		i.e., embedded Tomcat 6 servlet container that ships with MyEclipse 6.
		<br>
		&nbsp;
		<br>
		The project architecture consists of:
		<br>
		<ol>
			<li>
				A&nbsp; JSP UI form for collecting the user's address details and
				then displaying the resulting long-lat coordinate
				<br>
			</li>
			<li>

				The web service client API is autogenerated using the MyEclipse 6
				web services client wizard
				<br>
			</li>
			<li>
				All&nbsp; web service invocation and result processing is performed
				within the main jsp page
			</li>
		</ol>
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
		Right-click on this project and select either Debug As or Run As. Then
		select &quot;MyEclipse Server Application&quot;. MyEclipse 6 will
		automatically deploy the application to the MyEclipse Tomcat Server,
		and then start the server and open a browser window to the index.jsp
		page.
		<br>
		<br>
		<h2>
			Related Links
		</h2>
		<ul>
			<li>
				Microsoft TerraServer -
				<a href="http://terraserver.microsoft.com">http://terraserver.microsoft.com</a>
			</li>
			<li>
				MyEclipse Web Service Client Tutorial - &nbsp;
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/webservicesclient/ ">http://www.myeclipseide.com/documentation/quickstarts/webservicesclient/</a>
			</li>
			<li>
				MyEclipse Code-First Web Services Tutorial -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/webservices/">http://www.myeclipseide.com/documentation/quickstarts/webservices/</a>
			</li>
		</ul>

		<ul></ul>
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