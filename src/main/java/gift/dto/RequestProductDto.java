package gift.dto;

import gift.domain.Product;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record RequestProductDto(
        @NotBlank
        @Size(max = 15)
        @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣 ()\\[\\]+\\-&/_]*$",
                message = "오직 문자, 공백 그리고 특수문자 (),[],+,&,-,/,_만 허용됩니다."
        )
        String name,
        @NotNull @Min(0) Integer price,
        @Size(max = 255) String description,
        @URL String imageUrl
) {
    public Product toProduct() {
        return new Product(name, description, price, imageUrl);
    }
}
