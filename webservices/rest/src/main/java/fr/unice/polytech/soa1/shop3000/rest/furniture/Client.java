package fr.unice.polytech.soa1.shop3000.rest.furniture;

public class Client {

	private String name;
	private String adress;
	private String mail;
	private int nbCommande;
	
	public Client(String name, String adress, String mail, int nbCommande) {
	
		this.name = name;
		this.adress = adress;
		this.mail = mail;
		this.nbCommande = nbCommande;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNbCommande() {
		return nbCommande;
	}

	public void setNbCommande(int nbCommande) {
		this.nbCommande = nbCommande;
	}

	public Client(String name, String adress) {
		super();
		this.name = name;
		this.adress = adress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return "name ;;; " + name + ", adress ;;;; " + adress + ", mail ;;;;"
				+ mail + ", numberOfCommande : " + nbCommande;
	}

	
	
	
}
