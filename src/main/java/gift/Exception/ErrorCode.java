package gift.Exception;

public enum ErrorCode {
    INVALID_NAME_SIZE(400, "이름은 공백포함 15자 이하만 가능합니다."),
    INVALID_NAME_SYMBOL(400, "허용되는 특수문자 ( ), [ ], +, -, &, /, _  입니다.");

    private final String message;
    private final int code;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
