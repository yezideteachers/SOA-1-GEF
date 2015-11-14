package fr.unice.polytech.soa1.cookbook.flows.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * This file is part of the system project
 *
 * @author mosser (19/10/2015, 12:28)
 **/
public class RequestBuilder {

	static ObjectMapper mapper = new ObjectMapper();

	public static Map<String, Object> map = new HashMap<String, Object>();
	public static Map<Integer, Object> facture = new HashMap<Integer, Object>();

	// convert JSON string to Map
	public Map<String, Object> method1(String json) {

		try {
			map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public String buildSimpleRequest(OrderLine o, String uuid) {
		StringBuilder builder = new StringBuilder();
		builder.append("<cook:command xmlns:cook=\"http://cookbook.soa1.polytech.unice.fr/\">\n");
		builder.append("  <commandInfo>\n");
		builder.append("    <id>"     + uuid          + "</id>\n");
		builder.append("    <quant>" + o.getQuantity() + "</quant>\n");
		builder.append("    <income>" + o.getPrice() + "</income>\n");
		builder.append("  </commandInfo>\n");
		builder.append("</cook:command>");
		return builder.toString();
	}

	public String calculCommand(OrderLine o) {
		StringBuilder builder = new StringBuilder();
		builder.append("    <quant>" + o.getQuantity() + "</quant>\n");
		builder.append("    <income>" + o.getPrice() + "</income>\n");
		builder.append("    <id>" + map.get("id") + "</map>\n");
		builder.append("    <name>" + map.get("description") + "</name>\n");
		builder.append("    <color>" + map.get("color") + "</color>\n");
		return builder.toString();
	}

	public OrderLine setPricebis(OrderLine o, double price){
		o.setPrice(price);
		return o;
	}

	public Map<String , Object> getListProducts(){
		return map;
	}

	public Map<Integer, Object> makeBill(String s){
		facture.put(0,s);
		return facture;
	}


}
