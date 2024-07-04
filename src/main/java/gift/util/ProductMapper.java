package gift.util;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;

public class ProductMapper {

    public static ProductResponse toResponseDto(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public static Product toEntity(ProductRequest request) {
        return new Product(
                null,
                request.name(),
                request.price(),
                request.imageUrl()
        );
    }

}
