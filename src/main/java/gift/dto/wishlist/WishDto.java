package gift.dto.wishlist;

import gift.model.Product;

public record WishDto(
    Product product,
    int quantity
) {

}
