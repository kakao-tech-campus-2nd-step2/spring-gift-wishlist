package gift.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Product {
    Long id;
    String name;
    Long price;
    String imageUrl;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
