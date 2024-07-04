package gift.exception.type;

import gift.exception.ApplicationException;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String message) {
        super(message, 404);
    }
}
