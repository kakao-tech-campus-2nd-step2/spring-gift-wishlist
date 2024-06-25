package gift.product.dto;

import java.util.Objects;

public record ProductRequest(String name, int price, String imageUrl) {
    public ProductRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(imageUrl);
    }
}
