package gift.controller.product.dto;


import gift.model.product.Product;
import gift.validate.ValidProductName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
    @NotBlank @ValidProductName
    String name,
    @Min(0)
    Integer price,
    @NotBlank
    String imageUrl
) {

    public Product toEntity() {
        return Product.create(null, name(), price(), imageUrl());
    }

    public Product toEntity(Long id) {
        return Product.create(id, name(), price(), imageUrl());
    }
}
