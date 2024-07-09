package gift.main.annotation;


import gift.main.validator.ProductNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


@Documented
@Constraint(validatedBy = ProductNameValidator.class )
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidName {

    String value() default "";
    String message() default "Invalid product name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}