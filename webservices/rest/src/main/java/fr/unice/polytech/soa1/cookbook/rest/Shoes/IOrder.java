package fr.unice.polytech.soa1.cookbook.rest.Shoes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({"text/plain"})
public interface IOrder {
	
	@Path("/getOrder")
	@GET
	public Response getOrders();
	
	@Path("/getOrder/{id}")
	@GET
	public Response getOrder(@PathParam("id") int id);
	
	@Path("/deletOrders/{id}/{customerId}")
	@DELETE
	public Response deleteOrders(@PathParam("id") int id, @PathParam("customerId") String customerId);
	
	@Path("/confirmOrder/{id}")
	@PUT
	public Response confirmOrder(@PathParam("id") int id);
	
	@Path("/processOrder/{customeId}")
	@POST
	public Response processOrder(@PathParam("id") String customeId);
	
	@Path("/getShippingID/{id}")
	@GET
	public Response getShippingID(@PathParam("id") int id);
}
