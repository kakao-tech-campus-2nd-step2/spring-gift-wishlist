package gift.dto;

import gift.vo.Product;

public record ProductDto(
        String name,
        int price,
        String imageUrl
) {
    public Product toProduct() {
        return new Product(name, price, imageUrl);
    }
}
