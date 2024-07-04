package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    static final String NOT_FOUND_MESSAGE = "존재하지 않는 리소스에 대한 접근입니다.";
    static final String FOREIGN_KEY_CONSTRAINT_VIOLATION_MESSAGE = "외래키 제약 조건에 위배되었습니다.";
    static final String DUPLICATED_EMAIL_MESSAGE = "이미 존재하는 이메일입니다.";
    static final String INVALID_LOGIN_INFO_MESSAGE = "로그인 정보가 유효하지 않습니다.";

    @ExceptionHandler(value = NotFoundElementException.class)
    public ResponseEntity<String> notFoundElementExceptionHandling() {
        return new ResponseEntity<>(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ForeignKeyConstraintViolationException.class)
    public ResponseEntity<String> foreignKeyConstraintHandling() {
        return new ResponseEntity<>(FOREIGN_KEY_CONSTRAINT_VIOLATION_MESSAGE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = DuplicatedEmailException.class)
    public ResponseEntity<String> duplicatedEmailExceptionHandling() {
        return new ResponseEntity<>(DUPLICATED_EMAIL_MESSAGE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidLoginInfoException.class)
    public ResponseEntity<String> invalidLoginInfoExceptionHandling() {
        return new ResponseEntity<>(INVALID_LOGIN_INFO_MESSAGE, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(builder.toString());
    }
}
