package fr.unice.polytech.soa1.shop3000.rest.shoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order {
	
	@Override
	public String toString() {
		return "{customerID=" + customerID + ", date=" + date + ", status=" + status + ", amount=" + amount + "}";
	}
	private static int id = 0;
	private String customerID;
	private String date;
	OrderStatus status;
	double amount;
	
	
	public Order(String id_){
		id++;
		this.customerID = id_;
		status = OrderStatus.VALIDATION;
		Date tmp = new Date();
		date =  tmp.toString();
		this.amount = 0;
	}
	
	public void computeAmount(){
		HashMap<String, Product> prods = CatalogService.getProds();
		ArrayList<String> pan = Storage.read(customerID).getCart().getListOfProducts();
		for(int i=0; i<pan.size(); i++){
			if(prods.get(pan.get(i))!=null){
				amount+=prods.get(pan.get(i)).getPrice();
			}
		}
		
	}
	
	public int getId(){
		return id;
	}
	
	public String getCustomerID(){
		return customerID;
	}
	
	public String getOrderDate(){
		return date;
	}
	
	public OrderStatus getStatus(){
		return status;
	}
	public void setStatus(OrderStatus s){
		status = s;
	}
}
