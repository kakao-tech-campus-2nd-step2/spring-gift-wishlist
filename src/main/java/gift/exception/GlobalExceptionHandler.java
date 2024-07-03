package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotValidProductNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotValidProductNameException(NotValidProductNameException e, Model model) {
        model.addAttribute("msg", e.getMessage());
        model.addAttribute("url", "/admin/products/new");
        return "alert";
    }
}
