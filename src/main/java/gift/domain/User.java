package gift.domain;

import jakarta.validation.constraints.NotBlank;

public class User {
    private Long id;
    @NotBlank(message = "이메일은 필수로 입력하셔야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수로 입력하셔야 합니다.")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters, setters

    public Long getId() {
        return id;
    }

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
