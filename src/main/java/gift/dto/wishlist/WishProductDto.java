package gift.dto.wishlist;

import gift.model.Product;

public record WishProductDto(
    Product product,
    int count
) {

}
