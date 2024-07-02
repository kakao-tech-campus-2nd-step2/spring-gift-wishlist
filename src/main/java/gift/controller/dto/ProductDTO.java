package gift.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotEmpty(message = "Product name cannot be empty")
    @Pattern(
        regexp = "^[a-zA-Z0-9 ()\\[\\]+\\-&/_]{1,15}$",
        message = "Product name must be 1-15 characters long and can only include special characters: ( ), [ ], +, -, &, /, _"
    )
    private String name;
    private double price;

    private String imageUrl;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
