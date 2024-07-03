package gift.domain.product.dto;

import gift.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
    @NotNull
    @Size(min = 1, max = 15,
        message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.")
    @Pattern(regexp = "^[\\w\\s\\[\\]()\\+\\-&/_]*$",
        message = "사용 가능한 특수 문자는 (), [], +, -, &, /, _ 입니다.")
    @Pattern(regexp = "^(?!.*카카오).*$",
        message = "'카카오'가 포함된 문구는 사용할 수 없습니다.")
    String name,
    @NotNull
    Long price,
    @NotNull
    String imageUrl) {

    public static Product toEntity(Long id, ProductRequestDto requestDto) {
        return new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }
}
