package gift.dto;

import gift.exception.PriceLessThanZeroException;

public record ProductOptionRequest(Long productId, String name, Integer additionalPrice) {
    public ProductOptionRequest {
        if (additionalPrice < 0) {
            throw new PriceLessThanZeroException("가격은 0원보다 작을 수 없습니다.");
        }
    }
}
