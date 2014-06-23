package com.sun.jersey.samples.helloworld.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// the java class will be hosted at the URI path "/helloworld"


@Path("/helloworld")
public class Helloworld {

	// the java method will process HTTP GET requests
	@GET
	// the java method will produce content identified by the MIME media
	//type text/plain"
	@Produces("text/plain")
	public String getClichedMessage() {
		// return some cliched textual content 
		return "Hell World" ;
	}
}
