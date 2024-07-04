package gift.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull(message = "비밀번호를 입력해주세요.")
        String password,

        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email
) {
}
