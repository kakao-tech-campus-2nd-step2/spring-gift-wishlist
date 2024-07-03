package gift.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record Product(
        Long id,

        @NotBlank(message = "이름을 입력해주세요.")
        @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력 가능합니다.")
        @Pattern(regexp = "^[\\w\\s\\(\\)\\[\\]+&\\-/_]*$", message = "Invalid characters in name")
        String name,

        @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
        int price,

        @NotBlank(message = "이미지 URL을 입력해주세요.")
        String imageUrl
) {
}