package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 제품의 세부 사항을 나타내는 클래스. ID, 이름, 가격, 이미지 URL을 포함한다.
 */
public record Product(
    @NotNull
    long id,

    @NotNull
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$")
    String name,

    @NotNull
    long price,

    @NotNull
    @Pattern(regexp = "https?://.*")
    String imageUrl
) {}
