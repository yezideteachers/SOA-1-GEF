package fr.unice.polytech.soa1.cookbook.flows.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class RequestBuilder {

	static ObjectMapper mapper = new ObjectMapper();

	public static Map<String, Object> map = new HashMap<String, Object>();
	public static Map<Integer, Object> facture = new HashMap<Integer, Object>();
	public static ArrayList<Map<String,Object>> mapArray  = new ArrayList<Map<String, Object>>();
	private static double sum=0.0;

	// convert JSON string to Map
	public ArrayList<Map<String, Object>> Json2ListOfMap(String json) {

		String [] s = json.split("\\[");
		String res = "[ " +  s[1].substring(0,s[1].length()-1);

		try {
			mapArray = mapper.readValue(res, new TypeReference<List<Map<String, Object>>>() {});
			//map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapArray;
	}


	public Map<Integer , Object> calculCommand(OrderLine o) {
		String builder = new String();
		double amount = calculAmount(o.getPrice(),o.getQuantity());
		sum+=amount;
		Map<Integer , Object> m = new HashMap<Integer, Object>();
		BillForm b = new BillForm();
		b.setAmount(amount);
		b.setSum(sum);
		m.put(0,o);
		m.put(1,b);
		return m;

	}

	public OrderLine setPricebis(OrderLine o, double price){
		o.setPrice(price);
		return o;
	}

	public Map<String , Object> getListProducts(){
		return map;
	}

	public static ArrayList<Map<String ,  Object>> getFactures(ArrayList<Map<String ,  Object>> facture){
		return facture;
	}

	public Map<Integer, Object> makeBill(Map<Integer,Object> m){

		String builder = new String();
		OrderLine o = (OrderLine) m.get(0);
		BillForm b = (BillForm) m.get(1);
		builder+="quant : "  +  o.getQuantity() + " , ";
		builder+="Price : " + o.getPrice() + " , ";
		builder+="idProd : " + mapArray.get(o.getRefId()).get("id") + " , ";
		builder+="nameProd :" + mapArray.get(o.getRefId()).get("description") + " , ";
		builder+="colorProd : " + mapArray.get(o.getRefId()).get("color") + " , ";
		builder+="amount : " + b.getAmount() + "    \n   ";
		builder+="\n , ";
		builder +="sum = " + b.getSum();
		builder+="\n";
		if(facture.size()>0){
			String s  [] = facture.get(facture.size()-1).toString().split(",");
			facture.remove(facture.size()-1);
			String res="";
			for (int i = 0; i < s.length-1; i++) {
				res += s[i];
			}
			facture.put(facture.size(),res);
		}
		facture.put(facture.size(),builder);
		return facture;
	}

	//calcul amount of each command
	public double calculAmount(double price , int quantity){
		return price * quantity;
	}

	//expose bill
	public String getBill(int i){
		String res = facture.get(i).toString() + i;
		return res;
	}


}
