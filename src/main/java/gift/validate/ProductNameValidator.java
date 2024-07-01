package gift.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {


    private static final int MAX_LENGTH = 15;
    private static final Pattern VALID_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9가-힣\\(\\)\\[\\]\\+\\-\\&\\/\\_\\s]*$");
    private static final String FORBIDDEN_WORD = "카카오";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        if (value.length() > MAX_LENGTH) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Product name must be at most " + MAX_LENGTH + " characters long.")
                .addConstraintViolation();
            return false;
        }

        if (!VALID_PATTERN.matcher(value).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Product name contains invalid characters.")
                .addConstraintViolation();

            return false;
        }

        if (value.contains(FORBIDDEN_WORD)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    " '카카오' 가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다. 따로 문의 부탁드립니다.")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
