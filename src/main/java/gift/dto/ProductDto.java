package gift.dto;

import gift.domain.Product;

public record ProductDto(String name, Integer price, String description, String imageUrl) {
    public static ProductDto from(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getDescription(), product.getUrl());
    }
}
