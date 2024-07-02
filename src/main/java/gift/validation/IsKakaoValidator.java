package gift.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsKakaoValidator implements ConstraintValidator<isKakao, String> {

    @Override
    public void initialize(isKakao constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return !value.contains("카카오");
    }
}