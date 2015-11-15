package fr.unice.polytech.soa1.shop3000.flows.business;

import java.io.Serializable;

/**
 * Created by yazide on 28/10/2015.
 */
public class OrderLine implements Serializable{
    private int idClient;
    private int idMarket;
    private String ref;
    private int refId;
    private String name;
    private String color;
    private double price;
    private int quantity;

   /* public OrderLine(String ref, String name, String color, double price, int quantity) {
        this.ref = ref;
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }
    public OrderLine(){}
*/

    public int getIdClient() {
        return idClient;
    }

    public int getIdMarket() {
        return idMarket;
    }

    public void setIdClient(int idClient){
        this.idClient = idClient;
    }

    public void setIdMarket(int idMarket) {
        this.idMarket = idMarket;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
