package fr.unice.polytech.soa1.shop3000.rest.shoes;

public enum OrderStatus {
	
	VALIDATION("VALIDÉE"), ENVOI("ENVOYÉE"), RECEPTION("RECETION");
	
	private String orderStatus;
	
	private OrderStatus(String s){
		orderStatus = s;
	}

	public String getStatus(){
		return orderStatus;
	}
	
	public void setStatus(OrderStatus s){
		this.orderStatus = s.getStatus();
	}
}
