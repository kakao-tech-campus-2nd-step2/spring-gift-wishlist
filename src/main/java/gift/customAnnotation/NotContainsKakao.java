package gift.customAnnotation;

import gift.validator.NotContainsKakaoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotContainsKakaoValidator.class)
public @interface NotContainsKakao {

    String message() default "`카카오`가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
