<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MyBlog JSF/JPA CRUD</title>
	</head>
	<body>
		<h1>
			MyEclipse Google Maps Example Project
		</h1>
		<div
			style="padding-left: 5px; padding-top: 10px; padding-bottom: 10px; background: #f0f0f0">
			<%-- The request.getLocalPort() call is used below incase the user 
			has changed the default MyEclipse Tomcat port to something else. And
			because of how the Google API Keys work (domain-specific), we have to
			load http://localhost:<PORT> and cannot use the default domain name 
			that the launcher will use by default. --%>
			<b><a href="http://localhost:<%= request.getLocalPort() %>/GoogleMapsExample/Google.jsp">Click here</a> to begin testing this
				example<strong></strong><font size="-1"></font>
			</b>
		</div>
		<h2>
			Description
		</h2>
		This is a simple example application that uses the Google Maps
		JavaScript API.
		<br>
		This example application uses an embedded Google Map widget on the
		page to
		<br>
		display the location (and longitude and latitude) of the address typed
		in by
		<br>
		the user.
		<br>
		<br>
		It is possible to set breakpoints inside the /resources/map.js file
		and watch
		<br>
		how the Google JavaScript APIs work by stepping into them and
		inspecting their
		<br>
		runtime values using the advanced JavaScript tooling in MyEclipse.
		<br>
		<h2>
			Requirements
		</h2>
		<ul>
			<li>
				MyEclipse 6.0 or a previous version of MyEclipse with an application
				server that the project can be deployed to and run.
			</li>
			<li>
				MyEclipse 6.0 provides enhanced JavaScript tooling that wasn't
				available in previous versions, but the project should still run
				with previous versions of MyEclipse.
			</li>
		</ul>
		<h2>
			How to Run
		</h2>
		The easiest way to Run this project (Because it's JavaScript based,
		and runs
		<br>
		inside the browser) is to simply open the JSP page in the MyEclipse
		Visual JSP
		<br>
		Designer, and switch to the Preview tab.
		<br>
		<br>
		Alternatively you can deploy the project to the MyEclipse Tomcat 6
		Server and
		<br>
		then run it using the URL http://localhost:8080/GoogleMapsExample
		<br><br><strong>NOTE</strong>: <em>If you have changed the default Tomcat port from 8080 to something else, be sure to<br>change the URL to the correct port.<br></em>
		<h2>
			Related Links
		</h2>
		<ul>
			<li>
				MyEclipse Google Maps Demonstration -
				<a
					href="http://www.myeclipseide.com/images/tutorials/demos/myeclipse_google_maps_demo/myeclipse_google_maps_demo.htm">http://www.myeclipseide.com/images/tutorials/demos/myeclipse_google_maps_demo/myeclipse_google_maps_demo.htm</a>
			</li>
			<li>
				MyEclipse AJAX Workbench Overview -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/web20overview/">http://www.myeclipseide.com/documentation/quickstarts/web20overview/</a>
			</li>
			<li>
				MyEclipse JavaScript Development and Debugging Tutorial -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/javascript/">http://www.myeclipseide.com/documentation/quickstarts/javascript/</a>
			</li>
			<li>
				MyEclipse AJAX Debugging Tutorial -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/ajaxtutorial/ ">http://www.myeclipseide.com/documentation/quickstarts/ajaxtutorial/
				</a>
				<br>
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
