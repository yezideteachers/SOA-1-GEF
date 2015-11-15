package fr.unice.polytech.soa1.shop3000.rest.shoes;

public class Customer {
	
	private static int id=0;
	private String name;
	private String firstname;
	private String email;
	private int phone;
	private Adress shipping;
	private Adress billing;
	
	private Cart cart;
	
	public Customer(String name, String  firstname, String email, int phone, Adress shipping, Adress billing) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.phone = phone;
		this.shipping = shipping;
		this.billing = billing;
		this.cart = new Cart();
	}

	public String run(){
		id++;
		return name+id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public String getEmail(){
		return email;
	}
	
	public int getPhone(){
		return phone; 
	}
	
	public Adress getShippingAdress(){
		return shipping;
	}
	
	public Adress getBillingAdress(){
		return billing;
	}
	
	public void addProduct(String ref){
		cart.add(ref);
	}
	
	public void deleteProduct(String ref){
		cart.remove(ref);
	}
	
	public Cart getCart(){
		return this.cart;
	}
	
}
