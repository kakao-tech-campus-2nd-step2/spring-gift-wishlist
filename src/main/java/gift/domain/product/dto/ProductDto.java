package gift.domain.product.dto;

import gift.domain.product.entity.Product;

public record ProductDto(Long id, String name, int price, String imageUrl) {

    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
