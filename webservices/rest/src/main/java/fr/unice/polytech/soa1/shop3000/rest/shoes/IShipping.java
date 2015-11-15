package fr.unice.polytech.soa1.shop3000.rest.shoes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({"text/plain"})
public interface IShipping {
	
	@Path("/track/{id}")
	@GET
	public Response track(@PathParam("id") String id);

}
