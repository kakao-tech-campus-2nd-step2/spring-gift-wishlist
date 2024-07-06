package gift.exception;

public class UserException extends RuntimeException {

    private UserErrorCode userErrorCode;
    private String detailMessage;

    public UserException(UserErrorCode userErrorCode) {
        super(userErrorCode.getMessage());
        this.userErrorCode = userErrorCode;
        this.detailMessage = userErrorCode.getMessage();
    }

    public UserErrorCode getUserErrorCode() {
        return userErrorCode;
    }
}
