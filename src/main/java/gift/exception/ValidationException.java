package gift.exception;

// 추가한 예외. 잘못된 형식이라는 느낌을 직관적으로 주기 위해 validation이라는 단어를 선택
public class ValidationException extends IllegalArgumentException {
    public ValidationException(String message) {
        super(message);
    }
}
