package gift.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ApprovedByMDValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApprovedByMD {
    String message() default "상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의가 필요합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
