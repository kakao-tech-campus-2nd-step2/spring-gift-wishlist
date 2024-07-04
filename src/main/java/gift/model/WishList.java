package gift.model;

import java.util.HashMap;

public class WishList {

    private long id;
    private String email;
    private String product_name;
    private long quantity;

    public WishList() {
    }

    public WishList(Long id, String email, String product_name, long quantity) {
        this.id = id;
        this.email = email;
        this.product_name = product_name;
        this.quantity = quantity;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
