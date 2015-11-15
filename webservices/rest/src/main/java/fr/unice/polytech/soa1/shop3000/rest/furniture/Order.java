package fr.unice.polytech.soa1.shop3000.rest.furniture;

import java.util.HashMap;

public class Order {
	private int idOrder ;
	private String nameClient;
	private int idCart;
	public static int id;
	
	public static HashMap<Integer, Order> mapOrder = new HashMap<Integer, Order>();
	
	public Order(String nameClient, int idCart) {
		this.nameClient = nameClient;
		this.idCart = idCart;
		this.idOrder = id;
	}
	public static void create(String client,int idCart){
		mapOrder.put(idCart, new Order(client,idCart));
		
		id+=1;
	}
	
	public int getIdOrder() {
		return idOrder;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}
	
	
	public String toString() {
		return "idOrder : " + idOrder + ", nameClient:" + nameClient
				+ ", idCart : " + idCart;
	}

	static{
		Order.create("yezide", 0);
	
		
	}

	
	
}
