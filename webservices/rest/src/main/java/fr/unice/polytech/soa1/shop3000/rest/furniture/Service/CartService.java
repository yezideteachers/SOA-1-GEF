package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/carts")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface CartService {

	@GET
	public Response getAllCommande();
	
	@GET
	@Path("/cart/{idCart}")
	public Response getCart(@PathParam("idCart") int id);
	
	@POST
	@Path("/cart/{idProd}")
	public Response addToCart(@PathParam("idProd") int id);
	
	@DELETE
	@Path("/cart/{idProd}")
	public Response deleteToCart(@PathParam("idProd") int id);
}
