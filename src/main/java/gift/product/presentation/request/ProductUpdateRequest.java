package gift.product.presentation.request;

import gift.product.application.command.ProductUpdateCommand;

public record ProductUpdateRequest(
        String name,
        Integer price,
        String imageUrl
) {
    public ProductUpdateCommand toCommand(Long productId) {
        return new ProductUpdateCommand(
                productId,
                name,
                price,
                imageUrl
        );
    }
}
