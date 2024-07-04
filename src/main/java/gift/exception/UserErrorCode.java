package gift.exception;

public enum UserErrorCode {
    NOT_AUTHENTICATION("아이디나 비밀번호가 틀렸습니다.");

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
