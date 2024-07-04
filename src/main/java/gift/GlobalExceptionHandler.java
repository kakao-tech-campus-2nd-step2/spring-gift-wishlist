package gift;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        String viewName = "error";


        System.out.println("*****************"+referer);
        if (referer != null) {
            if (referer.contains("/admin/products/add")) {
                viewName = "Add_product";
            } else if (referer.contains("/admin/products/edit")) {
                viewName = "Edit_product";
            }
        }

        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("product", e.getProductDTO());
        return  viewName;
    }
}