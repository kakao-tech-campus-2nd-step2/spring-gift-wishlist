package gift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Product(
    @NotNull Long id,

    @NotBlank(message = "상품 이름은 비워둘 수 없습니다.")
    @Size(max = 15, message = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9 ()\\[\\]+\\-&/_]*$", message = "특수문자는 ( ), [ ], +, -, &, /, _만 사용 가능합니다.")
    String name,

    @Positive Integer price,

    String imageUrl
) {}
