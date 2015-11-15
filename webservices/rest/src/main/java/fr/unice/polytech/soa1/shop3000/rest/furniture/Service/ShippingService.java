package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/shippings")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public class ShippingService {
	
	boolean isdelivered=false;
	
	@PUT
	@Path("/shipping/idOrder/client")
	public Response setStatus(@PathParam("idOrder") int id, @PathParam("client") String client){
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/shipping/name")
	public Response getStatus(@PathParam("name") String client){
		return Response.ok().build();
	}
	
	
}
