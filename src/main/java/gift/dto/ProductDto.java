package gift.dto;

import gift.domain.Product;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductDto {

    private long id;

    @Size(max = 15, message = "Name is too long!")
    @Pattern(regexp = "^[a-zA-Z0-9 ()\\[\\]+\\-\\&\\/\\_가-힣]*$", message = "Name has invalid character")
    private String name;
    private int price;
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product toEntity() {
        return new Product(this.id, this.name, this.price, this.imageUrl);
    }
}
