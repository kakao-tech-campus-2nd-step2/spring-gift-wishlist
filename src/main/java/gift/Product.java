package gift;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(
        long id,
        @Size(max = 15, message = "상품 이름은 최대 15자까지 입력할 수 있습니다.")
        @Pattern(
                regexp = "^(?!.*카카오)[\\w\\s()\\\\]+-\\&\\#\\uAC00-\\uD7A3]*$",
                message = "상품 이름에 '카카오'를 포함할 수 없으며, 허용되지 않는 특수 문자가 포함되어 있습니다."
        )
        String name,
        @Min(value = 1, message = "가격은 1 이상이어야 합니다.")
        int price,
        String imageUrl
) {
}
