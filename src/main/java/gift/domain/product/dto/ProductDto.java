package gift.domain.product.dto;

import gift.domain.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
    Long id,
    @NotBlank(message = "상품 이름은 필수 입력 필드이며 공백으로만 구성될 수 없습니다.") String name,
    @NotNull(message = "상품 가격은 필수 입력 필드입니다.") int price,
    @NotBlank(message = "상품 이미지 주소는 필수 입력 필드입니다.") String imageUrl)
{
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
