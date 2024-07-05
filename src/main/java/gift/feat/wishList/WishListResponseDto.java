package gift.feat.wishList;

import gift.feat.product.domain.Product;

public record WishListResponseDto(
	Long id,
	String name,
	Long price,
	String imageUrl
) {
static public WishListResponseDto from(Product product) {
	return new WishListResponseDto(
		product.getId(),
		product.getName(),
		product.getPrice(),
		product.getImageUrl()
	);
	}
}
