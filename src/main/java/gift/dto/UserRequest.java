package gift.dto;

import jakarta.validation.constraints.Email;

public class UserRequest {
    @Email(message = "이메일 형식의 입력이어야 합니다.")
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
