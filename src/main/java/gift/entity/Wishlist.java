package gift.entity;

public record Wishlist(
    Long userId,
    Long productId,
    int productCount
) {

}
