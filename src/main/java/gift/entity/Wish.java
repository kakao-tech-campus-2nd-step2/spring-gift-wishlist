package gift.entity;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.service.ProductService;
import gift.dto.ProductResponseDto;

public class Wish {
    public final Long id;
    public final Long userId;
    public final Long productId;

    public Wish(Long id, Long userId, Long productId, ProductService productService) {
        validateProductId(productId, productService);
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public Wish(Long userId, Long productId, ProductService productService) {
        this(null, userId, productId, productService);
    }

    private void validateProductId(Long productId, ProductService productService) {
        productService.getAllProducts().stream()
                .filter(p -> p.id.equals(productId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + productId));
    }
}
