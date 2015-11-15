package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import fr.unice.polytech.soa1.shop3000.rest.furniture.*;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;



public class PaymentImpl implements PaymentService {

	
	public Response getAllpay() {
		
		Collection<Payment> listPayments = Payment.mapPayment.values();
		ArrayList<Payment> list = new ArrayList<Payment>();
		
		for(Payment el: listPayments) {
			list.add(el);
		}
		JSONObject result = new JSONObject();
		result.put("Payments", list);
		return Response.ok().entity(result.toString(2)).build();
	}
	
	public Response pay(int idOrder , int idBanking) {
		
		JSONObject result = new JSONObject();
		if(idBanking!=0){
			for (Order el : Order.mapOrder.values()) {
				if(el.getIdOrder()==idOrder){
					Payment.pay(idOrder, (int)(Math.random()*100)+3000);
					return Response.ok().build();
				}
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	public Response printBill(int idOrder) {
		
		
		JSONObject bill = new JSONObject();
		for (Payment elt : Payment.mapPayment.values()) {
			if(elt.getIdOrder()==idOrder){
				for (Order elt2 : Order.mapOrder.values()) {
					if(elt2.getIdOrder()==idOrder){
						bill.put("idBill" ,elt.getIdFacturation());
						bill.put("idPayment" , elt.getIdPayment());
						bill.put("idOrder" , elt2.getIdOrder());
						for (Cart elt3 : Cart.mapCart.values()) {
							if(elt2.getIdCart()==elt3.getIdCart()){
								for (Product eltP : RegisterProducts.col.values()) {
									if(eltP.getId()==elt3.getIdProduct()){
										ArrayList<Product> list = new ArrayList<Product>();
										list.add(eltP);
										bill.put("product " , list);}
								}
							}
						}
						for (Client elC : RegisterClients.stor.values()) {
							if(elC.getName().equals(elt2.getNameClient())){
								ArrayList<Client> list = new ArrayList<Client>();
								list.add(elC);
								
								bill.put("client ", list);
								return Response.ok().entity(bill.toString(2)).build();
							}
						}
					}
				}
			}
		}
		/*si la commande n'existe pas */
		return Response.status(Response.Status.NOT_FOUND).build();
		
	
	}



}
