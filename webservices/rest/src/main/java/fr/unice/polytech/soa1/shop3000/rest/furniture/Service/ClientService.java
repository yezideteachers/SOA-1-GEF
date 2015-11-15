package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clients")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface ClientService {

	
	
	@POST
	@Path("/client/{name}/{adress}/{mail}")
	public Response registerNewClient(@PathParam("name") String name,@PathParam("adress") String adress,
			@PathParam("mail") String mail);
	
	@GET
	@Produces("application/json")
	public Response getAvailableClients() ;
	
	
	@DELETE
	@Path("/client/{name}")
	public Response deleteClient(@PathParam("name") String name);
	
	@PUT
	@Path("/client/{name}/{address}/{mail}")
	public Response updateClient(@PathParam("name") String name,@PathParam("address") String adress,
					@PathParam("mail") String mail);
	
	@GET
	@Path("/client/{name}")
	public Response getClient(@PathParam("name")  String name);
	

	
	@GET
	@Path("/client/filter/{name}/{value}")
	public Response filterBy(@PathParam("name") String name, @PathParam("value") String v);
	
	
}
