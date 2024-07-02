package gift.domain.product.dto;

import gift.domain.product.Product;

public record ProductRequestDto(String name, Long price, String imageUrl) {

    public static Product toEntity(Long id, ProductRequestDto requestDto) {
        return new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }
}
