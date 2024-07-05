package gift.service;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.exception.DuplicatedEmailException;
import gift.exception.InvalidLoginInfoException;
import gift.exception.NotFoundElementException;
import gift.exception.UnauthorizedAccessException;
import gift.model.MemberRole;
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
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var auth = service.register(registerRequest);
        var id = authService.getMemberIdWithToken(auth.token());
        var role = authService.getMemberRoleWithToken(auth.token());

        Assertions.assertThat(role).isEqualTo(MemberRole.MEMBER);

        service.deleteMember(id, auth.token());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도하기 - 실패")
    void registerFailWithDuplicatedEmail() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var auth = service.register(registerRequest);
        var id = authService.getMemberIdWithToken(auth.token());

        Assertions.assertThatThrownBy(() -> service.register(registerRequest)).isInstanceOf(DuplicatedEmailException.class);

        service.deleteMember(id, auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 성공")
    void loginSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        service.register(registerRequest);

        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = service.login(loginRequest);
        var id = authService.getMemberIdWithToken(auth.token());
        var role = authService.getMemberRoleWithToken(auth.token());

        Assertions.assertThat(role).isEqualTo(MemberRole.MEMBER);

        service.deleteMember(id, auth.token());
    }

    @Test
    @DisplayName("로그인 실행하기 - 실패")
    void loginFailWithWrongPassword() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPasswords", "MEMBER");
        var auth = service.register(registerRequest);
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");

        Assertions.assertThatThrownBy(() -> service.login(loginRequest)).isInstanceOf(InvalidLoginInfoException.class);

        var id = authService.getMemberIdWithToken(auth.token());
        service.deleteMember(id, auth.token());
    }

    @Test
    @DisplayName("회원 탈퇴하기 - 성공")
    void deleteMemberSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword", "MEMBER");
        var loginRequest = new LoginRequest("test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var id = authService.getMemberIdWithToken(auth.token());
        var loginAuth = service.login(loginRequest);

        Assertions.assertThat(auth.token()).isEqualTo(loginAuth.token());

        service.deleteMember(id, auth.token());

        Assertions.assertThatThrownBy(() -> service.login(loginRequest)).isInstanceOf(NotFoundElementException.class);
    }

    @Test
    @DisplayName("다른 회원 탈퇴하기 - 실패")
    void deleteMemberFail() {
        var registerRequest1 = new RegisterRequest("테스트1", "test1@naver.com", "testPassword", "MEMBER");
        var auth1 = service.register(registerRequest1);
        var id1 = authService.getMemberIdWithToken(auth1.token());

        var registerRequest2 = new RegisterRequest("테스트2", "test2@naver.com", "testPassword", "MEMBER");
        var auth2 = service.register(registerRequest2);
        var id2 = authService.getMemberIdWithToken(auth2.token());

        Assertions.assertThatThrownBy(() -> service.deleteMember(id1, auth2.token()))
                .isInstanceOf(UnauthorizedAccessException.class);

        service.deleteMember(id1, auth1.token());
        service.deleteMember(id2, auth2.token());
    }

    @Test
    @DisplayName("관리자로 다른 회원 탈퇴하기")
    void deleteMemberWithAdmin() {
        var registerRequest1 = new RegisterRequest("테스트1", "test1@naver.com", "testPassword", "MEMBER");
        var auth1 = service.register(registerRequest1);
        var id1 = authService.getMemberIdWithToken(auth1.token());
        var role1 = authService.getMemberRoleWithToken(auth1.token());

        var registerRequest2 = new RegisterRequest("테스트2", "test2@naver.com", "testPassword", "ADMIN");
        var auth2 = service.register(registerRequest2);
        var id2 = authService.getMemberIdWithToken(auth2.token());
        var role2 = authService.getMemberRoleWithToken(auth2.token());

        Assertions.assertThat(role1).isEqualTo(MemberRole.MEMBER);
        Assertions.assertThat(role2).isEqualTo(MemberRole.ADMIN);

        service.deleteMember(id1, auth2.token());

        var loginRequest1 = new LoginRequest("test1@naver.com", "testPassword");

        Assertions.assertThatThrownBy(() -> service.login(loginRequest1)).isInstanceOf(NotFoundElementException.class);

        service.deleteMember(id2, auth2.token());
    }
}
