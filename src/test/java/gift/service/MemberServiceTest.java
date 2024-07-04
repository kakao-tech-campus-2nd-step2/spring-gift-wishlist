package gift.service;

import gift.dto.RegisterRequest;
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
    @DisplayName("회원가입 실행하기")
    void registerSuccess() {
        var registerRequest = new RegisterRequest("테스트", "test@naver.com", "testPassword");
        var auth = service.register(registerRequest);
        var claims = AuthServiceUtils.getClaimsWithToken(auth.token());

        Assertions.assertThat(claims.get("name", String.class)).isEqualTo("테스트");
    }
}
