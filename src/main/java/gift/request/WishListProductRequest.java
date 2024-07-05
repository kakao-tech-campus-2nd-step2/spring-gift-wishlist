package gift.request;

import gift.constant.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WishListProductRequest {

    @NotNull(message = ErrorMessage.PRODUCT_ID_REQUIRED)
    private Long productId;

}
