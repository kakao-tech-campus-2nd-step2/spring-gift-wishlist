package gift.domain;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String name, int price, String imageUrl){
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static Product dtoToEntity(ProductRequestDto productRequestDto) {
        return new Product(productRequestDto.getName(), productRequestDto.getPrice(), productRequestDto.getImageUrl());
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
