package gift.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                        RedirectAttributes redirectAttributes,
                                                        HandlerMethod handlerMethod,
                                                        HttpServletRequest request)
    {
        BindingResult bindingResult = ex.getBindingResult();
        Locale locale = LocaleContextHolder.getLocale();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorMessage = messageSource.getMessage(fieldError, locale);
            redirectAttributes.addFlashAttribute(fieldError.getField() + "Error", errorMessage);
        }

        String redirectUrl = "/products/new";
        if (handlerMethod.getMethod().getName().equals("update")) {
            String productId = request.getRequestURI().split("/")[2];
            redirectUrl = "/products/edit/" + productId;
        }

        return "redirect:" + redirectUrl;
    }
}
