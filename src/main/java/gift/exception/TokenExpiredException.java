package gift.exception;

import gift.constant.ErrorMessage;

public class TokenExpiredException extends GiftException {

    public TokenExpiredException() {
        super(ErrorMessage.EXPIRED_TOKEN);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }

}
