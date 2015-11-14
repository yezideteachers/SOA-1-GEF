package fr.unice.polytech.soa1.cookbook.rest.furniture;

import java.util.Collection;
import java.util.HashMap;

public class RegisterProducts {

	public static HashMap<Integer, Product> col = new HashMap<Integer, Product>();

	public static void create(String name, int quantity, TYPE type, COLOR color,
			double price,WOOD wood) {
			Product pr = new Product(quantity, type, name, color, price, wood);
		col.put(pr.getId(),pr);
		Product.IdAtuIncr+=1;


	}

	public static Product read(int id) {
		return col.get(id);

	}

	public static void delete(int id) {
		col.remove(id);

	}


	public static void update(int id, String name,int quantity , TYPE type, COLOR color , WOOD wood, double price) {
		col.get(id).setType(type.toString());
		col.get(id).setColor(color.toString());
		col.get(id).setDescription(name);
		col.get(id).setWood(wood.toString());
		col.get(id).setQuantity(quantity);
		col.get(id).setPrice(price);
		
		
	}
	
	public static Collection<Product> getAvailableProducts() {
		return col.values();
	}
	
	static
	{
		RegisterProducts.create("chaise", 10,TYPE.CHAIR, COLOR.BLACK, 24.99, WOOD.ACACIA);
		RegisterProducts.create("porte", 20,TYPE.DOOR, COLOR.BLACK, 244.99, WOOD.ACACIA);
		RegisterProducts.create("bureau", 30,TYPE.DESKTOP, COLOR.BLACK, 444.99, WOOD.ACACIA);
		RegisterProducts.create("porte", 23, TYPE.DESKTOP, COLOR.BLUE, 84.99, WOOD.EBONY);
		RegisterProducts.create("placard",12, TYPE.CUPBOARD, COLOR.RED, 254.0, WOOD.OAK);
		
	}

}
