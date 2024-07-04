package gift.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    private static final Pattern VALID_NAME_PATTERN =
            Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣\\(\\)\\[\\]\\+\\-\\&\\/\\_\\s]*$");
    private static final int MAX_LENGTH = 15;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            addConstraintViolation(context, "이름을 1자 이상 입력하세요");
            return false;
        }

        if (value.length() > MAX_LENGTH) {
            addConstraintViolation(context, "이름은 최대 15자까지 입력 가능합니다");
            return false;
        }

        if (!VALID_NAME_PATTERN.matcher(value).matches()) {
            addConstraintViolation(context, "사용 불가능한 특수 문자가 포함되어 있습니다");
            return false;
        }

        if (value.contains("카카오")) {
            addConstraintViolation(context, "카카오가 포함된 문구는 담당 MD와 상의하세요");
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
