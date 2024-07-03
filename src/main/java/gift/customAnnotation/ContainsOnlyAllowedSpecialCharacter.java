package gift.customAnnotation;

import gift.validator.ContainsOnlyAllowedSpecialCharacterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContainsOnlyAllowedSpecialCharacterValidator.class)
public @interface ContainsOnlyAllowedSpecialCharacter {

    String message() default "(, ), [, ], +, -, &, /, _ 와 같은 특수문자만 상품 이름에 허용됩니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
