package gift.exception;

public enum ErrorCode {
    INVALID_NAME_SIZE("상품 이름은 15자 이하여야합니다. (공백포함)"),
    INVALID_NAME_PATTERN("( ), [ ], +, -, &, /, _ 외 특수문자는 사용 불가능합니다."),
    KAKAO_NAME_NOT_ALLOWED("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의해 주세요."),
    PRODUCT_NOT_FOUND("해당 상품이 존재하지 않습니다."),
    EMAIL_ALREADY_EXISTS("해당 이메일이 이미 존재합니다."),
    INVALID_CREDENTIALS("이메일 또는 비밀번호가 잘못되었습니다."),
    USER_NOT_FOUND("해당 사용자가 존재하지 않습니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    UNAUTHORIZED_REQUEST("유효한 인증 자격 증명이 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
