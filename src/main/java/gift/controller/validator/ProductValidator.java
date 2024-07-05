package gift.controller.validator;

import gift.controller.dto.ProductRequestDto;
import gift.exception.ProductErrorCode;
import gift.exception.ProductException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validateKakaoWord(ProductRequestDto productRequestDto) throws ProductException {
        if(productRequestDto.getName().contains("카카오")){
            throw new ProductException(ProductErrorCode.HAS_KAKAO_WORD);
        }
    }
}
