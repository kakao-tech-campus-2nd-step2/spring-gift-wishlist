package gift.product.model.dto.valid;

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
        if (name.isBlank()){
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
            context.buildConstraintViolationWithTemplate("사용할 수 없는 특수문자가 포함되어 있습니다.")
                    .addConstraintViolation();
            return false;
        }

        if (name.contains("카카오")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("상품 이름에 '카카오'를 포함할 수 없습니다. 담당 MD와 협의 후 사용해주세요.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
