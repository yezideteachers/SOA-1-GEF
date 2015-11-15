package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface PaymentService {
	
	@GET
	public Response getAllpay();
	
	@POST
	@Path("/payment/{idOrder}/{idBanking}")
	public Response pay(@PathParam("idOrder") int idOrder , @PathParam("idBanking") int idBanking);
	
	@GET
	@Path("/bill/{idPayment}")
	public Response printBill(@PathParam("idPayment") int idOrder);
	

}
