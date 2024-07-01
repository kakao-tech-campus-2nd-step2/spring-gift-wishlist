package gift.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @Pattern(regexp = "^[ -&()+,/_a-zA-z0-9가-힣]{1,15}$") String name,
        @PositiveOrZero Integer price,
        String imageUrl) {
}
