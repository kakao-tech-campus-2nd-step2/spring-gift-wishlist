package gift.common.exception;

import gift.common.util.ApiResponse;
import gift.controller.UserController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiResponse<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ApiResponse.error(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<String> handleUserNotFoundException(UserNotFoundException e) {
        return ApiResponse.error(e.getStatus(), e.getMessage());
    }
}
