package gift.dto;

import gift.domain.Product;

public record ProductResponseDto(Long id, String name, Long price, String imageUrl) {
    public static ProductResponseDto of(Product product) {
        return new ProductResponseDto(product.id(), product.name(), product.price(), product.imageUrl());
    }
}
