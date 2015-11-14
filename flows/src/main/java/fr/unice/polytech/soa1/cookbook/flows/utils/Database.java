package fr.unice.polytech.soa1.cookbook.flows.utils;



import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public final class Database {

	// Local mock for a database
	public static Map<Integer, BillForm> contents = new HashMap<Integer, BillForm>();


	public void setData(int uid, BillForm f) {
		contents.put(uid, f);
	}

	public BillForm getData(int uuid) {
		if (contents.containsKey(uuid))
			return contents.get(uuid);
		else
			throw new IllegalArgumentException("Unknown uuid: [" + uuid + "]");
	}

	public static void addProduct(OrderLine o , BillForm form, ArrayList<String> l){

		StringBuilder b = new StringBuilder();

		b.append("market " + o.getIdMarket() + ", \n");
		b.append("  Price: " + o.getPrice() +"\n");
		b.append("  Amount to pay: " + form.getAmount() + "\n");
		l.add(0,b.toString());
		l.add(1," debeug ");
	}




	static {
		Database.contents.put(1,new BillForm());
		Database.contents.put(2,new BillForm());
		Database.contents.put(3,new BillForm());
	}

}