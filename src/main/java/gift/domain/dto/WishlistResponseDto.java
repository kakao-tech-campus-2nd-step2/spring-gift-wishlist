package gift.domain.dto;

import gift.domain.entity.Product;

public record WishlistResponseDto(
    Long productId,
    String productName,
    Long productPrice,
    String productImageUrl,
    Long quantity) {

    public static WishlistResponseDto of(Long quantity, Product product) {
        return new WishlistResponseDto(product.id(), product.name(), product.price(), product.imageUrl(), quantity);
    }
}
