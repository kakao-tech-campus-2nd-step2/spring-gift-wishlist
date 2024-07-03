package gift.exception;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


import gift.exception.product.DuplicateProductIdException;
import gift.exception.product.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetail.setTitle("Product Not Found");
		problemDetail.setType(URI.create("https://example.com/probs/product-not-found"));
		problemDetail.setInstance(URI.create(request.getDescription(false)));
		return problemDetail;
	}


	@ExceptionHandler(DuplicateProductIdException.class)
	public ProblemDetail handleDuplicateProductIdException(DuplicateProductIdException ex, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
		problemDetail.setTitle("Duplicate Product ID");
		problemDetail.setType(URI.create("https://example.com/probs/duplicate-product-id"));
		problemDetail.setInstance(URI.create(request.getDescription(false)));
		return problemDetail;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
		problemDetail.setTitle("Validation Error");
		problemDetail.setType(URI.create("https://example.com/validation-error"));
		problemDetail.setInstance(URI.create(request.getDescription(false)));

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		problemDetail.setProperty("errors", errors);

		return problemDetail;
	}
}