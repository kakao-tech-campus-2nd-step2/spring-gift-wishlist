package gift.validator;

import gift.annotation.Kakao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KakaoValidator implements ConstraintValidator<Kakao, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return !value.contains("카카오");
    }

}
