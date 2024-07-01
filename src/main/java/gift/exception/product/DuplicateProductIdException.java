package gift.exception.product;

public class DuplicateProductIdException extends RuntimeException {
	public DuplicateProductIdException(Long id) {
		super("Product with ID " + id + " already exists");
	}
}