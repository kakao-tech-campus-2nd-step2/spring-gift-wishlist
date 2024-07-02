package gift.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;

public record ProductRequest(
    @Max(value = 15, message = "상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$",
        message = "상품 이름에 허용되지 않은 특수 문자가 포함되어 있습니다. 허용되는 특수 문자: ( ), [ ], +, -, &, /, _"
    )
    String name,
    int price,
    String imageUrl) {

    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
