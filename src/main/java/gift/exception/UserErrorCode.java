package gift.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {

    WRONG_LOGIN("잘못된 로그인 정보입니다");
    private String message;
}
