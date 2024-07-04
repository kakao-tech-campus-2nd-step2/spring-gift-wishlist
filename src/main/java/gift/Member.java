package gift;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Member {
    private Long id;
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "유효하지 않은 이메일 형식 입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
