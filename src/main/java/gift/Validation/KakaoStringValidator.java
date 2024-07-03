package gift.Validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KakaoStringValidator implements ConstraintValidator<KakaoStringValidation, String> {
    private final Pattern pattern = Pattern.compile(".*(kakao|카카오).*", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return !pattern.matcher(value).find();
    }
}
