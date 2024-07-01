package gift.product.application.command;

public record ProductUpdateCommand(
        Long productId,
        String name,
        Integer price,
        String imageUrl
) {
}
