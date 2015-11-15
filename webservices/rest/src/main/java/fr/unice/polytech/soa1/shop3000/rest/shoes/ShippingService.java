package fr.unice.polytech.soa1.shop3000.rest.shoes;

import java.util.HashMap;
import org.json.JSONArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tracking")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public class ShippingService implements IShipping{
	
	private static HashMap<String, String> trackers = new HashMap<String, String>();
	
	public Response track(String id){
		if(trackers.get(id)==null){
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		JSONArray result = new JSONArray();
		result.put(trackers.get(id));
		return Response.ok().entity(result.toString(2)).build();//Response.ok().build();
	}
	
	static {
		trackers.put("12LK", "en cours d'expédition");
	}
}