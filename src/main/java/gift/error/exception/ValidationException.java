package gift.error.exception;

import java.util.Map;

public class ValidationException extends GiftException {

    public ValidationException(String message, Map<String, String> validation) {
        super(message, validation);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
