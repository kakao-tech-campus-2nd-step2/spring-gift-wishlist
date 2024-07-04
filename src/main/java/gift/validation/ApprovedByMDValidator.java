package gift.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ApprovedByMDValidator implements ConstraintValidator<ApprovedByMD, String> {

    @Override
    public void initialize(ApprovedByMD constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return true;
        }

        return !name.contains("카카오") || isApprovedByMD(name);
    }

    //md 인증
    private boolean isApprovedByMD(String name) {

        return false;
    }
}
