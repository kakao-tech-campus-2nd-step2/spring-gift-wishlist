package gift.Product;

import gift.Validation.KakaoStringValidation;
import gift.Validation.SpecialCharacterValidation;
import org.hibernate.validator.constraints.Length;

public record ProductDTO(
    @Length(min = 1, max = 15, message = "product name's length must be between 1 and 15")
    @SpecialCharacterValidation
    @KakaoStringValidation
    String name,
    Integer price,
    String imageUrl) {

}
