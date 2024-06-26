package gift.product.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import gift.product.exception.ProductPriceOutOfRangeException;

public class ProductPrice {

    private Long value;

    public ProductPrice() {}

    public ProductPrice(Long value) {
        if (value < 0) {
            throw new ProductPriceOutOfRangeException();
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    // JSON 직렬화를 위해 @JsonValue 사용
    @JsonValue
    public Long toJson() {
        return value;
    }
}