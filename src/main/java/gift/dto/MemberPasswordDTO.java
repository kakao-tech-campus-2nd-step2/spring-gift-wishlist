package gift.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberPasswordDTO(
    @NotBlank
    String password,

    @NotBlank
    String newPassword1,

    @NotBlank
    String newPassword2
) {

}
