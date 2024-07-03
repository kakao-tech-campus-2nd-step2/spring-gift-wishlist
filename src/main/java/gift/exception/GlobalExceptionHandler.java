package gift.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                        RedirectAttributes redirectAttributes,
                                                        HandlerMethod handlerMethod,
                                                        HttpServletRequest request)
    {
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            redirectAttributes.addFlashAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
        }

        String redirectUrl = "/products/new";
        if (handlerMethod.getMethod().getName().equals("update")) {
            String productId = request.getRequestURI().split("/")[2];
            redirectUrl = "/products/edit/" + productId;
        }

        return "redirect:" + redirectUrl;
    }
}
