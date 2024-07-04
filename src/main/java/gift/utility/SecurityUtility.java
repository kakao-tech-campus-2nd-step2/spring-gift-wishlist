package gift.utility;

import org.springframework.ui.Model;

// 비밀번호 관련된 유틸리티 클래스
// 관리자 화면이므로 접근을 위해 비밀번호를 입력받는 것이 바람직하다고 생각했습니다.
// 아직은 사용할 때가 아닌 것 같아서 방치하였습니다.
public class SecurityUtility {
    private static final String PASSWORD = "qwer1234";

    // 비밀번호가 틀린 경우, 예외를 반환하는 함수
    public static void verifyPassword(String password) {
        if (!password.equals(PASSWORD)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }
    }

    public static void addPasswordAttribute(Model model) {
        model.addAttribute("password", PASSWORD);
    }
}
