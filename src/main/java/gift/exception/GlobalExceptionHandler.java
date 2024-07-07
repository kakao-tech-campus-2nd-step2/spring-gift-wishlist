package gift.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotValidProductNameException.class)
    public String handleNotValidProductNameException(NotValidProductNameException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("product", e.getProduct());
        return "add_product";
    }
}
