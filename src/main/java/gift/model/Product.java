package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 제품의 세부 사항을 나타내는 클래스. ID, 이름, 가격, 이미지 URL을 포함한다.
 */
public record Product(
    @NotNull(message = "ID는 null이 될 수 없습니다.")
    long id,

    @NotNull(message = "이름은 null이 될 수 없습니다.")
    @Size(min = 1, max = 15, message = "이름은 1자에서 15자 사이여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$", message = "이름은 알파벳, 숫자, 공백 및 특수 문자 ( ) [ ] + - & / _ 만 포함할 수 있습니다.")
    String name,

    @NotNull(message = "가격은 null이 될 수 없습니다.")
    long price,

    @NotNull(message = "이미지 URL은 null이 될 수 없습니다.")
    @Pattern(regexp = "https?://.*", message = "이미지 URL은 http 또는 https로 시작해야 합니다.")
    String imageUrl
) {}
