package gift.dto;

import gift.domain.Product;

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
