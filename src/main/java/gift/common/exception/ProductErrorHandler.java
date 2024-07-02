package gift.common.exception;

import gift.common.util.ApiResponse;
import gift.controller.ProductController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProductController.class)
public class ProductErrorHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleProductNotFound(
            ProductNotFoundException exception
    ) {
        System.out.println("ProductErrorHandler.handleProductNotFound");
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ApiResponse.error(exception.getHttpStatus(), exception.getMessage()));
    }
}
