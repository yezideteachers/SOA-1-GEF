package fr.unice.polytech.soa1.cookbook.rest.Shoes;



import org.json.JSONArray;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;

@Path("/shoes")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)

public class CatalogService implements ICatalog{
	
	private static HashMap<String, Product> products = new HashMap<String, Product>();

	public Response getProducts() {
		Collection<Product> prods = products.values();
		JSONArray result = new JSONArray();
		for(Product p: prods) {
			result.put(p);
		}
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response getProduct(String id) {
		Product p = products.get(id);
		JSONArray result = new JSONArray();
		if(p==null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		result.put(p.toString());
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response filter(String id) {
		Collection<Product> prods = products.values();
		JSONArray result = new JSONArray();
		
		for(Product p : prods){
			if(p.getColor().toUpperCase().equals(id.toUpperCase())){
				result.put(p.toString());
			}
		}
		return Response.ok().entity(result.toString(2)).build();
	}
	
	
	public static void Create(){
		products.put("toto", new Product("toto", 10, "titi", 10.5));
		products.put("to", new Product("to", 10, "titi", 10.5));
		products.put("tot", new Product("tot", 10, "titi", 10.5));
	}
	
	public static HashMap<String, Product> getProds(){
		return products;
	}
	
	static {
		products.put("lala", new Product("zozo",39,"green",150));
	}
	
}
