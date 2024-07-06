package gift.wishlist.model;

import gift.product.model.dto.ProductResponse;

public record WishListResponse(ProductResponse product, int quantity) {
}
