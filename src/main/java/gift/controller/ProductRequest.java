package gift.controller;

import gift.domain.Product;
import gift.error.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Optional;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.FieldError;

public class ProductRequest {

    @NotBlank(message = ErrorMessage.PRODUCT_NAME_NOT_BLANK)
    @Length(max = 15, message = ErrorMessage.PRODUCT_NAME_EXCEEDS_MAX_LENGTH)
    @Pattern(regexp = "[a-zA-Z0-9가-힣 ()\\[\\]+\\-&/_]*", message = ErrorMessage.PRODUCT_NAME_INVALID_CHAR)
    private String name;

    @NotNull(message = ErrorMessage.PRODUCT_PRICE_NOT_NULL)
    private Integer price;

    private String imageUrl;

    public Optional<FieldError> checkNameContainingKakao() {
        if (this.name != null && this.name.contains("카카오")) {
            return Optional.of(
                new FieldError("ProductRequest", "name", ErrorMessage.PRODUCT_NAME_CONTAINS_KAKAO));
        }

        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product toEntity() {
        return new Product(this.name, this.price, this.imageUrl);
    }

    public ProductRequest() {
    }

    public ProductRequest(String name, Integer price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
