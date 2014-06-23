<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MyEclipse Reports Demo Project</title>
	</head>
	<body>
		<h1> 
			MyEclipse Reports&trade; Demo Project 
		</h1>
		<div
			style="padding-left: 5px; padding-top: 10px; padding-bottom: 10px; background: #f0f0f0">
			<b><a href="new_report.jsp">Click here</a> to begin testing this
				example</b>
		</div>
		<h2>
			Description
		</h2>
		An example project that shows a simple MyEclipse Reports-created
		report.
		<br>
		<h2>
			Requirements
		</h2>
		<ul>
			<li>

				MyEclipse 6.0+ Pro
			</li>
			<li>
				MyEclipse Reports
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
				MyEclipse Reports&trade; Tutorial - The Basics&nbsp; -
				<a
					href="http://www.myeclipseide.com/documentation/reporting/quickstart/">http://www.myeclipseide.com/documentation/reporting/quickstart/</a>
			</li>
			<li>
				MyEclipse Reports&trade; for BIRT Users -
				<a
					href="http://www.myeclipseide.com/documentation/reporting/reporting_birt/index.html">http://www.myeclipseide.com/documentation/reporting/reporting_birt/index.html</a>
			</li>
			<li>
				MyEclipse Database Server Tutorial&nbsp; -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/derby/">http://www.myeclipseide.com/documentation/quickstarts/derby/</a>
			</li>
			<li>
				MyEclipse Tomcat Server Tutorial -
				<a href="www.myeclipseide.com/documentation/quickstarts/tomcat">www.myeclipseide.com/documentation/quickstarts/tomcat</a>
			</li>
			<li>
				MyEclipse Application Server Deployment documentation -
				<a
					href="http://www.myeclipseide.com/documentation/quickstarts/appservers/#deploying_project">http://www.myeclipseide.com/documentation/quickstarts/appservers/#deploying_project</a>
			</li></ul>
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
