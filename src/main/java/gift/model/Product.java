package gift.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record Product(
        Long id,

        @NotBlank(message = "이름은 필수 항목입니다.")
        @Size(max = 15, message = "이름은 15자 이내여야 합니다.")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s\\(\\)\\[\\]+&\\-/_]*$", message = "이름에 유효하지 않은 문자가 포함되어 있습니다.")
        String name,

        int price,
        String imageUrl
) {
}