package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 상품이 이미 존재할 때 발생하는 예외
 */
public class ProductAlreadyExistsException extends RuntimeException {

}
