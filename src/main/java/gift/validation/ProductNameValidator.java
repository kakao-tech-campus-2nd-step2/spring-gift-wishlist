package gift.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    private static final String VALID_PATTERN = "^[A-Za-z0-9 ()\\[\\]+\\-&/_가-힣]+$";

    @Override
    public void initialize(ValidProductName constraintAnnotation) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.contains("카카오")) {
            return false;
        }
        return value.matches(VALID_PATTERN);
    }
}
