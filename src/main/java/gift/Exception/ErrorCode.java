package gift.Exception;

public enum ErrorCode {
    INVALID_NAME_SIZE(400, "이름은 공백포함 15자 이하만 가능합니다."),
    INVALID_NAME_SYMBOL(400, "허용되는 특수문자 ( ), [ ], +, -, &, /, _  입니다."),
    INVALID_KAKAO(400, "\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");

    private final String message;
    private final int code;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
