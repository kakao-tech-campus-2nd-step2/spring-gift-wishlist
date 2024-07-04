package gift.Validation;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = KakaoStringValidator.class)
public @interface KakaoStringValidation {

    String message() default "if you include 'kakao' in you product name, then you must be consult with your MD";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
