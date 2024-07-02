package gift.controller.exception;

import gift.controller.AdminController;
import gift.controller.ProductController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {AdminController.class, ProductController.class})
public class ProductExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public void sendMsgToManager(){
        //채워질 예정
    }
}
