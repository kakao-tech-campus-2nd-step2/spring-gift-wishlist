package gift.api.wishlist;

public record WishListRequestDto(
    Long productId,
    Integer quantity
) {}
