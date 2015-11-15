package fr.unice.polytech.soa1.shop3000.rest.furniture;

import java.util.HashMap;

public class Payment {
	
	int idPayment;
	int idFacturation;
	int idOrder;
	static int id=0;
	
	public static HashMap<Integer, Payment> mapPayment = new HashMap<Integer, Payment>();

	public Payment(int idOrder , int idFacturation) {
		this.idPayment = id;
		this.idOrder=idOrder;
		this.idFacturation = idFacturation;
	}
	
	public static void pay(int idOrder,int idFact){
		mapPayment.put(id, new Payment(idOrder, idFact));
		id+=1;
	}

	public int getIdFacturation() {
		return idFacturation;
	}

	
	

	public void setIdFacturation(int idFacturation) {
		this.idFacturation = idFacturation;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdPayment() {
		return idPayment;
	}
	
	public String toString() {
		return "idPayment=" + idPayment + ", idFacturation="
				+ idFacturation + ", idOrder=" + idOrder;
	}
	
	static{
		
		Payment.pay(0, (int) (Math.random()*100) + 300);
	}
	
	
}
