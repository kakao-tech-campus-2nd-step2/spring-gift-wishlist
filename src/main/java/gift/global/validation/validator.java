package gift.global.validation;

import gift.dto.ProductDTO;
import gift.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 유효성 검증 클래스
 */
@Component
public class validator {

    public void validateProduct(ProductDTO productDTO) {
        if (productDTO.getName() == ""
            || productDTO.getPrice() == 0
            || productDTO.getImageUrl() == "") {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "입력값이 유효하지 않습니다.");
        }
    }
}
