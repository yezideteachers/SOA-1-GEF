package fr.unice.polytech.soa1.cookbook.flows.utils;


import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Database {

	// Local mock for a database
	public static Map<Integer, List<BillForm>> contents = new HashMap<Integer, List<BillForm>>();


	public void setData(int uid, Map<Integer,Object> m ) {
		BillForm b = (BillForm) m.get(1);
		List<BillForm> l = new ArrayList<BillForm>();
		if(contents.size()<=0){
			l.add(b);
			contents.put(uid, l);
		}
		else {
			l = contents.get(uid);
			l.add(b);
			contents.put(uid, l);
		}

	}

	public String getData(int uid) {
		if (contents.containsKey(uid)) {
			String list = "Amounts : ";
			for (int i = 0; i < contents.get(uid).size(); i++) {
				list +=contents.get(uid).get(i).getAmount() + " , ";
			}
			list+="\n";
			list+="sum : " + contents.get(uid).get(contents.get(uid).size()-1).getSum();
			return list;
		}
		else
			throw new IllegalArgumentException("Unknown uuid: [" + uid + "]");
	}

	public static void addProduct(OrderLine o , BillForm form, ArrayList<String> l){

		StringBuilder b = new StringBuilder();

		b.append("market " + o.getIdMarket() + ", \n");
		b.append("  Price: " + o.getPrice() +"\n");
		b.append("  Amount to pay: " + form.getAmount() + "\n");
		l.add(0,b.toString());
		l.add(1," debeug ");
	}




	/*static {
		/*BillForm b0 = new BillForm();
		b0.setAmount(33.2);b0.setDate("erer");b0.setSum(99993.32);
		Database.contents.put(0,b0);*/
		/*Database.contents.put(1,new BillForm());
		Database.contents.put(3,new BillForm());*/
	//}

}