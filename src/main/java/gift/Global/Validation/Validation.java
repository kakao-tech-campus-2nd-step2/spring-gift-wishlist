package gift.Global.Validation;

import gift.DTO.ProductDTO;
import gift.Global.Exception.BusinessException;
import gift.Global.Response.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 유효성 검증 클래스
 */
@Component
public class Validation {

    public void validateProduct(ProductDTO productDTO) {
        if (productDTO.getName() == ""
            || productDTO.getPrice() == 0
            || productDTO.getImageUrl() == "") {
            throw new BusinessException(ErrorCode.INVALID_PRODUCT_ARGUMENT);
        }
    }
}
