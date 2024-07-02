package gift.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpecialCharacterValidator.class)
public @interface SpecialCharacterValidation {
    String message() default "Special Character only allow (, ), [, ], +, -, &, /, _";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
