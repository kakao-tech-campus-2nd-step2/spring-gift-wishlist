package gift.dto;

import gift.validation.ValidName;
import gift.vo.Product;
import jakarta.validation.constraints.*;

public record ProductDto(
        @Size(max=15) @ValidName @NotEmpty(message = "상품명을 입력해 주세요.")
        String name,
        int price,
        String imageUrl
) {
    public Product toProduct() {
        return new Product(name, price, imageUrl);
    }
}
