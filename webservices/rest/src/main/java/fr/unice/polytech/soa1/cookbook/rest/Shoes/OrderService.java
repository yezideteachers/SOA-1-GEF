package fr.unice.polytech.soa1.cookbook.rest.Shoes;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import java.util.Collection;
import java.util.HashMap;

@Path("/orders")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)

public class OrderService implements IOrder{
 
	private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	private static HashMap<Integer, String> trackers = new HashMap<Integer, String>();
	
	public Response getOrders() {
		Collection<Order> ords = orders.values();
		JSONArray result = new JSONArray();
		for(Order o: ords) {
			result.put(o);
		}
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response getOrder(int id) {
		Order o = orders.get(id);
		JSONArray result = new JSONArray();
		if(o == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		result.put(o.toString());
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response deleteOrders(int id, String customerId) {
		Order o = orders.get(id);
		if(o == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		if(!o.getCustomerID().equals(customerId)){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		orders.remove(id);
		return Response.ok().build();
		
		
	}

	public Response confirmOrder(int id) {
		Order o = orders.get(id);
		if(o==null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		OrderStatus status = OrderStatus.RECEPTION;
		orders.get(id).setStatus(status);
		return Response.ok().build();
	}


	public Response processOrder(String customerId) {
		if(Storage.read(customerId)==null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Order order = new Order(customerId);
		order.computeAmount();
		orders.put(order.getId(), order);
		JSONArray result = new JSONArray();
		result.put(order);
		return Response.ok().entity(result.toString(2)).build();//Response.ok().build();
	}

	public Response getShippingID(int id) {
		if(trackers.get(id)==null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		JSONArray result = new JSONArray();
		result.put(trackers.get(id));
		return Response.ok().build();
	}
	
	

	
}