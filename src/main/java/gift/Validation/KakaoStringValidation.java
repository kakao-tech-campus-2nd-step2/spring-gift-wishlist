package gift.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = KakaoStringValidator.class)
public @interface KakaoStringValidation {
    String message() default "'Kakao' is not allowed in the string.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
