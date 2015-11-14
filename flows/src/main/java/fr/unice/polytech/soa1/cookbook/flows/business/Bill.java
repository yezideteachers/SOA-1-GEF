package fr.unice.polytech.soa1.cookbook.flows.business;

/**
 * Created by yazide on 28/10/2015.
 */
public class Bill {
    private String name,adresse;
    private double price;

    public Bill(String name, String adresse, double price) {
        this.name = name;
        this.adresse = adresse;
        this.price = price;
    }

    public Bill(){}
    @Override
    public String toString() {
        return "Bill{" +
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
