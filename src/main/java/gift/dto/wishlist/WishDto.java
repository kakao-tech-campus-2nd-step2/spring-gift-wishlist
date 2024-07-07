package gift.dto.wishlist;

import gift.model.Product;

public record WishDto(
    Long id,
    Product product,
    int quantity
) {

}
