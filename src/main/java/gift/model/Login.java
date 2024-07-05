package gift.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 로그인 모델 클래스
 */
public class Login {

    @NotNull(message = "email은 필수 입력입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
        message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Size(min = 4, max = 30, message = "비밀번호는 4자 이상 30자 미만입니다.")
    @NotNull
    private String password;

    /**
     * Login 생성자
     *
     * @param email    이메일
     * @param password 비밀번호
     */
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * 이메일 반환 메서드
     *
     * @return 이메일
     */
    public String getEmail() {
        return email;
    }

    /**
     * 이메일 설정 메서드
     *
     * @param email 이메일
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 비밀번호 반환 메서드
     *
     * @return 비밀번호
     */
    public String getPassword() {
        return password;
    }

    /**
     * 비밀번호 설정 메서드
     *
     * @param password 비밀번호
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
