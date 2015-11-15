package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface OrderService {

	@GET
	public Response getAllOrders();
	
	@GET
	@Path("/order/{idOrder}")
	public Response getOrder(@PathParam("idOrder") int id);
	
	@POST
	@Path("/order/{idCart}/{client}")
	public Response passCommand(@PathParam("idCart") int id , @PathParam("client") String cl);
}
