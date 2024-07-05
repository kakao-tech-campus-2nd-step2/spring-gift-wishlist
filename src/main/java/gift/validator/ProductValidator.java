package gift.validator;

import gift.exception.ProductErrorCode;
import gift.exception.ProductException;
import gift.model.dto.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validateKakaoWord(ProductRequestDto productRequestDto) throws ProductException{
        if (productRequestDto.getName().contains("kakao")){
            throw new ProductException(ProductErrorCode.HAS_KAKAO_WORD);
        };
    }
}
