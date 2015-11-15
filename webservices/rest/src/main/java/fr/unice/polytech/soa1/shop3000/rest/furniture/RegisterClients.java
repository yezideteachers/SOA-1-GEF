package fr.unice.polytech.soa1.shop3000.rest.furniture;

import java.util.Collection;
import java.util.HashMap;



public class RegisterClients{

	
public static HashMap<String, Client> stor = new HashMap<String, Client>();

public static void create(String name,String adress,String mail) {
	stor.put(name, new Client(name, adress, mail, 0));
	
}

public static Client read(String name) {
	return stor.get(name);
	
}

public static void delete(String name) {
	stor.remove(name);
	
}

public static void update(String name,String adress,String mail,int nbCommande) {
	stor.get(name).setAdress(adress);
	stor.get(name).setMail(mail);
	stor.get(name).setNbCommande(nbCommande);
	
}

public static Collection<Client> getAvailableClients() {
	return stor.values();
}

static{
	RegisterClients.create("yezide", "nice","al@gmail.com");
	RegisterClients.create("stephane", "cannes","al@yahoo.fr");
	RegisterClients.create("tintin", "mars","av@haotmail.com");
}	
	
}
