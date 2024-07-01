package gift.dto.request;

import gift.domain.Product;

public record ProductRequestDto(String name, int price, String imageUrl) {

    public static ProductRequestDto of(String name, int price, String imageUrl) {
        return new ProductRequestDto(name, price, imageUrl);
    }

    public static ProductRequestDto from(Product product) {
        return new ProductRequestDto(product.getName(), product.getPrice(), product.getImageUrl());
    }

}
