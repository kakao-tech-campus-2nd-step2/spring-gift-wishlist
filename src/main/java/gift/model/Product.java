package gift.model;

import gift.dto.ProductRequest;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Product(String name, Integer price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, String name, Integer price, String imageUrl) {
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

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void updateFrom(ProductRequest productRequest){
        this.name = productRequest.name();
        this.price = productRequest.price();
        this.imageUrl = productRequest.imageUrl();
    }

    public static Product from(ProductRequest productRequest) {
        return new Product(productRequest.name(), productRequest.price(), productRequest.imageUrl());
    }
}
