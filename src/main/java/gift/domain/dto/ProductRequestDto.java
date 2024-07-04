package gift.domain.dto;

import jakarta.validation.constraints.*;

public record ProductRequestDto(
    String name,
    Long price,
    String imageUrl) {
}