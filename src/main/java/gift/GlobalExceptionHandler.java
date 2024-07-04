package gift;

import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException e, WebRequest request, Model model) {
        BindingResult result = e.getBindingResult();
        String errorMessage = result.getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining("; "));

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("product", result.getTarget());

        String referer = request.getHeader("Referer");
        return (referer != null && referer.contains("/update")) ? "product-form" : "add-product";
    }
}
