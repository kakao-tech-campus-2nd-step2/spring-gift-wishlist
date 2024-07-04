package gift.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode {
    FAILURE_LOGIN(HttpStatus.FORBIDDEN, "아이디나 비밀번호가 틀렸습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    UserErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
