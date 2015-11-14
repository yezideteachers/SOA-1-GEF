package fr.unice.polytech.soa1.cookbook.rest.furniture;

public class Product {

	int id=0;
	public static int IdAtuIncr=0;
	
	// Fields
	int quantity;
	String type;
	String description;
	String color;
	double price;
	String wood;
	
	// Constructors

	public Product(){

	}

	public Product(int quantity, TYPE type, String name,
			COLOR color, double price, WOOD wood) {
		this.id=IdAtuIncr;
		this.quantity = quantity;
		this.type = type.toString();
		this.description = name;
		this.color = color.toString();
		this.price = price;
		this.wood=wood.toString();
		
	}
	
	// Getters & Setters
	public int getQuantity() {
		return quantity;
	}
	public int getId(){return id;}
	
	public void setQuantity(int quatity) {
		this.quantity = quatity;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getWood() {
		return wood;
	}

	public void setWood(String wood) {
		this.wood = wood;
	}

	public void setDescription(String name) {
		this.description = name;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// toString
	@Override
	public String toString() {
		return "id : " + id + ", description : " + description + "quantity : " + quantity
				+ ", type : " + type  + ", color :" + color
				+ ", wood : " + wood + ", price : " + price ;
	}
}
