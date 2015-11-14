package fr.unice.polytech.soa1.cookbook.rest.furniture.Service;

import fr.unice.polytech.soa1.cookbook.rest.furniture.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

public class ProductImpl implements ProductService {

	public Response createNewProduct(String description, int quantity, TYPE type,
			COLOR color, double price, WOOD wood) {
		/*if(RegisterProducts.read(name).getDescription() != null) {
			return Response.status(Response.Status.CONFLICT)
					       .entity("\"Existing name " + name + "\"")
					       .build();
		}*/
		RegisterProducts.create(description, quantity, type, color, price, wood);
		return Response.ok().build();
	}

	public Response getAvailableProducts() {
		Collection<Product> products = RegisterProducts.getAvailableProducts();
		JSONArray temp = new JSONArray();
		ArrayList<Product> list = new ArrayList<Product>();
		for(Product c: products) {
			temp.put(c.toString());
			list.add(c);
		}
		
		JSONObject result = new JSONObject();
		
		result.put("Products", list);
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response deleteProduct(int id) {
		if(RegisterProducts.read(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		RegisterProducts.delete(id);
		return Response.ok().build();
	}

	

	public Response getProduct(int id) {
		if(RegisterProducts.read(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		JSONObject jo = new JSONObject();
		jo.put("id", RegisterProducts.read(id).getId());
		jo.put("description", RegisterProducts.read(id).getDescription());
		jo.put("quantity", RegisterProducts.read(id).getQuantity());
		jo.put("price", RegisterProducts.read(id).getPrice());
		jo.put("color", RegisterProducts.read(id).getColor());
		jo.put("type", RegisterProducts.read(id).getType());
		jo.put("wood", RegisterProducts.read(id).getWood());
		
		return Response.ok().entity(jo.toString()).build();
	}

	public Response getProductName(int id) {
		if(RegisterProducts.read(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		JSONObject jo = new JSONObject();
		//jo.put("id", RegisterProducts.read(name).getId());
		jo.put("description", RegisterProducts.read(id).getDescription());
		/*jo.put("quantity", RegisterProducts.read(id).getQuantity());
		jo.put("color", RegisterProducts.read(id).getColor());
		jo.put("type", RegisterProducts.read(id).getType());
		jo.put("wood", RegisterProducts.read(id).getWood());*/

		return Response.ok().entity(jo.get("description")).build();
	}


	public Response filterBy(String name,String v) {
		Collection<Product> products = RegisterProducts.getAvailableProducts();
		ArrayList<Product> list = new ArrayList<Product>();
		
		JSONObject result = new JSONObject();
		if(name.equals("color")){
			for(Product c: products) {
				if(v.equals(c.getColor())){list.add(c);}
			}
		}
		else if(name.equals("type")){
			for(Product c: products) {
				if(v.equals(c.getType())){list.add(c);}
			}
		}
		else if(name.equals("wood")){
			for(Product c: products) {
				if(v.equals(c.getWood())){list.add(c);}
			}
		}
		else if(name.equals("price")){
			for(Product c: products) {
				if(v.equals(c.getPrice())){list.add(c);}
			}
		}
		
		else if(name.equals("quantity")){
			for(Product c: products) {
				if(v.equals(c.getQuantity())){list.add(c);}
			}
		}
		result.put(new String("filter products by"+ name), list);
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response updateProduct(String descr, int quant, TYPE type,
			COLOR color, double price, WOOD wood) {
		for (Product el : RegisterProducts.col.values()) {
			if(el.getDescription().equals(descr)){
				RegisterProducts.update(el.getId(), descr, quant, type, color, wood, price);
			}
		}
		return Response.ok().build();
	}

	


	
}
