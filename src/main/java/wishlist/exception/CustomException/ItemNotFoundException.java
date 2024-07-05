package wishlist.exception.CustomException;

import wishlist.exception.ErrorCode;

public class ItemNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public ItemNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
