package fr.unice.polytech.soa1.cookbook.rest.Shoes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({"text/plain"})
public interface ICustomer {

	@Path("/create/{name}/{firstname}/{email}/{phone}/{shipping}/{billing}")
	@POST
	public Response createNewCustomer(@PathParam("name") String name,
									  @PathParam("firstname") String firstname,
									  @PathParam("email") String email,
									  @PathParam("phone") int phone,
									  @PathParam("shipping") Adress shipping,
									  @PathParam("billing") Adress billing);
	
	@Path("/getAvailableCustomers")
	@GET
	public Response getAvailableCustomers();
	
	@Path("/generateIdentifier/{name}")
	@GET
	public Response generateIdentifier(@PathParam("name") String name);
	
	@Path("/deleteCustomer/{name}")
	@DELETE
	public Response deleteCustomer(@PathParam("name") String name);
	
	@Path("/addToCart/{name}/{ref}")
	@POST
	public Response addToCart(@PathParam("name") String name, @PathParam("ref") String ref);
	
	@Path("/deleteFromCart/{name}/{ref}")
	@DELETE
	public Response deleteFromCart(@PathParam("name") String name, @PathParam("ref") String ref);
}
