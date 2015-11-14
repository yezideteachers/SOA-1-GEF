package fr.unice.polytech.soa1.cookbook.flows.business;

import java.util.Map;

/**
 * Created by yazide on 09/11/2015.
 */
public class MockProduct {
    int id;
    String name;
    double price;
    static Map<Integer,MockProduct> map = null;

    public MockProduct() {
    }

    public MockProduct(int id, String name,double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean itExists(int id){
        for (int i = 0; i < map.size(); i++) {
            if(map.get(i).id == id){
                return true;
            }
        }
        return false;
    }

    static{
        map.put(0,new MockProduct(0,"prod0",22.5));
        map.put(1,new MockProduct(1,"prod1",22.6));
        map.put(2,new MockProduct(2,"prod2",22.7));
    }
}



