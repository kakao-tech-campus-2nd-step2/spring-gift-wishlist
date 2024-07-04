package gift.product.dto;

import gift.product.domain.Product;
import gift.product.domain.ProductName;

public record ServiceDto(Long id, ProductName name, int price, String imageUrl) {
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
