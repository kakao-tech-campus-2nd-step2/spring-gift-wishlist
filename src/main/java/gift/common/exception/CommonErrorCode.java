package gift.common.exception;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode implements ErrorCode {
    INVALID_INPUT_VALUE("C001", "Invalid input value", HttpStatus.BAD_REQUEST, "요청 값이 잘못되었습니다."),
    ;

    private final String code;
    private final String title;
    private final HttpStatus httpStatus;
    private final String detail;

    CommonErrorCode(String code, String title, HttpStatus httpStatus, String detail) {
        this.code = code;
        this.title = title;
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDetail() {
        return detail;
    }
}
