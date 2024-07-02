package gift.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = IsKakaoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface isKakao {

    String message() default "카카오가 포함된 상품은 MD와 협의후 등록가능합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}