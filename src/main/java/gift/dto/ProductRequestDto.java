package gift.dto;

import gift.domain.Product;

public record ProductRequestDto(
	Long id,
	String name,
	Long price,
	String imageUrl
) {

	public Product toEntity() {
		return Product.of(id, name, price, imageUrl);
	}
}
