package gift.exception;

public enum ErrorCode {
    INVALID_NAME_SIZE("상품 이름은 15자 이하여야합니다. (공백포함)"),
    INVALID_NAME_PATTERN("( ), [ ], +, -, &, /, _ 외 특수문자는 사용 불가능합니다."),
    KAKAO_NAME_NOT_ALLOWED("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의해 주세요."),
    PRODUCT_NOT_FOUND("해당 상품이 존재하지 않습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
