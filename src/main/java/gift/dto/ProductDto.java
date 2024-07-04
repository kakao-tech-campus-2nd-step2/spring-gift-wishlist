package gift.dto;

import gift.entity.ProductEntity;

// dto를 record로 리팩토링
public record ProductDto(
    long id,
    String name,
    int price,
    String image) {
}
