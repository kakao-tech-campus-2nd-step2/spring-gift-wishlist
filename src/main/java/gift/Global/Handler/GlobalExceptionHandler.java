package gift.Global.Handler;

import gift.Global.Exception.BusinessException;
import gift.Global.Response.ErrorCode;
import gift.Global.Response.ErrorResponseDto;
import gift.Global.Response.ResponseMaker;
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
        return ResponseMaker.createErrorResponse(e.getErrorCode());
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
        return ResponseMaker.createErrorResponse(ErrorCode.INVALID_PRODUCT_ARGUMENT);
    }
}
