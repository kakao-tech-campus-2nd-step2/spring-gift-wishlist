package gift.service;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.exception.NotFoundElementException;
import gift.model.MemberRole;
import gift.utils.AuthUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Value("${SECRET_KEY}")
    private String secretKey;

    @Test
    @DisplayName("회원가입 시도하기 - 성공")
    void registerSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var auth = memberService.register(registerRequest);
        var id = AuthUtils.getMemberIdWithToken(auth.token(), secretKey);
        var role = AuthUtils.getMemberRoleWithToken(auth.token(), secretKey);

        Assertions.assertThat(role).isEqualTo(MemberRole.MEMBER);

        memberService.deleteMember(id);
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도하기 - 실패")
    void registerFailWithDuplicatedEmail() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var auth = memberService.register(registerRequest);
        var id = AuthUtils.getMemberIdWithToken(auth.token(), secretKey);

        Assertions.assertThatThrownBy(() -> memberService.register(registerRequest)).isInstanceOf(DuplicatedEmailException.class);

        memberService.deleteMember(id);
    }

    @Test
    @DisplayName("로그인 실행하기 - 성공")
    void loginSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        memberService.register(registerRequest);

        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = memberService.login(loginRequest);
        var id = AuthUtils.getMemberIdWithToken(auth.token(), secretKey);
        var role = AuthUtils.getMemberRoleWithToken(auth.token(), secretKey);

        Assertions.assertThat(role).isEqualTo(MemberRole.MEMBER);

        memberService.deleteMember(id);
    }

    @Test
    @DisplayName("로그인 실행하기 - 실패")
    void loginFailWithWrongPassword() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPasswords", "MEMBER");
        var auth = memberService.register(registerRequest);
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");

        Assertions.assertThatThrownBy(() -> memberService.login(loginRequest)).isInstanceOf(InvalidLoginInfoException.class);

        var id = AuthUtils.getMemberIdWithToken(auth.token(), secretKey);
        memberService.deleteMember(id);
    }

    @Test
    @DisplayName("회원 탈퇴하기 - 성공")
    void deleteMemberSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = memberService.register(registerRequest);
        var id = AuthUtils.getMemberIdWithToken(auth.token(), secretKey);
        var loginAuth = memberService.login(loginRequest);

        Assertions.assertThat(auth.token()).isEqualTo(loginAuth.token());

        memberService.deleteMember(id);

        Assertions.assertThatThrownBy(() -> memberService.login(loginRequest)).isInstanceOf(NotFoundElementException.class);
    }
}
