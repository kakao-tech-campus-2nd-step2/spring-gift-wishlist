package gift.service;

import gift.dto.RegisterRequest;
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
        service.register(registerRequest);
    }
}
