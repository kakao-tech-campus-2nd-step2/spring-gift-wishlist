package gift.product.dto;

import gift.product.message.ProductInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.List;
import jakarta.validation.constraints.Size;

public record ProductReqDto(

        // 상품의 이름은 공백을 포함하여 최대 15자까지 입력 가능
        // 특수 문자는 (), [], +, -, &, /, _ 만 허용
        // "카카오" 문구가 들어간 경우에는 담당 MD와 협의한 경우에만 사용 가능
        @NotBlank(message = ProductInfo.PRODUCT_NAME_REQUIRED)
        @Size(min = 1, max = 15, message = ProductInfo.PRODUCT_NAME_SIZE)
        @List({
                @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣()\\[\\]+\\-&/_ ]+$", message = ProductInfo.PRODUCT_NAME_PATTERN),
                @Pattern(regexp = "^(?!.*카카오).*$", message = ProductInfo.PRODUCT_NAME_KAKAO)
        })
        String name,

        @NotNull(message = ProductInfo.PRODUCT_PRICE_REQUIRED)
        Integer price,

        @NotBlank(message = ProductInfo.PRODUCT_IMAGE_URL_REQUIRED)
        String imageUrl
) {
}
