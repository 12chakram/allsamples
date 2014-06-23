 MyBlog JSF/JPA CRUD Example Project

* License

This project is provided under the Apache 2 license, please see LICENSE.txt for
details.

* Description

MyBlogJSFJPA is a simple web blog application with a JSF web UI and JPA 
persistence. 

This project demonstrates full CRUD support for a single blog database table. 
Also this project is preconfigured to run on the MyEclipse Server Sandbox, 
i.e., embedded Tomcat 6 and Derby database with a sample schema that ships with 
MyEclipse 6.0.
 
The project architecture consists of:

 	1. A JSF page for each of the 4 CRUD operations on a Post database table
 	2. A managed bean that serves as a controller for maintaining the UI state 
   	and managing persistence actions
 	3. A Post JPA entity and a PostDAO  for persistence
	4. The MYBLOG schema required by this example is included in the 
	 MyEclipse Server Sandbox

To help you better understand the structure of the MYBLOG schema, an 
entity-relation diagram of the schema is included the database folder in this
project.  Double-click the file MYBLOG.mer to view an ER Diagram of the schema. 
You can generate your own ER diagram by opening the MyEclipse Database 
Explorer Perspective and right-clicking on the schema you wish to generate the 
ER Diagram for and selecting "New ER Diagram", then selecting where you want 
the ER Diagram generated.

* Requirements

	* MyEclipse 6.0 or later
	* Java SE 5 or greater

* How to Run

You can right-click on this project and go to Debug As or Run As then select
"MyEclipse Server Application". MyEclipse 6.0 and later will automatically deploy
the application to the MyEclipse Tomcat Server, then start it up and open
a browser window for you to the index.jsp page.

NOTE: If you observe an HTTP ERROR 500 or exception on startup, namely:
	SEVERE: Servlet.service() for servlet jsp threw exception
	java.net.ConnectException: Connection refused: connect

Then chances are the MyEclipse Derby server did not automatically start up. 
To fix this issue, please switch to the Servers view in the MyEclipse 
Perspective, start the MyEclipse Derby server manually and then relaunch the 
application.
	
* Related Links

	* MyEclipse JSF Tutorial - http://www.myeclipseide.com/documentation/quickstarts/jsf/
	* MyEclipse JPA Tutorial - http://www.myeclipseide.com/documentation/quickstarts/jpa/
	* MyEclipse JSF Designer Tutorial - http://www.myeclipseide.com/documentation/quickstarts/jsfdesigner/

* Feedback

We hope you found this example project helpful. If you ran into any problems 
while working with the example project, please feel free to post to our Example 
Project Forum (http://www.myeclipseide.com/PNphpBB2-viewforum-f-54.html) and 
let us know. Also if you had any suggestions for improvements, noticed something 
wrong or just wanted to ask questions we encourage you to post and let us know!