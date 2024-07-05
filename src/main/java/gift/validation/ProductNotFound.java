package gift.validation;

import gift.constant.ErrorMessage;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductNotFoundValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductNotFound {

    String message() default ErrorMessage.PRODUCT_NOT_FOUND;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
