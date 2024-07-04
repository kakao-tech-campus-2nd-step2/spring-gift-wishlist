package gift.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Product(
    Long id,
    @NotBlank
    @Size(max=15)
    @Pattern(regexp="^[가-힣 \\w\\(\\)\\[\\]\\+\\-\\&\\/]*$",
             message = "( ), [ ], +, -, &, /, _의 특수문자만 사용 가능합니다.")
    String name,
    Long price,
    String imageUrl) {
}
