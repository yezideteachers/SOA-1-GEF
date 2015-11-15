package fr.unice.polytech.soa1.shop3000.rest.furniture.Service;

import fr.unice.polytech.soa1.shop3000.rest.furniture.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;



public class ClientImpl implements ClientService{

	public Response registerNewClient(String name,String adress,String mail) {
		/*if(RegisterClients.read("user4").getName() != null) {
			return Response.status(Response.Status.CONFLICT)
					       .entity("\"Existing name " + "user4" + " ; " + "adress1" + "\"")
					       .build();
		}*/
		RegisterClients.create(name, adress, mail);
		return Response.ok().build();
	}
	
	public Response getAvailableClients() {
		
		Collection<Client> clients = RegisterClients.getAvailableClients();
		JSONArray result = new JSONArray();
		ArrayList<Client> list = new ArrayList<Client>();
		for(Client c: clients) {
			list.add(c);
		}
		JSONObject jso = new JSONObject();
		jso.put("list of clients", list);
		return Response.ok().entity(jso).build();
	}

	public Response deleteClient(String name) {
		if(RegisterClients.read(name) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		RegisterClients.delete(name);
		return Response.ok().build();
	}

	public Response updateClient(String name,String adress,String mail) {
		
		RegisterClients.update(name,adress,mail,RegisterClients.read(name).getNbCommande());
		return Response.ok().build();
		
	}

	public Response getClient(String nameClient) {
		if(RegisterClients.read(nameClient) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		
		}
		String name = RegisterClients.read(nameClient).getName();
		String mail = RegisterClients.read(nameClient).getMail();
		String address = RegisterClients.read(nameClient).getAdress();
		int nbCommande = RegisterClients.read(nameClient).getNbCommande();
		JSONObject jo = new JSONObject();
		jo.put("name", name);
		jo.put("mail", mail);
		jo.put("address", address);
		jo.put("nbOfCommande", nbCommande);
		return  Response.ok().entity(jo.toString()).build();
	}

	public Response filterBy(String name,String v) {

		Collection<Client> clients = RegisterClients.getAvailableClients();
		ArrayList<Client> list = new ArrayList<Client>();
		JSONObject result = new JSONObject();
		
			if(name.equals("name")){
				for(Client c: clients) {
					if(v.equals(c.getName())){list.add(c);}
				}
			}
			else if(name.equals("address")){
				for(Client c: clients) {
					if(v.equals(c.getAdress())){list.add(c);}
				}
			}
			else if(name.equals("mail")){
				for(Client c: clients) {
					if(v.equals(c.getMail())){list.add(c);}
				}
			}
			else if(name.equals("nbCommande")){
				for(Client c: clients) {
					if(v.equals(c.getNbCommande())){list.add(c);}
				}
			}
			result.put("clients ", list);
			return Response.ok().entity(result.toString(2)).build();
		}



	
}
