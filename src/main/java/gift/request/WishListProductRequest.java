package gift.request;

import gift.constant.ErrorMessage;
import gift.validation.ProductNotFound;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WishListProductRequest {

    @NotNull(message = ErrorMessage.PRODUCT_ID_REQUIRED)
    @ProductNotFound
    private Long productId;

}
