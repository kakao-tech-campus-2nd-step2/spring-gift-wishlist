package gift.controller.dto;

import gift.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {
    private Long id;

    @NotNull
    @Size(min=1, max=15, message="상품 이름은 공백을 포함하여 최대 15자입니다.")
    private String name;
    private int price;
    private String imageUrl;

    public ProductRequestDto(){}

    public Product toEntity(){
        return new Product(
            id,
            name,
            price,
            imageUrl
        );
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
