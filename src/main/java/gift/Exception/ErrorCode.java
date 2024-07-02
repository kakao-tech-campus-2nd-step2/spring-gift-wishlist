package gift.Exception;

public enum ErrorCode {
    INVALID_NAME_LENGTH(400, "길이가 15를 넘습니다."),
    INVALID_NAME_SPECIAL_SYMBOL(400, "( ), [ ], +, -, &, /, _ 외의 특수문자가 포함됐습니다."),
    INVALID_NAME_KAKAO(400, "( ), [ ], +, -, &, /, _ 외의 특수문자가 포함됐습니다."),
    INVALID_PRICE(400, "가격은 0이하가 될 수 없습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
