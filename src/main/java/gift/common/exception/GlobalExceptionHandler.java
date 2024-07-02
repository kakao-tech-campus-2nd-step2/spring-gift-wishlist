package gift.common.exception;

import gift.product.dto.ProductReqDto;
import gift.product.exception.ProductCreateException;
import gift.product.exception.ProductDeleteException;
import gift.product.exception.ProductErrorCode;
import gift.product.exception.ProductNotFoundException;
import gift.product.exception.ProductUpdateException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomProblemDetail> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new CustomProblemDetail(errorCode, request));
    }

    @ExceptionHandler(ProductCreateException.class)
    public ResponseEntity<CustomProblemDetail> handleProductCreateException(ProductCreateException ex, WebRequest request) {
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new CustomProblemDetail(errorCode, request));
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<CustomProblemDetail> handleProductUpdateException(ProductUpdateException ex, WebRequest request) {
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new CustomProblemDetail(errorCode, request));
    }

    @ExceptionHandler(ProductDeleteException.class)
    public ResponseEntity<CustomProblemDetail> handleProductDeleteException(ProductDeleteException ex, WebRequest request) {
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new CustomProblemDetail(errorCode, request));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(  // 파라미터 유효성 검사 실패 시 발생하는 예외 처리
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        ErrorCode errorCode = CommonErrorCode.INVALID_INPUT_VALUE;  // 기본적으로 INVALID_INPUT_VALUE로 처리

        Class<?> parameterType = ex.getParameter().getParameterType();

        if (parameterType.equals(ProductReqDto.class)) {            // 도메인에 따라 ErrorCode를 다르게 처리: INVALID_INPUT_VALUE_{도메인명}
            errorCode = ProductErrorCode.INVALID_INPUT_VALUE_PRODUCT;
        }

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(ex, errorCode, request));
    }

    private CustomProblemDetail makeErrorResponse(MethodArgumentNotValidException ex, ErrorCode errorCode, WebRequest request) {

        CustomProblemDetail problemDetail = new CustomProblemDetail(errorCode, request);

        List<ValidationError> invalidParams = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::of)
                .toList();

        problemDetail.setInvalidParams(invalidParams);

        return problemDetail;
    }
}
