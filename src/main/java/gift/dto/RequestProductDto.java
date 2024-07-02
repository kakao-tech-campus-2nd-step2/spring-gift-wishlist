package gift.dto;

import gift.domain.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record RequestProductDto(
        @NotBlank String name,
        @NotNull @Min(0) Integer price,
        @Size(max = 255) String description,
        @URL String imageUrl
) {
    public Product toProduct() {
        return new Product(name, description, price, imageUrl);
    }
}
