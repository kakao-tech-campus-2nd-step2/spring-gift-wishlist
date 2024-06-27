package gift.product.dto;

import gift.product.entity.Product;

public record ServiceDto(Long id, String name, int price, String imageUrl) {
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
