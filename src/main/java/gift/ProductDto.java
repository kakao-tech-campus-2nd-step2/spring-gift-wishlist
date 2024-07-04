package gift;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private Long id;


    private String name;

    private double price;

    private String imageUrl;

    public ProductDto(Long id, String name, double price, String imageUrl) {
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

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
