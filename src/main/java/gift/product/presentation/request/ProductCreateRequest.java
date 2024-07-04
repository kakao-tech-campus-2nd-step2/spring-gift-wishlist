package gift.product.presentation.request;

import gift.product.application.command.ProductCreateCommand;

public record ProductCreateRequest(
        String name,
        Integer price,
        String imageUrl) {
    public ProductCreateCommand toCommand() {
        return new ProductCreateCommand(
                name,
                price,
                imageUrl
        );
    }
}
