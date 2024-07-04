package gift.exception.type;

import gift.exception.ApplicationException;

public class DataAccessException extends ApplicationException {

    public DataAccessException(String message) {
        super(message, 500);
    }
}
