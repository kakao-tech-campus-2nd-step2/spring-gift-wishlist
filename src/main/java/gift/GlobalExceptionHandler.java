package gift;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RedirectView handleValidationException(MethodArgumentNotValidException e, RedirectAttributes attributes) {
        attributes.addFlashAttribute("errorMessage", e.getBindingResult().getFieldError().getDefaultMessage());
        return new RedirectView("/api/products");
    }
}
