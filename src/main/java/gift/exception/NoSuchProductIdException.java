package gift.exception;

public class NoSuchProductIdException extends CustomException {
    public NoSuchProductIdException(){
        super();
    }
    public NoSuchProductIdException(String message){
        super(message);
    }

}
