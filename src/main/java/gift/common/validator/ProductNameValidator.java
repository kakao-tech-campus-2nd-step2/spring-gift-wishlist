package gift.common.validator;

import gift.common.annotation.ProductNameValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator <ProductNameValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.contains("카카오")) {
            return false;
        }

        return true;
    }
}
