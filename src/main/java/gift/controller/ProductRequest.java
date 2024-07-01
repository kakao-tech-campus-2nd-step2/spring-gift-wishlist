package gift.controller;


import gift.model.Product;

public record ProductRequest(
    String name,
    Integer price,
    String imageUrl
) {

    public Product toEntity() {
        return Product.create(null, name(), price(), imageUrl());
    }

    public Product toEntity(Long id) {
        return Product.create(id, name(), price(), imageUrl());
    }
}
