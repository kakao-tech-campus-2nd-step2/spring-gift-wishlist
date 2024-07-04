package gift.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KakaoConstraintValidator implements ConstraintValidator<gift.annotation.KakaoValidator, String> {

    private static final String INVALID_SUBSTRING = "카카오";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return true;
        }
        return !value.contains(INVALID_SUBSTRING);
    }

}
