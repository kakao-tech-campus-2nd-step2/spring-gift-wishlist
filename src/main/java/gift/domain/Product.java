package gift.domain;

import gift.annotation.ProductName;
import jakarta.validation.constraints.NotNull;

public class Product {

    @NotNull
    private Long id;
    @ProductName
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
