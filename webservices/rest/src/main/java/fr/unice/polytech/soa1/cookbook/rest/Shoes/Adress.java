package fr.unice.polytech.soa1.cookbook.rest.Shoes;

public class Adress {
	
	
	private int no;
	private String street;
	private int zipcode;
	private String city;
	private String country;

	public Adress(int no, String s, int z, String ct, String cr) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.street = s;
		this.zipcode = z;
		this.city = ct;
		this.country = cr;
	}
	
	public void setAdress(Adress adr){
		this.no = adr.no;
		this.street = adr.street;
		this.zipcode = adr.zipcode;
		this.city = adr.city;
		this.country = adr.country;
	}
	
	@Override
	public String toString() {
		return "{no=" + no + ", street=" + street + ", zipcode=" + zipcode + ", city=" + city + ", country="
				+ country + "}";
	}

}
