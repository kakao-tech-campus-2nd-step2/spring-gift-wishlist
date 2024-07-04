package gift.product.dto;

import gift.product.domain.ProductName;
import gift.product.validation.ValidProductName;

import java.util.Objects;

public record ProductRequestDto(@ValidProductName ProductName name, int price, String imageUrl) {
    public ProductRequestDto {
        Objects.requireNonNull(imageUrl);
    }

    public ServiceDto toServiceDto() {
        return new ServiceDto(null, this.name, this.price, this.imageUrl);
    }

    public ServiceDto toServiceDto(Long id) {
        return new ServiceDto(id, this.name, this.price, this.imageUrl);
    }
}
