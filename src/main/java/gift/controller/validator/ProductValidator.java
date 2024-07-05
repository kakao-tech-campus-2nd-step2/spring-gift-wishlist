package gift.controller.validator;

import gift.controller.dto.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public boolean hasKakaoWord(ProductRequestDto productRequestDto) {
        return productRequestDto.getName().contains("카카오");
    }
}
