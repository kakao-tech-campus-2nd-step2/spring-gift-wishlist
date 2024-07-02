package gift.exception;

import gift.dto.ProblemDetails;
import gift.exception.product.DuplicateProductIdException;
import gift.exception.product.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ProblemDetails> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setType("https://example.com/probs/product-not-found");
		problemDetails.setTitle("Product Not Found");
		problemDetails.setDetail(ex.getMessage());
		problemDetails.setInstance(request.getRequestURI());
		problemDetails.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(problemDetails, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(DuplicateProductIdException.class)
	public ResponseEntity<ProblemDetails> handleDuplicateProductIdException(DuplicateProductIdException ex, HttpServletRequest request) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setType("https://example.com/probs/duplicate-product-id");
		problemDetails.setTitle("Duplicate Product ID");
		problemDetails.setDetail(ex.getMessage());
		problemDetails.setInstance(request.getRequestURI());
		problemDetails.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(problemDetails, HttpStatus.CONFLICT);
	}


	// 에러가 방대해지면 사용을 고려한다...
	public enum ProblemType {
		PRODUCT_NOT_FOUND("product-not-found", "https://example.com/probs/product-not-found", "Product Not Found"),
		DUPLICATE_PRODUCT_ID("duplicate-product-id", "https://example.com/probs/duplicate-product-id", "Duplicate Product ID");

		private final String code;
		private final String uri;
		private final String title;

		ProblemType(String code, String uri, String title) {
			this.code = code;
			this.uri = uri;
			this.title = title;
		}

		public String getCode() {
			return code;
		}

		public String getUri() {
			return uri;
		}

		public String getTitle() {
			return title;
		}
	}
}