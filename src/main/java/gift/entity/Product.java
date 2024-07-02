package gift.entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {
    public final Long id;

    @NotNull(message = "Product name cannot be null")
    @Size(max = 15, message = "Product name must be at most 15 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]+\\-&/_]*$", message = "Invalid characters in product name")
    public final String name;

    public final int price;
    public final String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
