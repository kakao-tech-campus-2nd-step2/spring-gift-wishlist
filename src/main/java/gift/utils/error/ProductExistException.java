package gift.utils.error;

public class ProductExistException extends RuntimeException{
    public ProductExistException(String message){
        super(message);
    }
}