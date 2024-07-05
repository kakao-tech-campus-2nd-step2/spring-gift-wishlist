package gift.dto.product;

public record ProductResponseDTO(
    Long id,
    String name,
    int price,
    String imageUrl
) { }
