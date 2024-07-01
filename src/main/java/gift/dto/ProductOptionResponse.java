package gift.dto;

import gift.exception.PriceLessThanZeroException;
import gift.model.Product;
import gift.model.ProductOption;

public record ProductOptionResponse(Long id, Long productId, String name, Integer additionalPrice) {
    public static ProductOptionResponse from(ProductOption productOption) {
        return new ProductOptionResponse(productOption.getId(), productOption.getProductId(), productOption.getName(), productOption.getAdditionalPrice());
    }
}
