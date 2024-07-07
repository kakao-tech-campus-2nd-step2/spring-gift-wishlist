package gift.validation;

import gift.service.MemberService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    MemberService memberService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !memberService.hasDuplicatedEmail(value);
    }

}
