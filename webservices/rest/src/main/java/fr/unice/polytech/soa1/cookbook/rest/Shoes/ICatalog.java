package fr.unice.polytech.soa1.cookbook.rest.Shoes;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({"text/plain"})
public interface ICatalog {

	@Path("/all")
	@GET
	public Response getProducts();

	@Path("/getProduct/{id}")
	@GET
	public Response getProduct(@PathParam("id") String id);
	
	@Path("/filter/{id}")
	@GET
	public Response filter(@PathParam("id") String id);
	
}