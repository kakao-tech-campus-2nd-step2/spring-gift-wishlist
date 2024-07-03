package gift.dto;

import gift.validation.ValidName;
import gift.vo.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProductUpdateDto (
        Long id,
        @Size(max=15) @ValidName @NotEmpty(message = "상품명을 입력해주세요.")
        String name,
        int price,
        String imageUrl
) {
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
