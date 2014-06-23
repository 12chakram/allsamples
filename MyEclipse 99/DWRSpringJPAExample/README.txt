MyEclipse DWR, Spring and JPA Example Project

* License

This project is provided under the Apache 2 license, please see LICENSE.txt for
details.

* Description

This project is an example application that makes use of the DWR framework
 (Direct Web Remoting), Spring and JPA to display sample information available 
 from the embedded Derby server that ships with MyEclipse 5.5.
 
 This project is laid out in layers that roughly translate to the following:
 	* JPA entities and DAOs that represent the CLASSICCARS schema
 	* Spring beans that wrap access to those DAOs and entities.
 	* DWR that exposes the Spring beans directly to the web tier
 	* A JSP page that uses JavaScript to make calls to those DWR-enabled Spring
 	beans, and updates the "Results" view of the webpage when the results
 	come back from the server.

This project is a great example of a larger application in action because
breakpoints and watch expressions can be set on every layer of the application
(Web, Spring, Persistence) and watch as the application processes a request
and replies.

To help better understand the structure of the CLASSICCARS schema, we have
included an ER Diagram of the schema in the root of the project, named
CLASSICCARS.mer. You can double-click that file to open the ER Diagram of the
schema and get a better idea of how it's structured, table formats, key constraints
and so on.

You can generate your own ER Diagram by opening the MyEclipse Database
Explorer Persecptive and right-clicking on the schema you wish to generate the
ER Diagram for and selecting "New ER Diagram", then selecting where you want
the ER Diagram generated.

* Requirements

	* MyEclipse 5.5, 6.0 or later
	* If MyEclipse 5.5 is used, a configured application server is required as well.

* How to Run

If you are using MyEclipse 6.0 or later , the easiest way to run this project is to
simply right-click on it and go to Debug As > MyEclipse Server Application. The 
MyEclipse Derby DBMS will automatically be started as well as the 
MyEclipse Tomcat 6 Server and the default page will be loaded for you, all 
automatically.

If you are using MyEclipse 5.5, you must start the MyEclipse Derby DBMS manually,
then deploy the project to a configured application server, and then startup
the application server and navigate to the project's path (normally:
http://localhost:8080/DWRSpringJPAExample)

NOTE: If you receive an HTTP ERROR 500 or exception on startup, namely:
	SEVERE: Servlet.service() for servlet jsp threw exception
	java.net.ConnectException: Connection refused: connect

then chances are the MyEclipse Derby server did not automatically start up. Please
switch to the Servers view in the MyEclipse Perspective, and start the MyEclipse
Derby server manually to fix the issue, then relaunch the application.

* Related Links

	* MyEclipse JPA and Spring Tutorial - http://www.myeclipseide.com/documentation/quickstarts/jpaspring/
	* MyEclipse JPA Tutorial - http://www.myeclipseide.com/documentation/quickstarts/jpa/
	* MyEclipse Spring Tutorial - http://www.myeclipseide.com/documentation/quickstarts/springintroduction/
	* DWR Homepage - http://getahead.org/dwr
	* DWR Spring Integration - http://getahead.org/dwr/server/spring

* Feedback

We hope you found this example project helpful. If you ran into any problems 
while working with the example project, please feel free to post to our Example 
Project Forum (http://www.myeclipseide.com/PNphpBB2-viewforum-f-54.html) and 
let us know. Also if you had any suggestions for improvements, noticed something 
wrong or just wanted to ask questions we encourage you to post and let us know!