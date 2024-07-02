package gift.model;

import jakarta.validation.constraints.Max;

public record ProductRequest(
    @Max(value = 15, message = "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.") String name,
    int price,
    String imageUrl) {

    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
