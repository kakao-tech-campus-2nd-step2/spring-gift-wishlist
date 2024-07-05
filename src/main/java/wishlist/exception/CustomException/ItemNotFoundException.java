package wishlist.exception.CustomException;

import wishlist.exception.ErrorCode;

public class ItemNotFoundException extends CustomException {

    public ItemNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
