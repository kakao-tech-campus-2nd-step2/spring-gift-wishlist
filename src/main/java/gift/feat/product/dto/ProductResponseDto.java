package gift.feat.product.dto;

import gift.feat.product.domain.Product;

public record ProductResponseDto(
	Long id,
	String name,
	Long price,
	String imageUrl
) {
	static public ProductResponseDto from(Product product) {
		return new ProductResponseDto(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getImageUrl()
		);
	}
}
