package gift.wishlist.model.dto;

import gift.product.model.dto.ProductResponse;

public record WishListResponse(ProductResponse product, int quantity) {
}
