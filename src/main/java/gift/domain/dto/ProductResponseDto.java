package gift.domain.dto;

import gift.domain.Entity.Product;

public record ProductResponseDto(Long id, String name, Long price, String imageUrl) {
    public static ProductResponseDto of(Product product) {
        return new ProductResponseDto(product.id(), product.name(), product.price(), product.imageUrl());
    }
}
