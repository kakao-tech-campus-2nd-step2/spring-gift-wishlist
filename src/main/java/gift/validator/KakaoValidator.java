package gift.validator;

import gift.annotation.Kakao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KakaoValidator implements ConstraintValidator<Kakao, String> {

    private static final String INVALID_SUBSTRING = "카카오";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.contains(INVALID_SUBSTRING);
    }

}
