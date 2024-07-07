package gift.core.annotaions;

import gift.core.exception.ValidationMessage;
import jakarta.validation.Payload;

public @interface ValidCharset {
	String message() default ValidationMessage.INVALID_CHARSET_MSG;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern() default "^[a-zA-Z0-9가-힣\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$";
}
