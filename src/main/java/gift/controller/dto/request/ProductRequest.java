package gift.controller.dto.request;

import gift.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public record ProductRequest(
        @NotBlank(
                message = "상품명은 필수 입력값입니다."
        )
        @Length(
                max = 15,
                message = "상품명은 최대 15자까지 입력 가능합니다."
        )
        String name,
        @NotNull(
                message = "가격은 필수 입력값입니다."
        )
        @Range(
                min = 1,
                max = 2_100_000_000,
                message = "가격은 1원 이상, 21억원 이하이어야 합니다."
        )
        Integer price,
        @NotBlank(
                message = "이미지 URL은 필수 입력값입니다."
        )
        @URL(
                message = "이미지 URL 형식이 올바르지 않습니다."
        )
        String imgUrl
) {
    public Product toModel() {
        return new Product(null, name(), price(), imgUrl());
    }

    public Product toModel(final Long id) {
        return new Product(id, name(), price(), imgUrl());
    }
}
