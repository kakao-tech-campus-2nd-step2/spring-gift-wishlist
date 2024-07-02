package gift.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {


    @Override
    public void initialize(ValidProductName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.equals("")) {
            return false;
        }

        if (name.length() > 15) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름은 15자 이내여야 합니다.")
                    .addConstraintViolation();
            return false;
        }

        if (!name.matches("[a-zA-Z0-9가-힣\\(\\)\\[\\]\\-+&_\\/\\s]+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름에는 (), [], -, +, &, _, /, 공백을 제외한 특수 문자를 사용할 수 없습니다.")
                    .addConstraintViolation();
            return false;
        }

        if (name.contains("카카오")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름에 '카카오' 가 포함 되어 있습니다. 담당 MD와 협의가 필요합니다.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
