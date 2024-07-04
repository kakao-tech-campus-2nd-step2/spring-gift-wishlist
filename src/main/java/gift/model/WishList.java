package gift.model;

import java.util.HashMap;

public class WishList {

    private long id;
    private String email;
    private HashMap<String, Integer> products;

    public WishList() {
    }

    public WishList(Long id, String email, HashMap<String, Integer> products) {
        this.id = id;
        this.email = email;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
}
