package gift.exception;

import io.jsonwebtoken.JwtException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        Locale locale = LocaleContextHolder.getLocale();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), messageSource.getMessage(fieldError, locale));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage("error.invalid.format.price", null, locale));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleIllegalAccessException(IllegalAccessException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageSource.getMessage(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageSource.getMessage(ex.getMessage(), null, locale));
    }
}
