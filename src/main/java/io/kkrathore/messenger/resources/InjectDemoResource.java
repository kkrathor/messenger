package io.kkrathore.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("/annotation")
	public String test(@MatrixParam("param") String matrixParam, 
			           @HeaderParam("header") String header,
			           @CookieParam("JSESSIONID") String cookieValue) {
		System.out.println("Test");
		return "Test " + " \nMatrix Param  " + matrixParam + "\nHeader " + header + "\nCookieValue" + cookieValue;
	}
}
