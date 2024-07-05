package gift.validator;

import gift.exception.ProductErrorCode;
import gift.exception.ProductException;
import gift.model.dto.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public boolean hasKakaoWord(ProductRequestDto productRequestDto) {
        return productRequestDto.getName().contains("kakao");
    }
}
