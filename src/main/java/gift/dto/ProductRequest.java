package gift.dto;

import gift.exception.LengthExceededException;
import gift.exception.PriceLessThanZeroException;

public record ProductRequest(String name, Integer price, String imageUrl) {
    public ProductRequest {
        if (price < 0) {
            throw new PriceLessThanZeroException("가격은 0원보다 작을 수 없습니다.");
        }
        if (name.length() > 15) {
            throw new LengthExceededException("상품 이름은 15자를 초과할 수 없습니다.");
        }
    }
}
