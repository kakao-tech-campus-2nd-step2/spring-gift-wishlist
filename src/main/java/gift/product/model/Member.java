package gift.product.model;

import jakarta.validation.constraints.NotBlank;

public class Member {

    @NotBlank(message = "이메일은 필수 입력 요소입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 요소입니다.")
    private String password;

    private String token;

    private int rank;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
