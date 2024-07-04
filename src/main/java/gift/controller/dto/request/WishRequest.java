package gift.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record WishRequest(
        @Min(0)
        Long id,
        @NotBlank
        @Length(max = 15)
        @Pattern(regexp = "^[()\\[\\]+\\-&/_ㄱ-하-ㅣ가-힣a-zA-Z0-9]*$",
                message = "( ), [ ], +, -, &, /, _ 을 제외한 특수 문자는 사용이 불가합니다.")
        String name
) {
}
