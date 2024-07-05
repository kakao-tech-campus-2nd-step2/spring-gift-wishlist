package gift.api.wishlist;

import gift.api.product.Product;

public record WishRequestDto(
    Product product,
    Integer quantity
) {}
