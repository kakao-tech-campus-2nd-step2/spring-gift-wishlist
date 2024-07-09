package gift.web.validation.constraints;

import static gift.web.validation.exception.code.ErrorCode.DESCRIPTION.INVALID_PASSWORD_FORMAT;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import gift.web.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default INVALID_PASSWORD_FORMAT;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}