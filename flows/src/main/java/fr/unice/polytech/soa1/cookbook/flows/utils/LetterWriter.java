package fr.unice.polytech.soa1.cookbook.flows.utils;


import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;

import java.util.Map;

public class LetterWriter {

	public String write(OrderLine o, BillForm form, String method) {
		StringBuilder b = new StringBuilder();
		/*double tmp = (double) price;
		o.setPrice(tmp);*/
		b.append("Product " + o.getName() + ", \n");
		b.append("market " + o.getIdMarket() + ", \n");
		b.append("\n");
		b.append("  Quantity: " + o.getQuantity() +"\n");
		b.append("  Price: " + o.getPrice() +"\n");
		b.append("  Amount to pay: " + form.getAmount() + "\n");
		b.append("  Bill  using the " + method + " method on " + form.getDate() + "tentative 8" + "\n");
		b.append("\n");

		return b.toString();
	}

	public String write2(String s) {
		StringBuilder b = new StringBuilder();
		//b.append(map.get(0).toString());
		b.append("---\n---");

		b.append("\n");

		return s;
	}

	public String write3(Map<Integer, Object> m){
		String bill ="";
		for (int i = 0; i < m.size(); i++) {
			bill+=m.get(i);
		}
		bill +='\n';
		bill +="\n";
		return bill;
	}
}
