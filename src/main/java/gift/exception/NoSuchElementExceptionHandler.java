package gift.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class NoSuchElementExceptionHandler {
    @ExceptionHandler
    public String handleNoSuchElementException(NoSuchElementException ex, Model model){
        model.addAttribute("message",ex.getMessage());
        return "Exception";
    }
}
