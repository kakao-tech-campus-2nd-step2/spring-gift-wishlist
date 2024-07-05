package gift.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record WishedProductDTO(
    @NotBlank
    @Email(message = "이메일 양식에 맞지 않습니다.")
    String memberEmail,

    @NotBlank
    long productId,

    @PositiveOrZero
    int amount
) {

}
