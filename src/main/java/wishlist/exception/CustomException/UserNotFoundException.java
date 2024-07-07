package wishlist.exception.CustomException;

import wishlist.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
