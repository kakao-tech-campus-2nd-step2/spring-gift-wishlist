package gift.dto;

public record WishInfoDTO(
        long id,
        long userId,
        long productId,
        int quantity
) {
}
