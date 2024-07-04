package gift.error.exception;

import gift.error.ErrorMessage;

public class LoginException extends GiftException {

    public LoginException() {
        super(ErrorMessage.LOGIN_FAILURE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }

}
