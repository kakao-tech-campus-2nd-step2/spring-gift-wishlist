package gift.dto;

import gift.domain.Product;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
	Long id,

	@Pattern(
		regexp = "^(?!.*카카오).*$",
		message = "Product name cannot contain '카카오'"
	)
	@Size(max = 15, message = "Product name must be at most 15 characters long")
	@Pattern(
		regexp = "^[a-zA-Z0-9가-힣 \\(\\)\\[\\]\\+\\-\\&\\/\\_]*$",
		message = "Product name contains invalid characters"
	)
	String name,
	Long price,
	String imageUrl
) {
	public Product toEntity() {
		return Product.of(id, name, price, imageUrl);
	}
}
