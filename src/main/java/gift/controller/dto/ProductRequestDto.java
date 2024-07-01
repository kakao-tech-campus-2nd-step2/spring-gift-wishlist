package gift.controller.dto;

import gift.model.Product;

public class ProductRequestDto {
    private Long id;
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
