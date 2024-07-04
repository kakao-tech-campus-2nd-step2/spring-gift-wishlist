package gift.controller.dto.request;

import gift.common.exception.ValidateErrorMessage;
import gift.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public record ProductRequest(
        @NotBlank(message = ValidateErrorMessage.INVALID_PRODUCT_NAME_NULL)
        @Pattern(
                regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣 ()\\[\\]+\\-&/_]*$",
                message = ValidateErrorMessage.INVALID_PRODUCT_NAME_PATTERN
        )
        @Length(max = 15, message = ValidateErrorMessage.INVALID_PRODUCT_NAME_LENGTH)
        String name,
        @NotNull(message = ValidateErrorMessage.INVALID_PRODUCT_PRICE_NULL)
        @Range(min = 1, max = 2_100_000_000, message = ValidateErrorMessage.INVALID_PRODUCT_PRICE_RANGE)
        Integer price,
        @NotBlank(message = ValidateErrorMessage.INVALID_PRODUCT_IMG_URL_NULL)
        @URL(message = ValidateErrorMessage.INVALID_PRODUCT_IMG_URL_FORMAT)
        String imgUrl
) {
    public Product toModel() {
        return new Product(null, name(), price(), imgUrl());
    }

    public Product toModel(final Long id) {
        return new Product(id, name(), price(), imgUrl());
    }
}
