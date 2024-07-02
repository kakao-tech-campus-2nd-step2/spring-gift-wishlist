package gift;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RedirectView handleValidationException(MethodArgumentNotValidException e, RedirectAttributes attributes) {
        FieldError error = e.getBindingResult().getFieldError();
        if (String.valueOf(error.getRejectedValue()).contains("카카오")) {
            attributes.addFlashAttribute("errorMessage",
                "\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
        } else {
            attributes.addFlashAttribute("errorMessage", error.getDefaultMessage());
        }
        return new RedirectView("/api/products");
    }
}
