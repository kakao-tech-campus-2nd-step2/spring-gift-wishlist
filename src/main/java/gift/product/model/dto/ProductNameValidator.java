package gift.product.model.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {
    private static final int MAX_LENGTH = 15;
    private static final String NAME_PATTERN = "^[a-zA-Z0-9 ()\\[\\]\\+\\-&/_]*$";

    @Override
    public void initialize(ValidProductName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return false;
        }

        if (name.length() > MAX_LENGTH) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름은 15자 이하로 입력해주세요.")
                    .addConstraintViolation();
            return false;
        }

        if (!name.matches(NAME_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name contains invalid characters")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
