package gift.product.error;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistsException(AlreadyExistsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model){
        model.addAttribute("errorMessage", "유효성 검사 실패: " + ex.getBindingResult().getFieldError().getDefaultMessage());
        return "error";
    }
}
