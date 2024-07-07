package gift.domain.exception;

public class UserTokenNotExistsException extends RuntimeException {

    public UserTokenNotExistsException() {
        super("토큰이 존재하지 않습니다. 발급이 필요합니다.");
    }
}
