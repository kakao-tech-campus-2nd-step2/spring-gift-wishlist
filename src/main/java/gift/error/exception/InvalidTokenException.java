package gift.error.exception;

import gift.error.ErrorMessage;

public class InvalidTokenException extends GiftException {

    public InvalidTokenException() {
        super(ErrorMessage.INVALID_TOKEN);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }

}
