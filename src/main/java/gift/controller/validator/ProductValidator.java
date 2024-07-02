package gift.controller.validator;

import gift.controller.dto.ProductRequestDto;

public class ProductValidator {
    public boolean hasKakaoWord(ProductRequestDto productRequestDto){
        return productRequestDto.getName().contains("kakao");
    }
}
