package gift;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.ui.Model;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidNameException.class)
    public String handleInvalidNameException(InvalidNameException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error_page";
    }

}