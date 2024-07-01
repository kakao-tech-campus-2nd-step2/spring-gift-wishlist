package gift.dto;

import gift.exception.PriceLessThanZeroException;

public record ProductRequest(String name, Integer price, String imageUrl) {
    public ProductRequest {
        if (price < 0) {
            throw new PriceLessThanZeroException("가격은 0원보다 작을 수 없습니다.");
        }
    }
}
