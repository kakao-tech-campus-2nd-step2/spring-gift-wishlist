package gift.service;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.util.AuthServiceUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    @DisplayName("회원가입 시도하기 - 성공")
    void registerSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = AuthServiceUtils.getClaimsWithToken(auth.token());

        Assertions.assertThat(claims.get("name", String.class)).isEqualTo("테스트");

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도하기 - 실패")
    void registerFailWithDuplicatedEmail() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = AuthServiceUtils.getClaimsWithToken(auth.token());

        Assertions.assertThatThrownBy(() -> service.register(registerRequest)).isInstanceOf(DuplicatedEmailException.class);

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 성공")
    void loginSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        service.register(registerRequest);

        var loginRequest = new LoginRequest("test@naver.com","testPassword");
        var auth = service.login(loginRequest);
        var claims = AuthServiceUtils.getClaimsWithToken(auth.token());

        Assertions.assertThat(claims.get("name", String.class)).isEqualTo("테스트");

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 실패")
    void loginFailWithWrongPassword() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPasswords");
        var auth = service.register(registerRequest);
        var loginRequest = new LoginRequest("test@naver.com","testPassword");

        Assertions.assertThatThrownBy(() ->  service.login(loginRequest)).isInstanceOf(InvalidLoginInfoException.class);

        var claims = AuthServiceUtils.getClaimsWithToken(auth.token());
        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }
}
