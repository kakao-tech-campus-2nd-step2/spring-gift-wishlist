package gift.product.application.command;

public record ProductCreateCommand(
        String name,
        Integer price,
        String imageUrl
) {
}
