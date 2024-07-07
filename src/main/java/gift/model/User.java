package gift.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record User(Long id,
                   @NotBlank(message = "이름을 입력하세요.")
                   String name,
                   @NotBlank(message = "이메일을 입력하세요.")
                   @Email(message = "유효한 이메일을 입력하세요.")
                   String email,
                   @NotBlank(message = "비밀번호를 입력하세요.")
                   String password,
                   String role) {

    public User {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 입력하세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일을 입력하세요.");
        }
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$]")) {
            throw new IllegalArgumentException("유효한 이메일을 입력하세요.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력하세요.");
        }
    }

}
