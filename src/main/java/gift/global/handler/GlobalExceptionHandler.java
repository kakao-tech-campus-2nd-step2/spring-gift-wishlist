package gift.global.handler;

import gift.global.exception.BusinessException;
import gift.global.response.ErrorResponseDto;
import gift.global.response.ResponseMaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * RuntimeException 을 상속받는 커스텀 에러 핸들러
     * 개발자가 직접 날리는 에러
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        return ResponseMaker.createErrorResponse(e.getStatus(), e.getMessage());
    }


    /**
     * MethodArgumentNotValidException 에러 핸들러
     * 매개변수 인자가 유효하지 않은 경우 발생
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> MethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return ResponseMaker.createErrorResponse(HttpStatus.BAD_REQUEST, "입력값이 유효하지 않습니다.");
    }
}
