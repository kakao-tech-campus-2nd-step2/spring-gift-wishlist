package wishlist.exception;

import java.util.Collections;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wishlist.exception.CustomException.ItemNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    private String handleItemNotFoundException(ItemNotFoundException e, Model model) {
        return handleException(e.getErrorCode(), Collections.emptyMap(),model);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private String handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e, Model model) {
        return handleException(ErrorCode.METHOD_NOT_ALLOWED, Collections.emptyMap(),model);
    }

    @ExceptionHandler(Exception.class)
    private String handleCommonException(Exception e, Model model) {
        return handleException(ErrorCode.BAD_REQUEST, Collections.emptyMap(),model);
    }

    private String handleException(ErrorCode errorCode,
        Map<String, String> errors,Model model) {
            model.addAttribute("errorResponse",new ErrorResponseDTO(errorCode,errors));
        return "error-page";
    }
}
