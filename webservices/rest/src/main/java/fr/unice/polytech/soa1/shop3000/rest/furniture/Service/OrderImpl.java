package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import fr.unice.polytech.soa1.shop3000.rest.furniture.Order;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;



public class OrderImpl implements OrderService {

	public Response getAllOrders() {
		Collection<Order> listorders = Order.mapOrder.values();
		
		ArrayList<Order> list = new ArrayList<Order>();
		for(Order el: listorders) {
			list.add(el);
		}
		JSONObject result = new JSONObject();
		result.put("orders", list);
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response getOrder(int idOrder) {
		JSONObject result = new JSONObject();
		for (Order el : Order.mapOrder.values()) {
			if(el.getIdOrder()==idOrder){
				result.put("Order : ", el.toString());
				break;
			}
		}
		return Response.ok().entity(result.toString(2)).build();
	}
	
	public Response passCommand(int idCart , String cl) {
		
		Order.create(cl, idCart);
		return Response.ok().build();
	}

}
