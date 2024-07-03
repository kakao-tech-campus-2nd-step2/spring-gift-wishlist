package gift.product.restapi.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
@Constraint(validatedBy = NotContainingKaKaoValidator.class) // 3
public @interface NotContainingKaKao {
    String message() default "\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다."; // 4
    Class[] groups() default {};
    Class[] payload() default {};
}
