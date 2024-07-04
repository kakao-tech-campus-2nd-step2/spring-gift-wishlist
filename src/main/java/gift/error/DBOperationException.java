package gift.error;

public class DBOperationException extends RuntimeException{
    public DBOperationException(String message) {
        super(message);
    }
}
