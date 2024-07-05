package gift.exception;

public class InvalidNewPasswordException extends RuntimeException {

    public InvalidNewPasswordException() {
        super("비밀번호 확인이 일치하지 않습니다.");
    }
}
