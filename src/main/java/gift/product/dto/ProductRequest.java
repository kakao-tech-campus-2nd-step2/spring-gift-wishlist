package gift.product.dto;

import gift.product.service.ProductService;

import java.util.Objects;

public record ProductRequest(String name, int price, String imageUrl) {
    public ProductRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(imageUrl);
    }

    public ServiceDto toServiceDto() {
        return new ServiceDto(null, this.name, this.price, this.imageUrl);
    }

    public ServiceDto toServiceDto(Long id) {
        return new ServiceDto(id, this.name, this.price, this.imageUrl);
    }
}
