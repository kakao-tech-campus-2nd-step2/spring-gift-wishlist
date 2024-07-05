package gift.service;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.exception.NotFoundElementException;
import gift.exception.UnauthorizedAccessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService service;
    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("회원가입 시도하기 - 성공")
    void registerSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = authService.getClaimsWithToken(auth.token());

        Assertions.assertThat(claims.get("name", String.class)).isEqualTo("테스트");

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도하기 - 실패")
    void registerFailWithDuplicatedEmail() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = authService.getClaimsWithToken(auth.token());

        Assertions.assertThatThrownBy(() -> service.register(registerRequest)).isInstanceOf(DuplicatedEmailException.class);

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 성공")
    void loginSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        service.register(registerRequest);

        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = service.login(loginRequest);
        var claims = authService.getClaimsWithToken(auth.token());

        Assertions.assertThat(claims.get("name", String.class)).isEqualTo("테스트");

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 실패")
    void loginFailWithWrongPassword() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPasswords");
        var auth = service.register(registerRequest);
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");

        Assertions.assertThatThrownBy(() -> service.login(loginRequest)).isInstanceOf(InvalidLoginInfoException.class);

        var claims = authService.getClaimsWithToken(auth.token());
        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());
    }

    @Test
    @DisplayName("회원 탈퇴하기 - 성공")
    void deleteMemberSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = authService.getClaimsWithToken(auth.token());
        var loginAuth = service.login(loginRequest);

        Assertions.assertThat(auth.token()).isEqualTo(loginAuth.token());

        service.deleteMember(Long.parseLong(claims.getSubject()), auth.token());

        Assertions.assertThatThrownBy(() -> service.login(loginRequest)).isInstanceOf(NotFoundElementException.class);
    }

    @Test
    @DisplayName("다른 회원 탈퇴하기 - 실패")
    void deleteMemberFail() {
        var registerRequest1 = new RegisterRequest("테스트1", "test1@naver.com", "testPassword");
        var auth1 = service.register(registerRequest1);
        var claims1 = authService.getClaimsWithToken(auth1.token());

        var registerRequest2 = new RegisterRequest("테스트2", "test2@naver.com", "testPassword");
        var auth2 = service.register(registerRequest2);
        var claims2 = authService.getClaimsWithToken(auth2.token());

        Assertions.assertThatThrownBy(() -> service.deleteMember(Long.parseLong(claims1.getSubject()), auth2.token()))
                .isInstanceOf(UnauthorizedAccessException.class);

        service.deleteMember(Long.parseLong(claims1.getSubject()), auth1.token());
        service.deleteMember(Long.parseLong(claims2.getSubject()), auth2.token());
    }
}
