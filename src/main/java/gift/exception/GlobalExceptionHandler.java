package gift.exception;

import gift.model.Product;
import jakarta.validation.ConstraintViolationException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException ex, Model model) {
        String errorMessage = ex.getMessage(); // 이 부분은 ConstraintViolationException의 메시지를 가져오는 것으로 변경
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("product", new Product(0,"", 0, ""));
        return "add_product";
    }

}
