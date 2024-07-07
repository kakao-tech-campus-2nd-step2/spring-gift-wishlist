package wishlist.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "조회하신 상품은 삭제되었거나 존재하지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "필수 입력값의 누락, 또는 형식과 다른 데이터 요청입니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "지원하지 않는 요청입니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이메일은 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "해당 요청에 대한 권한이 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
