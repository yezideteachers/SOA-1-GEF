package fr.unice.polytech.soa1.cookbook.rest.Shoes;

import java.util.Collection;
import java.util.HashMap;

public class Storage {

	// this mocks a database.
	private static HashMap<String, Customer> contents = new HashMap<String, Customer>();
	private static HashMap<String, Product> products= new HashMap<String, Product>();

	public static void create(String name, String  firstname, String email, int phone, Adress shipping, Adress billing) {
		contents.put(name, new Customer(name, firstname, email, phone, shipping, billing));
	}

	public static Customer read(String name) {
		return contents.get(name);
	}

	public static void delete(String name) {
		contents.remove(name);
	}

	public static Collection<Customer> findAll() {
		return contents.values();
	}
	public static Collection<Product> findAllProducts() {
		return products.values();
	}


	static {
		Storage.create("demo", "demo", "demo", 06, 
				new Adress(10, "demo", 0, "demo", "demo"), 
				new Adress(10, "demo", 0, "demo", "demo"));
	}

}
