package fr.unice.polytech.soa1.shop3000.rest.shoes;

import java.util.ArrayList;

public class Cart {
	
	private static int id=0;
	
	private ArrayList<String> products = new ArrayList<String>();
	
	Cart(){
		id++;
	}

	public void add(String ref){
		products.add(ref);
	}
	
	public void remove(String ref){
		products.remove(ref);
	}
	
	public ArrayList<String> getListOfProducts(){
		return products;
	}
	
	public int getID(){
		return id;
	}
}
