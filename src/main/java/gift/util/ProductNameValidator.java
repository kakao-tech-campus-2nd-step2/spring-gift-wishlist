package gift.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameValidator implements ConstraintValidator<NameConstraint, String> {

    String specialCharErrorMsg = "( ), [ ], +, -, &, /, _ 그 외 특수 문자 사용 불가";
    String kakaoErrorMsg = "'카카오''가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능";

    @Override
    public void initialize(NameConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        boolean containSpecialChar = !nameField.matches("^[a-zA-Z가-힣()\\[\\]\\+\\-&/_]+$");
        boolean containKakaoChar = nameField.contains("카카오");
        if (containSpecialChar && containKakaoChar) {
            return returnValidationResult(specialCharErrorMsg + " and " + kakaoErrorMsg, cxt);
        }
        if (containSpecialChar) {
            return returnValidationResult(specialCharErrorMsg, cxt);
        }
        if (containKakaoChar) {
            return returnValidationResult(kakaoErrorMsg, cxt);
        }
        return true;
    }

    private static boolean returnValidationResult(String errorMsg, ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate(errorMsg)
                .addConstraintViolation();
        return false;
    }
}


