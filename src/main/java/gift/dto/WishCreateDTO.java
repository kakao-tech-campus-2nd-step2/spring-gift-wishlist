package gift.dto;

public record WishCreateDTO(
        long userId,
        long productId,
        int quantity
) {
}
