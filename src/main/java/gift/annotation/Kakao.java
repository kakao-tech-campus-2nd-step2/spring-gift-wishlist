package gift.annotation;

import gift.validator.KakaoValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = KakaoValidator.class)
public @interface Kakao {
    String message() default "카카오";
    Class[] groups() default {};
    Class[] payload() default {};
}
