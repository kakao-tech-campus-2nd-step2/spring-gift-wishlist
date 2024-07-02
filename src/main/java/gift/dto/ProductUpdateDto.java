package gift.dto;

import gift.vo.Product;

public record ProductUpdateDto (
        Long id,
        String name,
        int price,
        String imageUrl
) {
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
