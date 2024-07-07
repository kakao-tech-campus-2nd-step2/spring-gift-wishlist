package gift.global.handler;

import gift.global.exception.BusinessException;
import gift.global.response.ErrorResponseDto;
import gift.global.response.ResponseMaker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * RuntimeException 을 상속받는 커스텀 에러 핸들러
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        return ResponseMaker.createErrorResponse(e.getStatus(), e.getMessage());
    }


    /**
     * MethodArgumentNotValidException 에러 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> MethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach( error -> {
            errors.add(error.getDefaultMessage());
        });
        String message = String.join("\n", errors);
        return ResponseMaker.createErrorResponse(HttpStatus.BAD_REQUEST, message);
    }
}