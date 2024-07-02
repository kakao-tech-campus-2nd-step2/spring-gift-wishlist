package gift.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductNameValidator.class)
public @interface ValidProductName {
    String message() default "올바르지 않은 입력입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
