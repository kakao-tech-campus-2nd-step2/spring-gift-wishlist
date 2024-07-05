package gift.feat.product.dto;

import gift.core.annotaions.NoKakao;
import gift.core.annotaions.ValidCharset;
import gift.feat.product.domain.Product;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
	Long id,
	@Size(max = 15, message = "Product name must be at most 15 characters long")
	@ValidCharset
	@NoKakao
	String name,
	Long price,
	String imageUrl
) {
	public Product toEntity() {
		return Product.of(id, name, price, imageUrl);
	}
}
