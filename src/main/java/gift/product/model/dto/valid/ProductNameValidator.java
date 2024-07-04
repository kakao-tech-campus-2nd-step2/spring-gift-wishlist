package gift.product.model.dto.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {
    private String excludeKeyword;

    @Override
    public void initialize(ValidProductName constraintAnnotation) {
        this.excludeKeyword = constraintAnnotation.excludeKeyword();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (!excludeKeyword.isEmpty() && name.contains(excludeKeyword)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름에 포함할 수 없는 키워드가 포함되어 있습니다: " + excludeKeyword)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
