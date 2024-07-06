package gift.core.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다!"),
    PRODUCT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 상품입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다!"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다."),
    USER_ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 계정을 찾을 수 없습니다!"),

    AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    AUTHENTICATION_FAILED(HttpStatus.FORBIDDEN, "인증에 실패하였습니다."),
    AUTHENTICATION_EXPIRED(HttpStatus.FORBIDDEN, "인증이 만료되었습니다."),

    WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품을 위시리스트에서 찾을 수 없습니다."),
    WISH_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 해당 상품이 위시리스트에 존재합니다."),

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.")
    ;

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
