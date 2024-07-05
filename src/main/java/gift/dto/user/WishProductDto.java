package gift.dto.user;

import gift.model.Product;

public record WishProductDto(
    Product product,
    int counts
) {

}
