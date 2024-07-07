package gift.domain.exception;

public class WishlistProductNotIncludedException extends RuntimeException {

    public WishlistProductNotIncludedException() {
        super("The product is not included your wishlist.");
    }
}
