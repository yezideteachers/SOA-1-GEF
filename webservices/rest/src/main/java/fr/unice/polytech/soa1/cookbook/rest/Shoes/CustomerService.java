package fr.unice.polytech.soa1.cookbook.rest.Shoes;

import org.json.JSONArray;
import org.json.JSONString;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/customers")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService implements ICustomer{

	public Response createNewCustomer(String name, String f, String e, int p, Adress s, Adress b) {
	    if(Storage.read(name) != null) {
			return Response.status(Response.Status.CONFLICT)
					       .entity("\"Existing name " + name + "\"")
					       .build();
		}
		Storage.create(name, f, e, p, s, b);
		return Response.ok().build();
	}

	
	public Response getAvailableCustomers() {
		Collection<Customer> cust = Storage.findAll();
		JSONArray result = new JSONArray();
		for(Customer c: cust) {
			result.put(c.getName());
		}
		return Response.ok().entity(result.toString(2)).build();
	}

	
	public Response generateIdentifier(String name) {
		if(Storage.read(name) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		String value = Storage.read(name).run();
		return Response.ok().entity("\""+value+"\"").build();
	}

	public Response deleteCustomer(String name) {
		if(Storage.read(name) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Storage.delete(name);
		return Response.ok().build();
	}


	public Response addToCart(String name, String ref) {
		if(Storage.read(name) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Storage.read(name).addProduct(ref);
		return Response.ok().build();
	}


	public Response deleteFromCart(String name, String ref) {
		if(Storage.read(name) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Storage.read(name).deleteProduct(ref);
		return Response.ok().build();
	}
	
	static {
		Storage.create("aaa", "firstname", "email", 0410121416, new Adress(10,"rue lamartine", 05000, "nice", "france"),new Adress(10,"rue lamartine", 05000, "nice", "france"));
	}
	
}