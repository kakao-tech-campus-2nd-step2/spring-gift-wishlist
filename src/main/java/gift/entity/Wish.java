package gift.entity;

public record Wish(
    Long userId,
    Long productId,
    int quantity
) {

}
